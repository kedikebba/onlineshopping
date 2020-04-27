package edu.miu.pm.onlineshopping.product.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private long id;
    private String categoryName;
    private List<Product> productList = new ArrayList<>();
}
