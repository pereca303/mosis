package lab.mosis.data;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {

    private static DataStorage instance;

    private List<MyPlace> data;

    private DataStorage() {

        this.data = new ArrayList<>();

        // TODO fake initialization

        this.data.add(new MyPlace("First name", "First description", 166.39115, -30.52477));
        this.data.add(new MyPlace("Second name", "Second description", -45.42354, 6.90155));

    }

    public static DataStorage getInstance() {

        if (DataStorage.instance == null) {
            DataStorage.instance = new DataStorage();
        }

        return DataStorage.instance;
    }

    public MyPlace getPlaceAt(int index) {

        if (this.data.size() >= index) {
            return this.data.get(index);
        }

        return null;
    }

    public void addPlace(MyPlace new_place) {

        this.data.add(new_place);
    }

    public List<MyPlace> getData() {

        return this.data;
    }

}
