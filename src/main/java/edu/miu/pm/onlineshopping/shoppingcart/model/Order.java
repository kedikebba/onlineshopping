package edu.miu.pm.onlineshopping.shoppingcart.model;

import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.product.model.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Column(name = "Total_Price")
    private double totalPrice;

//    @ElementCollection
//    @CollectionTable(name = "productId_quantity_map",
//            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
//    @MapKeyColumn(name = "product_id")
//    @Column(name = "quantity")
//    private Map<Long, Integer> cartItems = new HashMap<>();

    @OneToMany
    private List<CartItem> cartItems;

    private LocalDate orderCompletedDate;
    private Enum orderStatus = OrderStatus.PENDING;
    @ManyToOne
    @JoinColumn
    private EndUser buyer;
    private boolean isSufficientStockExist;
    @OneToMany
    private List<Product> stockErrors;
    private LocalDate deliveryDate;

    @OneToOne
    private PaymentStatus paymentStatus;

//    public void setCartItems(CartItem cartItem){
//        this.cartItems.put(cartItem.getProductId(), cartItem.getQuantity());
//    }


}
