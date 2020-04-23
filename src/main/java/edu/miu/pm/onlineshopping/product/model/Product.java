package edu.miu.pm.onlineshopping.product.model;

import edu.miu.pm.onlineshopping.admin.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id")
    private long id;
    @Column(name = "Product_Name")
    private String productName;
    private double price;
    @Column(name = "Product_Id")
    private LocalDate createdDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "Category_Id")
    private Category category;
    private int quantity;
    private User vendor;


}
