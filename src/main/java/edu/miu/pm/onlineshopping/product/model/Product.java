package edu.miu.pm.onlineshopping.product.model;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private int status;
//    private String productImage;

    private LocalDate submittedDate = LocalDate.now();
    private boolean approvedStatus = false;
    private boolean isAvailable = true;
    private int soldQuantity;
    @ManyToOne
    private Vendor vendor;

    //Boolean approvedStatus - False
    //boolean isAvailable - Set when Qty >= 1 - True else False
    //int soldQty - Default - 0
    //Vendor - ManyToOne
    //Date - Creation


//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;


//    public Product(Category category){
//        this.category = category;
//    }

//    public Product(  String productNumber, String productName, double price, String description, int quantity, String status, String productPath,Category category) {
//
//        this.productNumber = productNumber;
//        this.productName = productName;
//        this.price = price;
//        this.description = description;
//        this.quantity = quantity;
//        this.status = status;
//        this.productImage = productPath;
//        this.category=category;
//
//    }

//    public long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(long productId) {
//        this.productId = productId;
//    }
//
//    public String getProductNumber() {
//        return productNumber;
//    }
//
//    public void setProductNumber(String productNumber) {
//        this.productNumber = productNumber;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getProductImage() {
//        return productImage;
//    }
//
//    public void setProductImage(String productImage) {
//        this.productImage = productImage;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "productId=" + productId +
//                ", productNumber='" + productNumber + '\'' +
//                ", productName='" + productName + '\'' +
//                ", price=" + price +
//                ", description='" + description + '\'' +
//                ", quantity=" + quantity +
//                ", status='" + status + '\'' +
//                ", productImage='" + productImage + '\'' +
//                ", category=" + category +
//                '}';
//    }
}
