package hr.k33zo.gamewebshop.service;

import hr.k33zo.gamewebshop.model.*;
import hr.k33zo.gamewebshop.repository.ItemRepo;
import hr.k33zo.gamewebshop.repository.OrderItemRepo;
import hr.k33zo.gamewebshop.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    private final Cart cart;

    private final ItemRepo itemRepo;
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final UserDetailService userDetailService;

    @Autowired
    public CartService(Cart cart, ItemRepo itemRepo, OrderRepo orderRepo, OrderItemRepo orderItemRepo, UserDetailService userDetailService) {
        this.cart = cart;
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.userDetailService = userDetailService;
    }
    public void addToCart(CartItem cartItem) {

        boolean exists = false;

        for (CartItem ci : cart.getCart()) {
            if (Objects.equals(ci.getItemId(), cartItem.getItemId())) {
                exists = true;
                break;
            }
        }

        if (exists) {
            cart.getCart().forEach(ci -> {
                if (Objects.equals(ci.getItemId(), cartItem.getItemId())) {
                    ci.setQuantity(ci.getQuantity() + cartItem.getQuantity());
                }
            });
        } else {
            cart.getCart().add(cartItem);
        }

    }

    public List<CartItem> listCartItems() {
        return cart.getCart();
    }

    public double getTotalPriceOfItemsInCart() {

        double price = 0;


        for (CartItem cartItem : cart.getCart()) {
            price += cartItem.getPrice() * cartItem.getQuantity();
        }

        return price;
    }

    public String getProductNames() {

        String productNames = "";
        for (CartItem cartItem : cart.getCart()) {
            productNames += cartItem.getName() + ", ";
        }

        return productNames;
    }

    public void removeFromCart(long itemId, int quantity) {
        Iterator<CartItem> iterator = cart.getCart().iterator();
        while (iterator.hasNext()) {
            CartItem ci = iterator.next();
            if (ci.getItemId() == itemId) {
                if (ci.getQuantity() > quantity) {
                    ci.setQuantity(ci.getQuantity() - quantity);
                } else {
                    iterator.remove();
                }
            }
        }
    }



    public void clearCart() {
        cart.getCart().clear();
    }


    public void commitTransaction(String type) {

        User user = new User();
        long id = userDetailService.GetUsersIdByName();
        String username = userDetailService.getUserName();
        user.setId(id);
        user.setUsername(username);


        Order order = new Order();
        order.setUser(user);
        order.setType(type);
        order.setOrderDate(new java.util.Date());
        order.setItems(new ArrayList<>());
        orderRepo.save(order);
        for (CartItem cartItem : cart.getCart()) {

            Item item = new Item();

            itemRepo.findById(cartItem.getItemId()).ifPresent(i -> {
                item.setId(i.getId());
                item.setName(i.getName());
                item.setCategory(i.getCategory());
            });

            OrderItem transactionItem = new OrderItem();
            transactionItem.setOrder(order);


            transactionItem.setItem(item);
            transactionItem.setQuantity(cartItem.getQuantity());
            order.getItems().add(transactionItem);

            orderItemRepo.save(transactionItem);



        }
        clearCart();

    }


}
