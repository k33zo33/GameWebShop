package hr.k33zo.gamewebshop.event;

import hr.k33zo.gamewebshop.model.CartItem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class AddItemToCartEvent extends ApplicationEvent {

    private CartItem cartItem;

    public AddItemToCartEvent(Object source, CartItem cartItem) {
        super(source);
        this.cartItem = cartItem;
    }
}
