package skynet.nl.skynet_whattoeat;

import java.util.List;

/**
 * Created by Oteken on 06-06-15.
 */
public class Category {

    private static List<String> allCategories = Database.getAllCategories();

    private String name;
    private Integer id;

    public Category(Integer id) {
        this.id = id;
        this.name = allCategories.get(id);
    }

    public static void setAllCategories(List<String> allCategories) {
        Category.allCategories = allCategories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static List<String> getAllCategories() {
        return allCategories;
    }




}
