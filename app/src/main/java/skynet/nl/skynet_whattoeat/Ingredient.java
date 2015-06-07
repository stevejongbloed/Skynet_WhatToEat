package skynet.nl.skynet_whattoeat;

/**
 * Created by steve_000 on 1-6-2015.
 */
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Oteken on 17-05-15.
 */
public class Ingredient implements Parcelable
{
    private String name;
    private Integer id;
    private int iconId;
    // this is the unit of measurement e.a. kilogram, liter/litre
    private Unit unit;
    private String expirationDate;
    private static List<String> allIngredients = Database.getAllIngredients();


    public Ingredient(Integer id, Unit unit)
    {
        this.id = id;
        this.name = allIngredients.get(id);
        this.unit = unit;
    }


    public Ingredient(Integer id, Unit unit,
                      String expirationDate)
    {
        this.id = id;
        this.name = allIngredients.get(id);
        this.unit = unit;
        this.expirationDate = expirationDate;
    }

    public String getName()
    {
        return name;
    }

    public int getIconId()
    {
        return iconId;
    }

    public void setIconId(int iconId)
    {
        this.iconId = iconId;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {



    }
}
