package lab.mosis.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import lab.mosis.MyPlace;
import lab.mosis.R;
import lab.mosis.data.DataStorage;

public class EditActivity extends AppCompatActivity {

    private EditText edite_name;
    private EditText edit_description;

    private int place_index;

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

        this.edite_name.setText(place.getName());
        this.edit_description.setText(place.getDescription());

        ((Button) this.findViewById(R.id.edit_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.isEnabled()) {
                    MyPlace edit_place = DataStorage.getInstance().getPlaceAt(place_index);

                    edit_place.setName(edite_name.getText().toString());
                    edit_place.setDescription(edit_description.getText().toString());

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
}
