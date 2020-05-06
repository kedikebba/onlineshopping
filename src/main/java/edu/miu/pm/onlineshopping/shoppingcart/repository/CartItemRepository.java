package edu.miu.pm.onlineshopping.shoppingcart.repository;

import edu.miu.pm.onlineshopping.shoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


////////////////     Author:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
