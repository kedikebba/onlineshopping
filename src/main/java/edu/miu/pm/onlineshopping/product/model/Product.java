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

    private String status;
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

    public Product(String productNumber, String productName, double price, String description, int quantity, String status, String productImage, LocalDate creationDate, Vendor vendor, Category category) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.productImage = productImage;
        this.creationDate = creationDate;
        this.vendor = vendor;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(boolean approvedStatus) {
        this.approvedStatus = approvedStatus;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(int soldQty) {
        this.soldQty = soldQty;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", productImage='" + productImage + '\'' +
                ", creationDate=" + creationDate +
                ", approvedStatus=" + approvedStatus +
                ", isAvailable=" + isAvailable +
                ", soldQty=" + soldQty +
                ", vendor=" + vendor +
                ", category=" + category +
                '}';
    }
}
