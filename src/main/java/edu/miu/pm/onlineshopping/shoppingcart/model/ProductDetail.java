package edu.miu.pm.onlineshopping.shoppingcart.model;

import edu.miu.pm.onlineshopping.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    private long id;
    private Product product;
    private int quantity;
    private double productCost;
}
