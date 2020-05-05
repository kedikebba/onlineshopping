package edu.miu.pm.onlineshopping.product.model;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Product implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_number")
    private String productNumber;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price")
    private  double price;
    @Column(name = "description")
    private String description;
    private int quantity;

    private ProductStatus status;
    private String productImage;

    private LocalDate creationDate;
    private boolean approvedStatus;
    private boolean isAvailable;
    private  int soldQty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendorId")
    private Vendor vendor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;


    public Product(Category category){
        this.category = category;
    }

    public Product(String productNumber, String productName, double price, String description, int quantity, ProductStatus status, String productImage, LocalDate creationDate, boolean approvedStatus, boolean isAvailable, int soldQty, Vendor vendor, Category category) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.productImage = productImage;
        this.creationDate = creationDate;
        this.approvedStatus = approvedStatus;
        this.isAvailable = isAvailable;
        this.soldQty = soldQty;
        this.vendor = vendor;
        this.category = category;
    }


}
