package edu.miu.pm.onlineshopping.shoppingcart.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    private long id;
    private Product product;
    private int quantity;
    private double productCost;
}
