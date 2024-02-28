package com.app.selenium.dataObjects;

import com.app.selenium.utils.JacksonUtils;

import java.io.IOException;
import java.util.List;

public class Product {

    private int id;

    private String name;
    private boolean featured;
    public Product(){

    }
    public Product(int id) throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
        /*
        It returns objects of type Product class
         */
        for(Product product : products){
            if(product.getId() == id){
                this.id = id;
                this.name = product.getName();
            }
        }
    }

    public Product(boolean featured) throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);

        for(Product product: products ){
            if(featured){
                this.id = product.getId();
                this.name = product.getName();
            }
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeatured( boolean featured ){
        this.featured = featured ;
    }

    public boolean getFeatured( ){
        return featured;
    }


}
