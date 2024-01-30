package com.tests.selenium.tests.dataProviderTests;

import com.app.selenium.dataObjects.Product;
import com.app.selenium.dataProvider.MyDataProvider;
import com.app.selenium.utils.JacksonUtils;
import com.tests.selenium.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestWithDataProvider extends BaseTest {

    /*@DataProvider(name = "getFeaturedProducts")
    public Product[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJson("products.json", Product[].class);
        /* I have created Product.class only. products.json file has List of Products.
        So , when deserializing the Json , we have to deserialize to Product[].class

        Suppose, if we have created Products class and if we have created a varaible of List<Product>
        we can deserialize to Products.class which ahs List varaible of type Product.


    }*/

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void verifyUsingDataProvider(Product product){//Here the parameter to pass is of type Product
        product.getName();
        product.getId();
    }

    @Test
    public void testIQQuestions(){
        Integer p1 = 100;
        Integer p2 = 100;
        System.out.println(p1 == p2 );

        Integer s1 = 200;
        Integer s2 = 200;
        System.out.println( s1 == s2 );
    }

    @Test
    public void verifyBinary() {
        int a = 1041;//2147483647;

        int length = returnBinaryGap(a);
        System.out.println("Binary gap :  " + length);
        System.out.println("Math .max() : " + Math.max(1,0));
    }

    @Test
    public void verifySwapping(){
        swap2Numbers(-10, -40);
    }

    public void swap2Numbers(int a, int b){
        System.out.println("Before swapping: a: " + a + " b: " + b);
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("After swapping: a: " + a + " b: " + b);

    }
    public int returnBinaryGap(int number){
        int a = number;
        String binary = Integer.toBinaryString(a);
        System.out.println("Binary Representation : " + binary);
        char[] ch = binary.toCharArray();
        int startIndex = 0;
        int endIndex = 0;
        char[] binaryGap;
        int length = 0;
        for( int i = 0 ; i < ch.length-1 ; i++ ) {
            if (ch[i] == 1) {
                startIndex = i;
                for (int j = i + 1; j < ch.length; j++) {
                    if (ch[j] == 0) {
                        if ((j - i) >= 2) {
                            if (ch[j] == 1) {
                                endIndex = j;
                                binaryGap = binary.toCharArray().toString().substring(startIndex, endIndex).toCharArray();
                                length = binaryGap.length;
                            }
                        }

                    }
                }
            }
        }
        return length;
    }
}
