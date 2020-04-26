package edu.miu.pm.onlineshopping.shoppingcart.model;

import edu.miu.pm.onlineshopping.admin.User;
import edu.miu.pm.onlineshopping.product.model.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Order_Id")
    private long id;
//    @Column(name = "Order_Number")
    private int orderNumber;
//    @Column(name = "Total_Price")
    private double totalPrice;
    @OneToMany
//    @Column(name = "Product_List")
    private List<Product> productList;
    private double tax;
//    @Column(name = "Order_Date")
    private LocalDateTime orderDate;
    @ManyToOne
//    @JoinColumn(name="User_ID")
    private User buyer;

    public Order(int orderNumber, double totalPrice, List<Product> productList, double tax, LocalDateTime orderDate, User buyer) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.productList = productList;
        this.tax = tax;
        this.orderDate = orderDate;
        this.buyer = buyer;
    }
}
