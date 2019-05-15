package lab.mosis.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongToDoubleFunction;

public class MyPlaceData {

    private List<MyPlace> myPlace;
    private Map<String, Integer> myPlaceKeyIndexMapping;
    private DatabaseReference database;
    private static final String FIREBASE_CHILD = "my-place";

    private MyPlaceData() {

        this.myPlace = new ArrayList<>();
        this.myPlaceKeyIndexMapping = new HashMap<>();
        this.database = FirebaseDatabase.getInstance().getReference();
        this.database.child(MyPlaceData.FIREBASE_CHILD).addChildEventListener(childEventListener);
        this.database.child(MyPlaceData.FIREBASE_CHILD).addListenerForSingleValueEvent(valueEventListener);

    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    private ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public void addNewPlace(MyPlace place) {

        String key = database.push().getKey();
        this.myPlace.add(place);
        myPlaceKeyIndexMapping.put(key, this.myPlace.size() - 1);
        database.child(MyPlaceData.FIREBASE_CHILD).child(key).setValue(palce);
        place.key = key;

    }

    public void deletePlace(int index) {

        database.child(MyPlaceData.FIREBASE_CHILD).child(this.myPlace.get(index).key).removeValue();
        this.myPlace.remove(index);
        this.recreateKeyIndexMapping();

    }

    public void updatePlace(int index, String name, String desc, Double longt, Double lat) {

        MyPlace newPlace = this.myPlace.get(index);

        newPlace.name = name;
        newPlace.description = desc;
        newPlace.longitude = longt;
        newPlace.latitude = lat;

        database.child(MyPlaceData.FIREBASE_CHILD).child(this.myPlace.get(index).key).setValue(newPlace);

    }

    private void recreateKeyIndexMapping() {

        myPlaceKeyIndexMapping.clear();
        for (int i = 0; i < this.myPlace.size(); i++) {
            myPlaceKeyIndexMapping.put(myPlace.get(i).key, i);
        }

    }

}
