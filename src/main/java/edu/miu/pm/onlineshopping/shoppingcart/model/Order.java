package edu.miu.pm.onlineshopping.shoppingcart.model;

import edu.miu.pm.onlineshopping.admin.User;
import edu.miu.pm.onlineshopping.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Id")
    private long id;
    @Column(name = "Order_Number")
    private int orderNumber;
    @Column(name = "Total_Price")
    private double totalPrice;
    @OneToMany
    private List<Product> productList;
    private double tax;
    @ManyToOne
    @JoinColumn(name="User_ID")
    private User buyer;
}
