package edu.miu.pm.onlineshopping.product.service.Imp;


import edu.miu.pm.onlineshopping.product.model.ProductDetail;
import edu.miu.pm.onlineshopping.product.repository.IProductDetailRepository;
import edu.miu.pm.onlineshopping.product.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
public class ProductDetailService implements IProductDetailService {

    private IProductDetailRepository productDetailRepository;

    @Autowired
    public ProductDetailService(IProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll(Sort.by("detailName"));
    }

    @Override
    @Transactional
    public ProductDetail save(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail findById(Long productDetailId) {
        return productDetailRepository.findById(productDetailId).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long productDetailId) {
     productDetailRepository.deleteById(productDetailId);
    }

    @Override
    public int getProductStockQuantity(Long productId) {
        return productDetailRepository.getProductStockQuantity(productId);
    }
}
