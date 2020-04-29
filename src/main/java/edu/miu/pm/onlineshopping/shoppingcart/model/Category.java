package edu.miu.pm.onlineshopping.shoppingcart.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(name = "Category_Name")
    private String categoryName;
   // @OneToMany(mappedBy = "category")
    //@JsonIgnore
    private List<Product> productList = new ArrayList<>();

    public Category(String categoryName, List<Product> productList) {
        this.categoryName = categoryName;
        this.productList = productList;
    }

    public void setProductList(Product product){
        productList.add(product);
    }
}
