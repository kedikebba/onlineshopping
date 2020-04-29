package edu.miu.pm.onlineshopping.shoppingcart.model;


import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<CartItem> cartItems;
    private double totalPrice;



}
