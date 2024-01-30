package com.app.selenium.constants;

public enum ProductType {

    BLUESHOES(1215, "Blue Shoes", "blue-shoes"),
    BLUETSHIRT(1196, "Blue Tshirt", "blue-tshirt");

    private int id;
    private String productName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    private String productLink;

    ProductType(int id, String productName, String productLink) {
        this.id = id;
        this.productName = productName;
        this.productLink = productLink;
    }


}
