package hr.k33zo.gamewebshop.controller;

import hr.k33zo.gamewebshop.model.CartItem;
import hr.k33zo.gamewebshop.model.Item;
import hr.k33zo.gamewebshop.model.Order;
import hr.k33zo.gamewebshop.model.OrderItem;
import hr.k33zo.gamewebshop.service.ItemService;
import hr.k33zo.gamewebshop.service.OrderItemService;
import hr.k33zo.gamewebshop.service.OrderService;
import hr.k33zo.gamewebshop.service.UserDetailService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@EnableWebSecurity
@Controller
@RequestMapping("/orderController")
public class OrderController {

    private final OrderService orderService;
    private final UserDetailService userDetailService;
    private final ItemService itemService;
    private final OrderItemService orderItemService;

    public OrderController(OrderService orderService, UserDetailService userDetailService, ItemService itemService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.userDetailService = userDetailService;
        this.itemService = itemService;
        this.orderItemService = orderItemService;
    }
    @GetMapping("/getOrders")
    public String getAllItems(Model model){
        long id = userDetailService.GetUsersIdByName();

        if(UserDetailService.isAdmin()){
            model.addAttribute("items", orderService.getAllOrders());
        } else {
            model.addAttribute("items", orderService.getOrdersByUserId(id));
        }

        return "orders";
    }

    public String addOrder(@RequestBody List<CartItem> items){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order order = new Order();

        Order savedOrder = orderService.saveOrder(order);

        for(CartItem cartItem : items){

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            Item item = new Item();
            item.setId(cartItem.getItemId());
            orderItem.setItem(item);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemService.saveOrderItem(orderItem);

        }



        return "redirect:/itemController/items";
    }

    @GetMapping("/search")
    public String searchOrders(@RequestParam(required = false) String user,
                                     @RequestParam(required = false) LocalDate date,
                                     Model model) {
        long id;

        //if date and name are  null and user not admin, get all transactions for that user
        //if date and name are null and user is admin, get ALL transactions
        //if name is null and date is not null and user is admin, get all transactions for that date
        //if name is not null and date is null and user is admin, get all transactions for that user
        //if name is
        //if any field null and not Admin, get username print for him
        //
        List<Order> transactions = null;
        if ((user == null  || user == "" || date == null) && !UserDetailService.isAdmin()) {

            id = userDetailService.GetUsersIdByName();
            transactions = orderService.searchByUserIdAndPurchaseDate(id, date);
        }
        else if ((user == null || user == "") && UserDetailService.isAdmin() && (date != null)) {
            transactions = orderService.getAllOrdersByDate(date);
        }
        else if ((user != null || user != "") && UserDetailService.isAdmin() && (date != null))
        {
            id = userDetailService.GetUserIdByProvidedName(user) ;
            transactions = orderService.searchByUserIdAndPurchaseDate(id, date);
        }
        else if (date == null && UserDetailService.isAdmin())
        {
            id = userDetailService.GetUserIdByProvidedName(user) ;
            transactions = orderService.getOrdersByUserId(id);
        }

        model.addAttribute("items", transactions);
        return "orders";
    }


}
