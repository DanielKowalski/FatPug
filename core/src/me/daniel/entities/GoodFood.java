package me.daniel.entities;

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
        foods.put("chocolate", false);
        foods.put("grape", false);
        foods.put("avocado", false);
        foods.put("bone", true);
        foods.put("food", true);
    }

    public static boolean isGoodFood(String name) {
        if(foods.containsKey(name))return foods.get(name);
        return false;
    }

    public static String getRandomFood() {
        return foods.keySet().toArray()[new Random().nextInt(foods.size())].toString();
    }

}
