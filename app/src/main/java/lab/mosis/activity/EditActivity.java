package lab.mosis.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lab.mosis.R;
import lab.mosis.data.DataStorage;
import lab.mosis.data.MyPlace;

public class EditActivity extends AppCompatActivity {

    private EditText edite_name;
    private EditText edit_description;
    private EditText longitude_edit;
    private EditText latitude_edit;

    private int place_index;

    private int MAP_ACTIVITY_CODE = 3243;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity);

        this.populateView();

    }

    private void populateView() {

        Intent intent = this.getIntent();

        this.place_index = intent.getIntExtra("place_index", 0);
        MyPlace place = DataStorage.getInstance().getPlaceAt(place_index);

        this.edite_name = ((EditText) this.findViewById(R.id.edit_place_name));
        this.edit_description = ((EditText) this.findViewById(R.id.edit_place_description));
        this.longitude_edit = ((EditText) this.findViewById(R.id.long_edit_et));
        this.latitude_edit = ((EditText) this.findViewById(R.id.lat_edit_et));

        this.edite_name.setText(place.getName());
        this.edit_description.setText(place.getDescription());
        this.longitude_edit.setText(String.valueOf(place.getLongitude()));
        this.latitude_edit.setText(String.valueOf(place.getLatitude()));

        ((Button) this.findViewById(R.id.map_edit_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map_intent = new Intent(EditActivity.this, MapActivity.class);

                map_intent.putExtra("map_context", MapContext.EditMyPlace);
                map_intent.putExtra("longitude", longitude_edit.getText().toString());
                map_intent.putExtra("latitude", latitude_edit.getText().toString());

                startActivityForResult(map_intent, MAP_ACTIVITY_CODE);

            }
        });

        ((Button) this.findViewById(R.id.edit_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.isEnabled()) {
                    MyPlace edit_place = DataStorage.getInstance().getPlaceAt(place_index);

                    edit_place.setName(edite_name.getText().toString());
                    edit_place.setDescription(edit_description.getText().toString());
                    edit_place.setLongitude(Double.parseDouble(longitude_edit.getText().toString()));
                    edit_place.setLatitude(Double.parseDouble(latitude_edit.getText().toString()));

                    setResult(Activity.RESULT_OK);
                    finish();

                }

            }
        });

        ((Button) this.findViewById(R.id.cancel_edit_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_CANCELED);
                finish();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAP_ACTIVITY_CODE) {
            // has to be

            if (resultCode == Activity.RESULT_OK) {

                double longitude = data.getDoubleExtra("longitude", -1);
                double latitude = data.getDoubleExtra("latitude", -1);

                this.longitude_edit.setText(String.valueOf(longitude));
                this.latitude_edit.setText(String.valueOf(latitude));

            }

        }

    }

}
