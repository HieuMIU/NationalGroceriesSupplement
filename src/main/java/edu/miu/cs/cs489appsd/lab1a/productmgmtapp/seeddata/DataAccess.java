package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.seeddata;

import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<Product>() {
            {
                add(new Product("3128874119", "Banana", LocalDate.of(2023,1,24),124, 0.55));
                add(new Product("2927458265", "Apple", LocalDate.of(2022,12,9),18, 1.09));
                add(new Product("9189927460", "Carrot", LocalDate.of(2023,3,31),89, 2.99));
            }
        };

        return products;
    }
}
