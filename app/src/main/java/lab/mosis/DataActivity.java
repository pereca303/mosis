package lab.mosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataActivity extends AppCompatActivity {
    
    private Map<Integer, MenuTask> menu_tasks;
    
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
                
                Toast.makeText(getApplicationContext(), "This actually works", Toast.LENGTH_SHORT)
                        .show();
                
                return true;
            }
        });
        
        
    }
    
    private void initViews() {
        
        this.setSupportActionBar((Toolbar) findViewById(R.id.data_toolbar));
        ((Toolbar) this.findViewById(R.id.data_toolbar)).inflateMenu(R.menu.data_page_menu_options);
        
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        this.getMenuInflater().inflate(R.menu.data_page_menu_options, menu);
        
        return true;
    }
    
    private void initDataSource() {
        
        ((ListView) findViewById(R.id.data_container)).setAdapter(new MyAdapter(this,
                                                                                R.layout.list_item,
                                                                                DataStorage.getInstance()
                                                                                        .getData()));
        
    }
    
    
}
