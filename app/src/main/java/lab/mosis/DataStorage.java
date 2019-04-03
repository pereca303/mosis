package lab.mosis;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    
    private static DataStorage instance;
    
    private List<MyPlace> data;
    
    private DataStorage() {
        
        this.data = new ArrayList<>();
        
        // TODO fake initialization
        
        this.data.add(new MyPlace("First name", "First decription"));
        this.data.add(new MyPlace("Second name", "Second decription"));
        this.data.add(new MyPlace("Third name", "Third decription"));
        this.data.add(new MyPlace("Fourth name", "Fourth decription"));
        this.data.add(new MyPlace("Sixth name", "Sixth decription"));
        this.data.add(new MyPlace("Seventh name", "Seventh decription"));
        
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
    
    public MyPlace[] getData() {
        
        MyPlace[] ret_data = new MyPlace[this.data.size()];
        
        for (int i = 0; i < this.data.size(); i++) {
            ret_data[i] = new MyPlace(this.data.get(i).getName(),
                                      this.data.get(i).getDescription());
        }
        
        return ret_data;
    }
    
}
