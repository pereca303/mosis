package lab.mosis.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lab.mosis.MyPlace;
import lab.mosis.R;
import lab.mosis.data.DataStorage;

public class PlaceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        this.populateView();

    }

    private void populateView() {

        Intent intent = this.getIntent();

        int place_index = intent.getIntExtra("place_index", 0);

        MyPlace target_place = DataStorage.getInstance().getPlaceAt(place_index);

        ((TextView) this.findViewById(R.id.place_name_tv)).setText(target_place.getName());
        ((TextView) this.findViewById(R.id.place_description_tv)).setText(target_place.getDescription());

        ((Button) this.findViewById(R.id.back_place_info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}