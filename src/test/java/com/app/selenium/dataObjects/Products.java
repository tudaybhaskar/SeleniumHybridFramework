package com.app.selenium.dataObjects;

import java.util.List;

public class Products {

    private List<Product> productList;

    public Products(List<Product> productList){
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }


}
