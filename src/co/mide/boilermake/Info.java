package co.mide.boilermake;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Info extends ActionBarActivity {
	private EditText drinkNum;
	private EditText codoNum;
	private EditText sleepNum;
	private Editor e;
	boolean d = true;
	boolean c = true;
	boolean s = true;
	private Button save;
	private SharedPreferences p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		save = (Button) findViewById(R.id.saveB);
		codoNum = (EditText) findViewById(R.id.codoInfoNum);
		drinkNum = (EditText) findViewById(R.id.drinkInfoNum);
		sleepNum = (EditText) findViewById(R.id.sleepInfoNum);
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveData(v);
				
			}
		});
		drinkNum.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence ss, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(ss.length() != 0)
					d = true;
				save.setEnabled(d&&s&&c);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		sleepNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence ss, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(ss.length() != 0)
					s = true;
				save.setEnabled(d&&s&&c);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		codoNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence ss, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(ss.length() != 0)
					c = true;
				save.setEnabled(d&&s&&c);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		p = getSharedPreferences("boilermake", MODE_PRIVATE);
		e = p.edit();
	}
	void saveData(View v){
		e.putInt("sleep", Integer.parseInt((sleepNum.getText().toString())));
		e.putInt("drink", Integer.parseInt((drinkNum.getText().toString())));
		e.putInt("codo", Integer.parseInt((codoNum.getText().toString())));
		e.commit();
		Intent badge = new Intent(this, Badge.class);
		v.setEnabled(false);
		startActivity(badge);
		this.finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//
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
