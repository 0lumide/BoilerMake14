package co.mide.boilermake;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class SelectAction extends ActionBarActivity {
	private LinearLayout container;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_action);
		
		container = (LinearLayout)findViewById(R.id.container);
		ViewTreeObserver vto = container.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		    @SuppressWarnings("deprecation")
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
		    public void onGlobalLayout() {
		        ViewTreeObserver obs = container.getViewTreeObserver();
		        	//((ImageButton)findViewById(R.id.act1)).;
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
		            obs.removeOnGlobalLayoutListener(this);
		        } else {
		            obs.removeGlobalOnLayoutListener(this);
		        }
		    }

		});
	}
	public void buttonClick(View v){
//		int s = v.getId()
//		switch(){
//		case "act1":break;
//		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.select_action, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
