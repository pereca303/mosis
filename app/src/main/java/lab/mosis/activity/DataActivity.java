package lab.mosis.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import lab.mosis.MenuTask;
import lab.mosis.MyPlaceAdapter;
import lab.mosis.R;
import lab.mosis.data.DataStorage;

public class DataActivity extends AppCompatActivity {

    private Map<Integer, MenuTask> menu_tasks;

    private MyPlaceAdapter adapter;

    private int selected_index = -1;

    private int ADD_ACTIVITY_CODE = 123;
    private int EDIT_ACTIVITY_CODE = 312;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        this.initViews();
        this.initDataSource();
        this.initMenuTasks();

    }

    private void initMenuTasks() {

        this.menu_tasks = new HashMap<>();

        this.menu_tasks.put(R.id.data_first_item, new MenuTask() {

            @Override
            public boolean execute(MenuItem selected_item) {

                Intent intent = new Intent(DataActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_CODE);

                return true;
            }
        });

        this.menu_tasks.put(R.id.data_second_item, new MenuTask() {
            @Override
            public boolean execute(MenuItem selected_item) {

                if (selected_index > -1) {

                    Intent intent = new Intent(DataActivity.this, PlaceInfoActivity.class);
                    intent.putExtra("place_index", selected_index);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Select some item first ... ", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        this.menu_tasks.put(R.id.data_third_item, new MenuTask() {
            @Override
            public boolean execute(MenuItem selected_item) {

                if (selected_index > -1) {

                    Intent intent = new Intent(DataActivity.this, EditActivity.class);
                    intent.putExtra("place_index", selected_index);
                    startActivityForResult(intent, EDIT_ACTIVITY_CODE);

                } else {
                    Toast.makeText(getApplicationContext(), "Select some item first ... ", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        this.menu_tasks.put(R.id.data_fourth_item, new MenuTask() {
            @Override
            public boolean execute(MenuItem selected_item) {

                Intent map_intent = new Intent(DataActivity.this, MapActivity.class);
                map_intent.putExtra("map_context", String.valueOf(MapContext.ShowAllPlaces));

                startActivity(map_intent);

                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        MenuTask task = this.menu_tasks.get(item.getItemId());
        if (task != null) {
            task.execute(item);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == this.ADD_ACTIVITY_CODE) {
            // return from activity for creating new place

            if (resultCode == Activity.RESULT_OK) {
                // just reload listView
                Toast.makeText(this, "Mathicng result", Toast.LENGTH_SHORT).show();

                this.adapter.notifyDataSetChanged();
            }

        } else if (requestCode == this.EDIT_ACTIVITY_CODE) {

            if (resultCode == Activity.RESULT_OK) {

                Toast.makeText(this, "Place change", Toast.LENGTH_SHORT).show();

                this.adapter.notifyDataSetChanged();

            }

        }


    }

    private void initViews() {

        this.setSupportActionBar((Toolbar) findViewById(R.id.data_toolbar));
        ((Toolbar) this.findViewById(R.id.data_toolbar)).inflateMenu(R.menu.data_page_menu_options);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        ((ListView) this.findViewById(R.id.data_container)).setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(DataActivity.this, "Selected: " + position, Toast.LENGTH_SHORT).show();

                selected_index = position;

                parent.setSelected(true);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.data_page_menu_options, menu);

        return true;
    }

    private void initDataSource() {

        this.adapter = new MyPlaceAdapter(this, R.layout.list_item, DataStorage.getInstance().getData());

        ((ListView) findViewById(R.id.data_container)).setAdapter(this.adapter);

    }

}
