package edu.miu.pm.onlineshopping.product.controller;

import edu.miu.pm.onlineshopping.product.model.ProductDetail;
import edu.miu.pm.onlineshopping.product.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/product")
public class ProductDetailController {

    @Autowired
    private IProductDetailService productDetailService;

    @GetMapping(value = "/productDetails")
    public List<ProductDetail> getAllProductDetails() {
        return (List<ProductDetail>) productDetailService.findAll();
    }

    @GetMapping(value = "/productDetail/{id}")
    public ProductDetail getProductDetailById(@PathVariable("id") Long productDetailId) {
        return productDetailService.findById(productDetailId);
    }

    @PostMapping(value = "/productDetail", produces = "application/json")
    public ProductDetail save(@Valid @RequestBody ProductDetail productDetail) {
        productDetailService.save(productDetail);
        return productDetail;
    }

    @DeleteMapping(value = "/productDetail/{id}")
    public ProductDetail delete(@PathVariable("id") Long productDetailId) {
        ProductDetail productDetail = productDetailService.findById(productDetailId);
        productDetailService.delete(productDetailId);
        return productDetail;
    }

    @GetMapping(value = "/getProductStockQuantity/{id}")
    public int getProductStockQuantity(@PathVariable("id") Long productId) {
        return productDetailService.getProductStockQuantity(productId);
    }
}
