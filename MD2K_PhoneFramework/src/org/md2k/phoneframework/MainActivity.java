package org.md2k.phoneframework;

import org.md2k.phoneframework.logger.Log;
import org.md2k.phoneframework.services.sensors.ConnectionService;

import org.md2k.phoneframework.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	public static Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button button_connect = (Button) findViewById(R.id.button_connect);
		button_connect.setVisibility(View.INVISIBLE);
    	context=getBaseContext();
    	Intent i= new Intent(context, ConnectionService.class);
    	context.startService(i);             	
		
/*        button_connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d("MainActivity","Button_Connection Click");
            	context=getBaseContext();
            	Intent i= new Intent(context, ConnectionService.class);
            	context.startService(i);             	
            }
        });
*/        
		final Button button_disconnect = (Button) findViewById(R.id.button_disconnect);
        button_disconnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.d("MainActivity","Button_Disconnection Click");
            	
            	context=getBaseContext();
            	Intent i= new Intent(context, ConnectionService.class);
            	context.stopService(i);
            }
        });
        
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
