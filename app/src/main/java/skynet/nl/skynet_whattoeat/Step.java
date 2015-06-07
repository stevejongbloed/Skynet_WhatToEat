package skynet.nl.skynet_whattoeat;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oteken on 06-06-15.
 */
public class Step implements Serializable {

    private String title;
    private Double duration;
    private String text;
    private Integer stepNumber;
    private ArrayList<Tool> tools;
    private ArrayList<Ingredient> ingredients;

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }


    public String getText() {
        return text;
    }

    public Double getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTools(ArrayList<Tool> tools)
    {
        this.tools = tools;
    }


}
