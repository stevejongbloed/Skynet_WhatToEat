package skynet.nl.skynet_whattoeat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oteken on 06-06-15.
 */
public class Recipe {

    private String Name;
    private List<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private Double duration;
    private Double rating;
    private Catagory catagory;

    public void setName(String name) {
        Name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }


    public String getName() {
        return Name;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public Double getRating() {
        return rating;
    }

    public Double getDuration() {
        return duration;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void openPage()
    {

    }

}
