package edu.miu.pm.onlineshopping.shoppingcart.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<ProductDetail> productDetails;
    private double totalPrice;
}
