package lab.mosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import lab.mosis.data.MyPlace;

public class MyPlaceAdapter extends ArrayAdapter<MyPlace> {

    private Context context;
    private List<MyPlace> data_source;

    public MyPlaceAdapter(Context context, int resource, List<MyPlace> objects) {
        super(context, resource, objects);

        this.context = context;
        this.data_source = objects;
    }

    public View getView(int pos, View old_view, ViewGroup parent) {

        // reuse old view if possible
        if (old_view == null) {
            old_view = (LayoutInflater.from(this.context)).inflate(R.layout.list_item, parent, false);
        }

        // get item model from data source
        MyPlace target_place = this.data_source.get(pos);
        // populate view with model data
        ((TextView) old_view.findViewById(R.id.item_name)).setText(target_place.getName());
        ((TextView) old_view.findViewById(R.id.item_description)).setText(target_place.getDescription());
        ((TextView) old_view.findViewById(R.id.long_tv)).setText(String.valueOf(target_place.getLongitude()));
        ((TextView) old_view.findViewById(R.id.lat_tv)).setText(String.valueOf((target_place.getLatitude())));


        return old_view;
    }

}
