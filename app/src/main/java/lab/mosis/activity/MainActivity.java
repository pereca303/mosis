package lab.mosis.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

import lab.mosis.MenuTask;
import lab.mosis.R;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, MenuTask> menu_tasks;
    private ImageView main_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initViews();
        this.initMenuTasks();

    }

    private void initViews() {

        this.setSupportActionBar((Toolbar) this.findViewById(R.id.custom_toolbar));
        ((Toolbar) findViewById(R.id.custom_toolbar)).inflateMenu(R.menu.main_toolbar_items);

        this.main_logo = (ImageView) this.findViewById(R.id.main_logo);
        this.main_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent map_intent = new Intent(MainActivity.this, MapActivity.class);

                map_intent.putExtra("map_context", String.valueOf(MapContext.ShowCurrentLocation));

                startActivity(map_intent);

            }

        });

    }

    private void initMenuTasks() {

        this.menu_tasks = new HashMap<Integer, MenuTask>();

        // todo populate menu tasks

        this.menu_tasks.put(R.id.first_item, new MenuTask() {

            @Override
            public boolean execute(MenuItem selected_item) {

                // go to about activity, show about page
                Intent about_page = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about_page);

                // true means that the event is handled
                return true;
            }
        });

        this.menu_tasks.put(R.id.second_item, new MenuTask() {

            @Override
            public boolean execute(MenuItem selected_item) {

                // go to about activity, show about page
                Intent data_page = new Intent(MainActivity.this, DataActivity.class);
                startActivity(data_page);

                // true means that the event is handled
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.getMenuInflater().inflate(R.menu.main_toolbar_items, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        MenuTask menu_task = this.menu_tasks.get(id);
        if (menu_task != null) {
            return menu_task.execute(item);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        super.onStart();

//        Toast.makeText(this, "ON START", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {

        super.onResume();

//        Toast.makeText(this, "ON RESUME", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {

        super.onPause();

//        Toast.makeText(this, "ON PAUSE", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {

        super.onStop();

//        Toast.makeText(this, "ON STOP", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

//        Toast.makeText(this, "ON DESTORY", Toast.LENGTH_SHORT).show();


    }

}
