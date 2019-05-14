package lab.mosis.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lab.mosis.R;
import lab.mosis.data.DataStorage;
import lab.mosis.data.MyPlace;

public class AddActivity extends AppCompatActivity {

    private EditText name_edit;
    private EditText description_edit;
    private EditText longitude_edit;
    private EditText latitude_edit;

    private int MAP_ACTIVITY_CODE = 3345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.initViews();

    }

    private void initViews() {

        this.name_edit = ((EditText) this.findViewById(R.id.place_name_edit));
        this.description_edit = ((EditText) this.findViewById(R.id.place_description_edit));
        this.longitude_edit = ((EditText) this.findViewById(R.id.long_add_et));
        this.latitude_edit = ((EditText) this.findViewById(R.id.lat_add_et));

        ((Button) this.findViewById(R.id.create_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.isEnabled()) {
                    // special watcher controls input and disable/enable buttons

                    Toast.makeText(getApplicationContext(), "Place added", Toast.LENGTH_SHORT).show();

                    DataStorage.getInstance()
                            .addPlace(new MyPlace(name_edit.getText().toString(),
                                                  description_edit.getText().toString(),
                                                  Double.parseDouble(longitude_edit.getText().toString()),
                                                  Double.parseDouble(latitude_edit.getText().toString())));

                    setResult(Activity.RESULT_OK);
                    finish();

                } else {

                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();

                }

            }
        });

        ((Button) this.findViewById(R.id.cancel_new_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(Activity.RESULT_CANCELED);
                finish();

            }
        });

        ((Button) this.findViewById(R.id.map_add_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddActivity.this, MapActivity.class);

                intent.putExtra("map_context", String.valueOf(MapContext.AddMyPlace));

                startActivityForResult(intent, MAP_ACTIVITY_CODE);

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
