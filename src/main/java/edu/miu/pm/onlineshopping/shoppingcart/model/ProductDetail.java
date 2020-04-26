package edu.miu.pm.onlineshopping.shoppingcart.model;

import edu.miu.pm.onlineshopping.product.model.Product;
import lombok.*;

import javax.persistence.Entity;

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
