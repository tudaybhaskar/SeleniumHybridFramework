package com.app.selenium.dataProvider;

import com.app.selenium.dataObjects.Product;
import com.app.selenium.utils.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "getFeaturedProducts")
    // @DataProvider(name = "getFeaturedProducts" parallel = true)//When parallel is set to true , this runs in parallel
    public Product[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJson("products.json", Product[].class);
        /* I have created Product.class only. products.json file has List of Products.
        So , when deserializing the Json , we have to deserialize to Product[].class

        Suppose, if we have created Products class and if we have created a varaible of List<Product>
        we can deserialize to Products.class which ahs List varaible of type Product.

         */
    }
}
