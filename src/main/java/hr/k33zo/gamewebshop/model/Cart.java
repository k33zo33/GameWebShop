package hr.k33zo.gamewebshop.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Cart {
    private List<CartItem> cart = new ArrayList<>();
}
