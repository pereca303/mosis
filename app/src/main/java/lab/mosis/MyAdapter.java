package lab.mosis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<MyPlace> {
    
    private Context context;
    private MyPlace[] data_source;
    
    public MyAdapter(Context context, int resource, MyPlace[] objects) {
        super(context, resource, objects);
        
        this.context = context;
        this.data_source=objects;
    }
    
    public View getView(int pos, View old_view, ViewGroup parent) {
        
        // reuse old view if possible
        if (old_view == null) {
            old_view = (LayoutInflater.from(this.context)).inflate(R.layout.list_item,parent,false);
        }
        
        // get item model from data source
        MyPlace target_place=this.data_source[pos];
        // populate view with model data
        ((TextView) old_view.findViewById(R.id.item_name)).setText(target_place.getName());
        ((TextView) old_view.findViewById(R.id.item_description)).setText(target_place.getDescription());
        
        return old_view;
    }
    
}
