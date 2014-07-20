package eg.informationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        

 
        Button func = (Button) findViewById(R.id.func);
        
        func.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		Intent intent = new Intent(MainActivity.this, FuncActivity.class);
        		startActivity(intent);
        	}
        });
        
        Button add = (Button) findViewById(R.id.add);
        
        add.setOnClickListener(new OnClickListener() {
        	public void onClick(View v)
        	{
        		Intent intent = new Intent(MainActivity.this, AddActivity.class);
        		startActivity(intent);
        	}
        });
        
    }
}
          
         
               
             
  

