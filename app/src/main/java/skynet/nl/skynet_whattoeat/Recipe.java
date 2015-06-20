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
    private Category category;
    private int stepCount;

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

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

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getName() {
        return Name;
    }

    public Category getCategory() {
        return category;
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
