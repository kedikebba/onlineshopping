package edu.miu.pm.onlineshopping.searchModule.controller;
import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.searchModule.repository.Prouductrepository;
import edu.miu.pm.onlineshopping.searchModule.service.ProductServicereport;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ReportController {
    @Autowired
    private Prouductrepository productRepository;

    @Autowired
    private ProductServicereport productService;

    @GetMapping("/getProducts")
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    @GetMapping("/productReport/{format}")
    public String generateProductReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return productService.exportProductReport(format);
    }

}
