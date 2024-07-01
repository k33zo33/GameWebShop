package hr.k33zo.gamewebshop.listeners;

import hr.k33zo.gamewebshop.event.AddItemToCartEvent;
import hr.k33zo.gamewebshop.service.CartService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddItemToCartListener {
    private final CartService cartService;

    public AddItemToCartListener(CartService cartService) {
        this.cartService = cartService;
    }

    @EventListener
    public void onApplicationEvent(AddItemToCartEvent event) {
        cartService.addToCart(event.getCartItem());
    }
}
