package edu.miu.pm.onlineshopping.product.service.Imp;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.repository.VendorRepository;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import edu.miu.pm.onlineshopping.product.model.Category;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.repository.IProductRepository;
import edu.miu.pm.onlineshopping.product.service.ICategoryService;
import edu.miu.pm.onlineshopping.product.service.IProductDetailService;
import edu.miu.pm.onlineshopping.product.service.IProductService;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@NoArgsConstructor
@Service
public class ProductService implements  IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private ICategoryService categoryService;


//    @Autowired
//    public ProductService(IProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

   // public List<Product> getApprovedProduct(){
     //   return productRepository.getAllByStatus("approved");
    //}

//    public List<Product> findByVendor(User user){
//        return productRepository.findAllByUser(user);
//    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void delete(Long productId) {
       productRepository.deleteById(productId);
    }

    @Override
    public List<Product> findByAll(String productNumber, String productName, Double minProductPrice, Double maxProductPrice, Integer status) {
        return productRepository.findAll(productNumber, productName, minProductPrice, maxProductPrice, status);
    }

    @Override
    public List<Product> findByNameNumber(String productNumber, String productName, Integer status) {
        return productRepository.findByNameNumber(productNumber,productName,status);
    }

    @Override
    public List<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice, Integer status) {
        return findByNamePrice(productName,minProductPrice,maxProductPrice,status);
    }

    @Override
    public List<Product> findByNumberPrice(String productNumber, Double minProductPrice, Double maxProductPrice, Integer status) {
        return productRepository.findByNumberPrice(productNumber,minProductPrice,maxProductPrice,status);
    }

    @Override
    public List<Product> findByName(String productName, Integer status) {
        return productRepository.findByName(productName,status);
    }

    @Override
    public List<Product> findByNumber(String productNumber, Integer status) {
        return findByNumber(productNumber,status);
    }

    @Override
    public List<Product> findByStatus(Integer status) {
        return findByStatus(status);
    }

     @Override
    public List<Product> findByPrice(Double minProductPrice, Double maxProductPrice, Integer status) {
        return productRepository.findByPrice(minProductPrice,maxProductPrice,status);
    }

//    @Override
//    public Product save(Product product, User user) {
//
//        productRepository.save(product);
//        product.setUser(user);
////save product detail.
//        productDetailService.save(new ProductDetail(product.getDescription(),product.getDescription(), product.getQuantity(), product, user));
//
//
//        return product;
//    }

    //---------------------------- Added by Getaneh --------------------------//
    @Override
    public List<Product> searchProduct(String search) {
        return productRepository.findAllByProductNameContainsOrCategory_CategoryNameContainsOrVendor_FirstNameContains(search, search, search);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        Vendor vendor = vendorService.getVendorByName(product.getVendor().getFirstName());
        if (vendor != null){
            product.setVendor(vendor);
        }

        Category category = categoryService.getCategoryByName(product.getCategory().getCategoryName());
        if (category != null){
            product.setCategory(category);
        }
        product.setStatus(1);
        product.setApprovedStatus(true);
        return productRepository.save(product);
    }

    @Override
    public Product getByProductNameAndCategory(String productName, String categoryName) {
        return productRepository.findByProductNameAndCategory_CategoryName(productName, categoryName);
    }

    @Override
    @Transactional
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getVendorProducts(Vendor vendor) {
         return productRepository.findByVendorAndApprovedStatusIsTrue(vendor);
    }


    //----------------------------End of Added by Getaneh --------------------------//
}
