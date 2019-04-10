package lab.mosis.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lab.mosis.R;

public class AboutActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        
        ((Button)this.findViewById(R.id.about_button)).setOnClickListener(new View.OnClickListener() {
    
            @Override
            public void onClick(View v) {
            
                finish();
                
            }
        });
        
    
    }
    
}
