package hr.k33zo.gamewebshop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private long itemId;
    private String name;
    private String username;
    private Integer quantity;
    private double price;

}
