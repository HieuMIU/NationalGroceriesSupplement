package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.comparator.ProductComparator;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.dto.ProductList;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.seeddata.DataAccess;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMgmtApp {

    public static void main(String[] args) {
        DataAccess dataAccess = new DataAccess();
        List<Product> productList = dataAccess.getAllProduct();
        printProducts(productList);

    }

    private static void printProducts(List<Product> productList) {

        List<Product> sortedProductList = productList.stream()
                .sorted(new ProductComparator())
                .collect(Collectors.toList());

        //Print Product in Json Format
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        System.out.println("Printed in JSON Format");
        try {
            String jsonText = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sortedProductList);
            System.out.println(jsonText);
        } catch (JsonProcessingException e) {
            System.out.println("Something wrong!");
        }

        //Print Product in XML Format
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        xmlMapper.registerModule(new JavaTimeModule());
        System.out.println("Printed in XML Format");
        try {
            String xmlText = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new ProductList(sortedProductList));
            System.out.println(xmlText);
        } catch (JsonProcessingException e) {
            System.out.println("Something wrong!");
        }

        //Print Product in CSV Format
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        csvMapper.registerModule(new JavaTimeModule());

        CsvSchema csvSchema = csvMapper.schemaFor(Product.class);
        System.out.println("Printed in CSV Format");
        try {
            String csvText = csvMapper.writer(csvSchema).writeValueAsString(sortedProductList);
            System.out.println(csvText);
        } catch (JsonProcessingException e) {
            System.out.println("Something wrong!");
        }

    }
}