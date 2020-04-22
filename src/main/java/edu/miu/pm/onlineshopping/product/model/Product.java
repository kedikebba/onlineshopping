package edu.miu.pm.onlineshopping.product.model;

import java.time.LocalDate;


public class Product {

    private long id;
    private String productName;
    private double price;
    private LocalDate createdDate = LocalDate.now();
    private Category category;
    private int quantity;


}
