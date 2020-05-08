package edu.miu.pm.onlineshopping.admin.controller;

import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.shoppingcart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

////////////////     Contributor:               ///////
////---              Getaneh Yilma Letike, Id: 610112       ---------//

@RestController
public class HomeController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/home")
	public ModelAndView homePage() {
//		ModelAndView mav=new ModelAndView("home");
		ModelAndView mav = new ModelAndView();
		mav.addObject("products", orderService.getAllProducts());
		mav.setViewName("product_inventory");
		return mav;
	}

	//    @GetMapping("/list")
//    public ResponseEntity<List<Product>> getInventory(){
//
//        return new ResponseEntity<>(orderService.getAllProducts(), HttpStatus.OK);
//    }
}
