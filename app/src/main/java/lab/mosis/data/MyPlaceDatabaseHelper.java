package lab.mosis.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyPlaceDatabaseHelper extends SQLiteOpenHelper {

    //    private static final String DATABASE_CREATE = "create table " + MyPlaceDBAdapter.DATABASE_NAME + " (" + MyPlaceDBAdapter.PLACE_ID + " integer primary key autoincrement, " + MyPlaceDBAdapter.PLACE_NAME + " text unique not null, " + MyPlaceDBAdapter.PLACE_DESCRIPTION + " text, " + MyPlaceDBAdapter.PLACE_LONG + " text, " + MyPlaceDBAdapter.PLACE_LAT + " text, " + MyPlaceDBAdapter.PLACE_FILENAME + " text);";
    private static final String DATABASE_CREATE = "";

    private static final String DROP_IF_EXISTS = "drop table if exists " + MyPlaceDBAdapter.DATABASE_NAME;

    public MyPlaceDatabaseHelper(Context context,
                                 String name,
                                 SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyPlaceDatabaseHelper.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.v("TaskDBAdapter",
              "Upgrading from: " + oldVersion + " to: " + newVersion + ", wich will destroy all old data ... ");

        db.execSQL(MyPlaceDatabaseHelper.DROP_IF_EXISTS);

        this.onCreate(db);

    }
}
