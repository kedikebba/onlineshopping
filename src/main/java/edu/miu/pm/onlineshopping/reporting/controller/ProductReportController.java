package edu.miu.pm.onlineshopping.reporting.controller;

import edu.miu.pm.onlineshopping.product.model.Product;

import edu.miu.pm.onlineshopping.product.repository.IProductRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ProductReportController {
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductReportingService productReportingService;

    @GetMapping("/getProducts")
    public List<Product> getProducts() {

        return productRepository.findAll();

    }

    @GetMapping("/productReport/{format}")
    public String generateProductReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return productReportingService.exportProductReport(format);
    }
}
