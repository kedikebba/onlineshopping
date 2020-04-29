package edu.miu.pm.onlineshopping.shoppingcart.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Order_Id")
    private long id;

//    @Column(name = "Total_Price")
    private double totalPrice;

    @ElementCollection
    @CollectionTable(name = "productId_quantity_map",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Long, Integer> cartItems = new HashMap<>();
    private LocalDate orderCompletedDate;
    private Enum orderStatus = OrderStatus.PENDING;
//    @ManyToOne
//    @JoinColumn(name="User_ID")
//    private User buyer;
    private String buyerName;

//    public void setCartItems(CartItem cartItem){
//        this.cartItems.put(cartItem.getProductId(), cartItem.getQuantity());
//    }


}
