package edu.miu.pm.onlineshopping.shoppingcart.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private long productId;
    private int quantity;
    private String productName;
}
