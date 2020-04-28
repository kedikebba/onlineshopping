package edu.miu.pm.onlineshopping.searchModule.service;

import edu.miu.pm.onlineshopping.product.model.Product;
import edu.miu.pm.onlineshopping.searchModule.repository.Prouductrepository;
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
 public class ProductServicereport {
    @Autowired
    private Prouductrepository productRepository;


    public String exportProductReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\13196\\JasperReport";
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
