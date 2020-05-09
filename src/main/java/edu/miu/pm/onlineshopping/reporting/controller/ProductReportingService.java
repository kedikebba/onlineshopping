package edu.miu.pm.onlineshopping.reporting.controller;

import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.product.repository.IProductRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductReportingService {


    @Autowired
    private IProductRepository productRepository;

    public String exportProductReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "C:\\Users\\13196\\JasperReport";
        String path = "C:\\Users\\user\\Desktop";

        //importing the product  POJO mentioned in the product module
        //how to differentiate it from the one that is mentioned inside the shopping cart
        List<Product> products = productRepository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:products.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Kassahun Assfaw");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\products.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\products.pdf");
        }


        return "report generated in path : " + path;
    }
}
