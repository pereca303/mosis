package lab.mosis.data;

public class MyPlace {

    private long ID;

    private String name;
    private String description;

    private double longitude;
    private double latitude;

    // what ? ...
    private float filename;

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

    public double getFilename() {
        return filename;
    }

    public void setFilename(float filename) {
        this.filename = filename;
    }
}
