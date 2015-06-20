package skynet.nl.skynet_whattoeat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oteken on 17-05-15.
 */
public class Database {

    public static ArrayList<Recipe> allRecipes = new ArrayList<>();

    public static List<String> getAllUnits()
    {
        List<String> allUnits = new ArrayList<String>();

        allUnits.add("Kilo");
        allUnits.add("Gram");
        allUnits.add("Liter");


        return allUnits;
    }

    public static List<String> getAllIngredients()
    {
        List<String> allIngredients = new ArrayList<String>();

        allIngredients.add("Water");
        allIngredients.add("Banana");
        allIngredients.add("Salt");
        allIngredients.add("Spaghetti");
        allIngredients.add("Fish");
        allIngredients.add("Apple");


        return allIngredients;
    }

    public static List<String> getAllCategories()
    {
        List<String> allCatogories = new ArrayList<String>();

        allCatogories.add("Meat Dish");
        allCatogories.add("Fish Dish");
        allCatogories.add("Vegan Dish");
        allCatogories.add("Vegetarian Dish");

        return allCatogories;
    }

    public static ArrayList<Recipe> getAllRecipes()
    {
        return allRecipes;
    }
}