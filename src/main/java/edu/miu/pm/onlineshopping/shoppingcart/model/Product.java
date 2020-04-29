package edu.miu.pm.onlineshopping.shoppingcart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(name = "Product Name")
    private String productName;
    private double price;
//    @Column(name = "Created Date")
    private LocalDate createdDate;
   // @ManyToOne
//    @JoinColumn(name = "Category_Id")
   // @JoinColumn
    private Category category;
    private int quantity;
    //@OneToOne
    private User vendor;

    public Product(String productName, double price, LocalDate createdDate, Category category, int quantity, User vendor) {
        this.productName = productName;
        this.price = price;
        this.createdDate = createdDate;
        this.category = category;
        this.quantity = quantity;
        this.vendor = vendor;
    }

}
