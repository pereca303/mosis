package lab.mosis.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lab.mosis.MyPlace;
import lab.mosis.R;
import lab.mosis.data.DataStorage;

public class AddActivity extends AppCompatActivity {

    private EditText name_edit;
    private EditText description_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.initViews();

    }

    private void initViews() {

        this.name_edit = ((EditText) this.findViewById(R.id.place_name_edit));
        this.description_edit = ((EditText) this.findViewById(R.id.place_description_edit));

        ((Button) this.findViewById(R.id.create_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.isEnabled()) {
                    // special watcher controls input and disable/enable buttons

                    Toast.makeText(getApplicationContext(), "Place added", Toast.LENGTH_SHORT).show();

                    DataStorage.getInstance()
                            .addPlace(new MyPlace(name_edit.getText().toString(),
                                                  description_edit.getText().toString()));

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

    }


}
