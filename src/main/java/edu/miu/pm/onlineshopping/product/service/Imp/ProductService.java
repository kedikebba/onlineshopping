package edu.miu.pm.onlineshopping.product.service.Imp;

import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.model.ProductDetail;
import edu.miu.pm.onlineshopping.product.model.ProductStatus;
import edu.miu.pm.onlineshopping.product.repository.IProductRepository;
import edu.miu.pm.onlineshopping.product.service.IProductDetailService;
import edu.miu.pm.onlineshopping.product.service.IProductService;
import edu.miu.pm.onlineshopping.shoppingcart.model.Order;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class ProductService implements  IProductService {

    private IProductRepository productRepository;

    @Autowired
    private IProductDetailService productDetailService;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getApprovedProduct(){
        return productRepository.getAllByStatus("ApprovedStatus");
    }

    public List<Product> findAllByVendor(Vendor vendor){
        return productRepository.findAllByVendor(vendor);
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void updateStock(Order order) {

    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {


       return productRepository.save(product);



    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void delete(Long productId) {
       productRepository.deleteById(productId);
    }

    @Override
    public List<Product> findByAll(String productNumber, String productName, Double minProductPrice, Double maxProductPrice, ProductStatus status) {
        return productRepository.findAll(productNumber, productName, minProductPrice, maxProductPrice, status);
    }

    @Override
    public List<Product> findByNameNumber(String productNumber, String productName, ProductStatus status) {
        return productRepository.findByNameNumber(productNumber,productName,status);
    }

    @Override
    public List<Product> findByNamePrice(String productName, Double minProductPrice, Double maxProductPrice, ProductStatus status) {
        return findByNamePrice(productName,minProductPrice,maxProductPrice,status);
    }

    @Override
    public List<Product> findByNumberPrice(String productNumber, Double minProductPrice, Double maxProductPrice, ProductStatus status) {
        return productRepository.findByNumberPrice(productNumber,minProductPrice,maxProductPrice,status);
    }

    @Override
    public List<Product> findByName(String productName, ProductStatus status) {
        return productRepository.findByName(productName,status);
    }

    @Override
    public List<Product> findByNumber(String productNumber, ProductStatus status) {
        return findByNumber(productNumber,status);
    }

    @Override
    public List<Product> findByStatus(ProductStatus status) {
        return findByStatus(status);
    }

     @Override
    public List<Product> findByPrice(Double minProductPrice, Double maxProductPrice, ProductStatus status) {
        return productRepository.findByPrice(minProductPrice,maxProductPrice,status);
    }


    //Added by Getaneh - I changed this to return null to clear error, please remove null and un-coment the below return
    @Override
    public List<Product> searchProduct(String search) {
        return null;
       // return productRepository.findAllByProductNameContainsOrCategory_CategoryNameContainsOrVendor_FirstNameContains(search, search, search);
    }


    @Override
    public List<Product> findByProductNameContainsOrCategory(String categoryName) {
        return productRepository.findByProductNameContainsOrCategory(categoryName);
    }

    @Override
    public List<Product> findByCategoryNameContainsOrVendor(String categoryNumber) {
        return  productRepository.findByCategoryNameContainsOrVendor(categoryNumber);
    }

    @Override
    public List<Product> findByFirstNameContain(String firstName) {
        return  productRepository.findByFirstNameContain(firstName);
    }

    @Override
    public List<Product> findAllByProductNameContainsOrCategory_CategoryNameContainsOrVendor_FirstNameContains(String categoryName, String categoryNumber, String firstName) {

        if(!findByProductNameContainsOrCategory(categoryName).equals("") && findByCategoryNameContainsOrVendor(categoryNumber).equals("")&& findByFirstNameContain(firstName).equals("") )
            productRepository.findByProductNameContainsOrCategory(categoryName);
        else  if(findByProductNameContainsOrCategory(categoryName).equals("") && !findByCategoryNameContainsOrVendor(categoryNumber).equals("") && findByFirstNameContain(firstName).equals("") )
            return  productRepository.findByProductNameContainsOrCategory(categoryNumber);
        else  if(findByProductNameContainsOrCategory(categoryName).equals("") && findByCategoryNameContainsOrVendor(categoryNumber).equals("") && !findByFirstNameContain(firstName).equals(""))
            return  productRepository.findByFirstNameContain(firstName);
        return findAll();
    }
}
