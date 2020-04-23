package edu.miu.pm.onlineshopping.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Category_Id")
    private long id;
    @Column(name = "Category_Name")
    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();
}
