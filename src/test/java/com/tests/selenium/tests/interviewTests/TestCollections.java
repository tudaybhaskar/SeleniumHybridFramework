package com.tests.selenium.tests.interviewTests;


import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestCollections {

    @Test
    public void verifyMap_Iteration(){
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        map.forEach( (key, value) -> System.out.println("key:" + key +" :" + "value: " +value));
        System.out.println(" ---- Using For each --------");
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            System.out.println("key:" + entry.getKey() +" :" + "value: " +entry.getValue());
        }
    }

    @Test
    public void verifyMap_Iteration_filter(){
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);
        map.put("Five", 5);

        map.entrySet().stream()
                .filter((entry) -> entry.getValue()%2 == 0 )
                .forEach( (entry) -> System.out.println("key:" + entry.getKey() +" :" + "value: " +entry.getValue()));

    }

    @Test
    public void verifyArrayListIteration(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Orange");

        for(String item : arrayList){
            System.out.println("Item in Array List : " + item);
        }
        System.out.println("-----Using Iterator---------");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
