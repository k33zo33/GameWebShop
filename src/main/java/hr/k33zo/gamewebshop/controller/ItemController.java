package hr.k33zo.gamewebshop.controller;


import hr.k33zo.gamewebshop.event.AddItemToCartEvent;
import hr.k33zo.gamewebshop.model.CartItem;
import hr.k33zo.gamewebshop.model.Category;
import hr.k33zo.gamewebshop.model.Item;
import hr.k33zo.gamewebshop.service.CategoryService;
import hr.k33zo.gamewebshop.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@EnableWebSecurity
@RequestMapping("/itemController")
@Controller
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ItemController(ItemService itemService, CategoryService categoryService, ApplicationEventPublisher eventPublisher) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.eventPublisher = eventPublisher;
    }



    @GetMapping("/items")
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "items";
    }

    @GetMapping("/addItem")
    public String showAddItemForm(Model model) {

        model.addAttribute("categories", categoryService.getAllCategories());
        return "addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("categoryId") int category,
                          @RequestParam("price") double price,
                          @RequestParam("url") String url,
                          Model model) {

        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setPicture(url);
        item.setCategory(categoryService.getCategoryById((long) category).orElse(new Category()));

        itemService.saveItem(item);

        model.addAttribute("message", "Item added successfully");

        return "addItem";
    }

    @GetMapping("/items/details/{id}")
    public String getItemDetails(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return "item-not-found";
        }
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping("/addItemToCart")
    public String addItemToCart(@RequestParam("name") String name,
                                @RequestParam("quantity") Integer quantity,
                                @RequestParam("itemId") long itemId,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        CartItem cartItem = new CartItem();
        cartItem.setItemId(itemId);

        cartItem.setPrice(itemService.getItemById(itemId).getPrice());
        cartItem.setUsername(username);
        cartItem.setName(name);
        cartItem.setQuantity(quantity);

        eventPublisher.publishEvent(new AddItemToCartEvent(this, cartItem));
        redirectAttributes.addAttribute("message", "Item added to cart successfully");


        return "redirect:/cart/showCart";
    }

    @GetMapping("/edit/{id}")
    public String showEditItemForm(@PathVariable Long id, Model model) {
        Item item = itemService.getItemById(id);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("item", item);
        model.addAttribute("categories", categories);
        return "editItem";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable Long id, @ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/itemController/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return "redirect:/itemController/items";
    }



}
