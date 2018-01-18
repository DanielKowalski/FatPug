package me.daniel.screens;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by daniel on 18.01.2018.
 */

public class GoodFood {

    private static Map<String, Boolean> foods;

    public GoodFood() {
        foods = new HashMap<String, Boolean>();
        foods.put("apple", true);
        foods.put("cupcake", false);
        foods.put("ham", true);
        foods.put("bread", true);
        foods.put("chocolate", false);
    }

    public static boolean isGoodFood(String name) {
        if(foods.containsKey(name))return foods.get(name);
        return false;
    }

    public static String getRandomFood() {
        return foods.keySet().toArray()[new Random().nextInt(foods.size())].toString();
    }

}
