package edu.miu.pm.onlineshopping.shoppingcart.model;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.product.model.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

////////////////     Author:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Id")
    private long id;
    @Column(name = "Order_Number")
    private String orderNumber;
    private double totalPrice;
    private double shippingPrice = 5.5;
    private String method = "Paypal";
    private String currency = "USD";
    @OneToMany
    private List<CartItem> cartItems;
    private LocalDate orderCompletedDate;
    private Enum orderStatus = OrderStatus.PENDING;
    @ManyToOne
    @JoinColumn
    private EndUser buyer;
    private boolean isSufficientStockExist = true;
    @OneToMany
    private List<Product> stockErrors = new ArrayList<>();
    private LocalDate deliveryDate;


}
