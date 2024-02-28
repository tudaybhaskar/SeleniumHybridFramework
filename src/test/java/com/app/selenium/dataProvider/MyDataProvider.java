package com.app.selenium.dataProvider;

import com.app.selenium.dataObjects.Product;
import com.app.selenium.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "getFeaturedProducts", parallel = true)
    // @DataProvider(name = "getFeaturedProducts" parallel = true)//When parallel is set to true , this runs in parallel
    public Object[] getFeaturedProducts() throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
        List<Product> listProduct = new ArrayList<>();
        for( Product product : products ){
            if( product.getFeatured() ){
                listProduct.add(product);
            }
        }
        return listProduct.toArray();
        /*
        I got an error when i am trying to perform the below step
        (Product[]) listProduct.toArray(); Got class cast exception because i am trying to cast Object to Product[]
         */
        /* I have created Product.class only. products.json file has List of Products.
        So , when deserializing the Json , we have to deserialize to Product[].class

        Suppose, if we have created Products class and if we have created a variable of List<Product>
        we can deserialize to Products.class which ahs List variable of type Product.

         */
    }
}
