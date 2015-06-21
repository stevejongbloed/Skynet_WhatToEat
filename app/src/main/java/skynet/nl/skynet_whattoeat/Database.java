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

        allIngredients.add("Tomato");
        allIngredients.add("Cucumber");
        allIngredients.add("Chicken");
        allIngredients.add("Spaghetti");
        allIngredients.add("Beef");
        allIngredients.add("Rice");
        allIngredients.add("Aubergine");
        allIngredients.add("Garlic");
        allIngredients.add("Onion");
        allIngredients.add("Lime");
        allIngredients.add("Thyme");
        allIngredients.add("Mint");
        allIngredients.add("Paprica");
        allIngredients.add("Salmon");
        allIngredients.add("Dough");
        allIngredients.add("Potato");



        return allIngredients;
    }

    public static List<String> getAllCategories()
    {
        List<String> allCatogories = new ArrayList<String>();

        allCatogories.add("Entree");
        allCatogories.add("Main course");
        allCatogories.add("Dessert");
        return allCatogories;
    }

    public static ArrayList<Recipe> getAllRecipes()
    {
        return allRecipes;
    }
}