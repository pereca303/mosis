package lab.mosis.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import lab.mosis.R;
import lab.mosis.data.DataStorage;
import lab.mosis.data.MyPlace;

public class PlaceInfoActivity extends AppCompatActivity {

    private LinearLayout long_lat_holder;
    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        this.populateView();

        this.long_lat_holder = (LinearLayout) this.findViewById(R.id.long_lat_holder_info);
        this.long_lat_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map_intent = new Intent(PlaceInfoActivity.this, MapActivity.class);

                map_intent.putExtra("map_context", String.valueOf(MapContext.ShowMyPlace));

                map_intent.putExtra("longitude", longitude);
                map_intent.putExtra("latitude", latitude);

                startActivity(map_intent);

            }

        });

    }

    private void populateView() {

        Intent intent = this.getIntent();

        int place_index = intent.getIntExtra("place_index", 0);

        MyPlace target_place = DataStorage.getInstance().getPlaceAt(place_index);

        ((TextView) this.findViewById(R.id.place_name_tv)).setText(target_place.getName());
        ((TextView) this.findViewById(R.id.place_description_tv)).setText(target_place.getDescription());
        ((TextView) this.findViewById(R.id.long_info_tv)).setText(String.valueOf(target_place.getLongitude()));
        ((TextView) this.findViewById(R.id.lat_info_tv)).setText(String.valueOf(target_place.getLatitude()));

        ((Button) this.findViewById(R.id.back_place_info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.longitude = target_place.getLongitude();
        this.latitude = target_place.getLatitude();

    }
}
