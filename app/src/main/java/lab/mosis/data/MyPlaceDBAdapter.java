package lab.mosis.data;

import android.database.sqlite.SQLiteDatabase;

public class MyPlaceDBAdapter {

    public static final String DATABASE_NAME = "MyPlaceDb";
    public static final String DATABASE_TABlE = "MyPlace";
    public static final int DATABASE_VERSION = 1;

    public static final String PLACE_ID = "ID";
    public static final String PLACE_NAME = "Name";
    public static final String PLACE_DESCRIPTION = "Desc";
    public static final String PLACE_LONG = "Long";
    public static final String PLACE_LAT = "Lat";
    public static final String PLACE_FILENAME = "Filename";

    private SQLiteDatabase database;

    public MyPlaceDBAdapter() {


    }

}
