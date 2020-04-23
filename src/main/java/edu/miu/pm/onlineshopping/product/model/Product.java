package edu.miu.pm.onlineshopping.product.model;

import edu.miu.pm.onlineshopping.admin.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id")
    private long id;
    @Column(name = "Product Name")
    private String productName;
    private double price;
    @Column(name = "Created Date")
    private LocalDate createdDate = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "Category_Id")
    private Category category;
    private int quantity;
    @OneToOne
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
