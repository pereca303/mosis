package lab.mosis.data;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MyPlace {

    private long ID;

    public String name;
    public String description;

    public double longitude;
    public double latitude;

    @Exclude
    public String key;

    public MyPlace(){}


    public MyPlace(String name, String description, double longitude, double latitude) {

        this.name = name;
        this.description = description;

        this.longitude = longitude;
        this.latitude = latitude;

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}