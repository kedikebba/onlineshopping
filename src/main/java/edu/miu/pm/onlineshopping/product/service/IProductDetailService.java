package edu.miu.pm.onlineshopping.product.service;

import edu.miu.pm.onlineshopping.product.model.ProductDetail;

import java.util.List;

public interface IProductDetailService {
    public List<ProductDetail> findAll();

    public ProductDetail save(ProductDetail productDetail);

    public ProductDetail findById(Long productDetailId);

    public void delete(Long productDetailId);

    public int getProductStockQuantity(Long productId);
}
