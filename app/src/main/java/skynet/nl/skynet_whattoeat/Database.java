package skynet.nl.skynet_whattoeat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oteken on 17-05-15.
 */
public class Database {

    public static ArrayList<Recipe> allRecipes = hardCodeRecipes();

    public static List<String> getAllUnits()
    {
        List<String> allUnits = new ArrayList<String>();

        allUnits.add("Kilogram");
        allUnits.add("Gram");
        allUnits.add("Milligram");
        allUnits.add("Litre");
        allUnits.add("Gallon");
        allUnits.add("Amount");
        allUnits.add("Cup");
        allUnits.add("Spoon");


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
        allIngredients.add("Pear");
        allIngredients.add("Anchovies");
        allIngredients.add("Sugar");
        allIngredients.add("Asparagus");
        allIngredients.add("Aubergine");
        allIngredients.add("Avocado");
        allIngredients.add("Beef");
        allIngredients.add("Black Pepper");
        allIngredients.add("Blue Berry");
        allIngredients.add("Rice");
        allIngredients.add("Bread");
        allIngredients.add("Cabbage");
        allIngredients.add("Carrot");
        allIngredients.add("Cayenne Pepper");
        allIngredients.add("Cheese");
        allIngredients.add("Chilly");
        allIngredients.add("Cinnamon");
        allIngredients.add("Clam");
        allIngredients.add("Cocoa");
        allIngredients.add("Crab");
        allIngredients.add("Date");
        allIngredients.add("Egg");
        allIngredients.add("Flour");
        allIngredients.add("Beans");
        allIngredients.add("Galangal");
        allIngredients.add("Guacamole");
        allIngredients.add("Halibut");
        allIngredients.add("Honey");
        allIngredients.add("Ham");
        allIngredients.add("Ice");
        allIngredients.add("Jam");
        allIngredients.add("Kiwi");
        allIngredients.add("Ketchup");
        allIngredients.add("Lamb");
        allIngredients.add("Lemon");
        allIngredients.add("Macaroni");
        allIngredients.add("Mandarin");
        allIngredients.add("Mango");
        allIngredients.add("Milk");
        allIngredients.add("Chocolate");
        allIngredients.add("Mustard");
        allIngredients.add("Potatoes");
        allIngredients.add("Oyster");
        allIngredients.add("Pepper");
        allIngredients.add("Peanuts");
        allIngredients.add("Rabbit");
        allIngredients.add("Salmon");
        allIngredients.add("Sardine");
        allIngredients.add("Trout");
        allIngredients.add("Spinach");
        allIngredients.add("Turkey");
        allIngredients.add("Tomato");
        allIngredients.add("Venison");
        allIngredients.add("Wasabi");
        allIngredients.add("Watermelon");
        allIngredients.add("Garlic");
        allIngredients.add("Yoghurt");
        allIngredients.add("Tomato Paste");
        allIngredients.add("Sunflower Oil");
        allIngredients.add("Olive Oil");

        Collections.sort(allIngredients);

        return allIngredients;
    }

    public static List<String> getAllCategories()
    {
        List<String> allCategories = new ArrayList<String>();

        allCategories.add("Meat Dish");
        allCategories.add("Fish Dish");
        allCategories.add("Vegan Dish");
        allCategories.add("Vegetarian Dish");

        return allCategories;
    }

    public static ArrayList<Recipe> getAllRecipes()
    {
        return allRecipes;
    }

    private static ArrayList<Recipe> hardCodeRecipes()
    {
        ArrayList<Recipe> newRecipes = new ArrayList<Recipe>();

        Recipe newRecipe = new Recipe();

        newRecipe.setName("Spaghetti Tomato Sauce");
        newRecipe.setCategory(new Category(2));
        newRecipe.setDuration(30.0);

        ArrayList<Step> allSteps = new ArrayList<Step>();

        Step newStep1 = new Step();
        newStep1.setStepNumber(1);
        newStep1.setTitle("Preperation");
        newStep1.setText("First off let's get our required ingredients and tools. " +
                "You will be needing a pan, go ahead and fill it with water now and get it to boil. after this you can proceed to the next step");
        allSteps.add(newStep1);


        Step newStep2 = new Step();
        newStep2.setStepNumber(2);
        newStep2.setTitle("Spaghetti Fase");
        newStep2.setText("Now just dump the spaghetti into the water and let it stay for ~10min. Proceed to next step :).");
        allSteps.add(newStep2);


        Step newStep3 = new Step();
        newStep3.setStepNumber(3);
        newStep3.setTitle("Sauce Fase");
        newStep3.setText("After the spaghetti is ready take it out and now you pour a layer of sunflower seed oil into the pan mixed with some water. " +
                "You can also put in the tomato paste now. Let that tomato paste sit in the heat for half a minute, and the sauce should be done now. Proceed to next step");
        allSteps.add(newStep3);


        Step newStep4 = new Step();
        newStep4.setStepNumber(4);
        newStep4.setTitle("Enjoy Fase");
        newStep4.setText("All that remains to do now is mix the sauce into the spaghetti and you are done. Good job pall, you can also add some yoghurt into the spaghetti or on your plate separately, enjoy your meal. ");
        allSteps.add(newStep4);

        newRecipe.setSteps(allSteps);

        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();

        Unit newUnit1 = new Unit(0, 1.0);
        Ingredient newIngredient1 = new Ingredient(53, newUnit1);

        allIngredients.add(newIngredient1);

        newRecipe.setIngredients(allIngredients);
        newRecipes.add(newRecipe);

        return newRecipes;
    }
}