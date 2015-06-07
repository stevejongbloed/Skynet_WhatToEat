package skynet.nl.skynet_whattoeat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Oteken on 17-05-15.
 */

public class Unit implements Parcelable
{
    private String measurementUnit;
    private double amount;
    private static List<String> allUnits = Database.getAllUnits();

    public Unit(Integer choice, Double amount)
    {
        measurementUnit = allUnits.get(choice);
        this.amount = amount;
    }

    public String getMeasurementUnit()
    {
        return measurementUnit;
    }

    public String getAmount()
    {
        return Double.toString(amount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}


