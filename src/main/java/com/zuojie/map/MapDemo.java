package com.zuojie.map;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,String>map=new HashMap<>();
        map.put("小凡","帅");
        map.put("小熊","很帅");
        map.put("小胖","相当帅");
        String string = map.get("小熊");
        System.out.println("==="+string);

    }
}
