package lab.mosis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private Button simple_button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.getViews();
        
        this.initHandlers();
        
    }
    
    private void initHandlers() {
        
        this.simple_button.setOnClickListener(new View.OnClickListener() {
    
            @Override
            public void onClick(View v) {
    
                Toast.makeText(getApplicationContext(),"You clicked ^_^",Toast.LENGTH_SHORT).show();
                
            }
        });
        
    }
    
    private void getViews(){
        
        this.simple_button=this.findViewById(R.id.simple_button);
        
        
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        
        Toast.makeText(this,"ON START",Toast.LENGTH_SHORT).show();
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
    
        Toast.makeText(this,"ON RESUME",Toast.LENGTH_SHORT).show();
        
    }
    
    @Override
    protected void onPause() {
        super.onPause();
    
        Toast.makeText(this,"ON PAUSE",Toast.LENGTH_SHORT).show();
        
    }
    
    @Override
    protected void onStop() {
        super.onStop();
    
        Toast.makeText(this,"ON STOP",Toast.LENGTH_SHORT).show();
    
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    
        Toast.makeText(this,"ON DESTORY",Toast.LENGTH_SHORT).show();
    
    
    }
    
}
