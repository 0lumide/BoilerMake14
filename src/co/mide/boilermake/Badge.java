package co.mide.boilermake;

import java.util.ArrayList;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Badge extends ActionBarActivity {
	private ImageView background;
	private final double radiusRatio = 0.63;
	private int radius;
	private double tickWidth;
	private double tickHeight;
	private float lifeCount;
	private float percent;
	private int centerX;
	private int centerY;
	private TextView lifeDisplay;
	int count;
	private final int numTicks = 14;
	private ImageButton[] ticks;
	private double[][] tickPos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_badge);
		background = (ImageView) findViewById(R.id.background);
		lifeDisplay = (TextView)findViewById(R.id.lifeDisplay);
		tickPos = new double[numTicks][3];
		getPercent();
		ViewTreeObserver vto = background.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		    @SuppressWarnings("deprecation")
			@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
			@Override
		    public void onGlobalLayout() {
		        ViewTreeObserver obs = background.getViewTreeObserver();
		        radius = (int)((float)background.getWidth() / 2);
				centerX = (int) radius;
				centerY = (int)((float)background.getHeight() / 2);//background.getBottom() - (int)radius;
				ticks = new ImageButton[numTicks];
				positionButtons(radius);
		        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
		            obs.removeOnGlobalLayoutListener(this);
		        } else {
		            obs.removeGlobalOnLayoutListener(this);
		        }
		    }

		});
	}

	void getPercent(){
		//This should read the percent from memory 
		//but now I'll just use 75.5%
		percent = 75.5f;
		lifeCount = (percent/(100f/numTicks));
	}
	@SuppressLint("NewApi")
	void positionButtons(int r){
		ticks[0] = (ImageButton)findViewById(R.id.tick1);
		ticks[1] = (ImageButton)findViewById(R.id.tick2);
		ticks[2] = (ImageButton)findViewById(R.id.tick3);
		ticks[3] = (ImageButton)findViewById(R.id.tick4);
		ticks[4] = (ImageButton)findViewById(R.id.tick5);
		ticks[5] = (ImageButton)findViewById(R.id.tick6);
		ticks[6] = (ImageButton)findViewById(R.id.tick7);
		ticks[7] = (ImageButton)findViewById(R.id.tick8);
		ticks[8] = (ImageButton)findViewById(R.id.tick9);
		ticks[9] = (ImageButton)findViewById(R.id.tick10);
		ticks[10] = (ImageButton)findViewById(R.id.tick11);
		ticks[11] = (ImageButton)findViewById(R.id.tick12);
		ticks[12] = (ImageButton)findViewById(R.id.tick13);
		ticks[13] = (ImageButton)findViewById(R.id.tick14);
		tickWidth = ticks[0].getWidth();
		tickHeight = ticks[0].getHeight();
		//int xOff = (int)(tickWidth/2 + (background.getWidth() - background.getHeight())/2);
		//int yOff = (int)(-1 * tickHeight/2);
		for(int i = 0; i < numTicks;  i++){
			double angle = i * (360f/numTicks);
			int[] pos = calcPos(i, angle);
			double[] temp = {pos[0], pos[1], angle};
			tickPos[i] = temp;
		}
		startAnimation();
	}
	@SuppressLint("NewApi")
	void animateLight(int i){
		/*ValueAnimator anim = ValueAnimator.ofInt(((ColorDrawable)ticks[i].getBackground()).getColor(), Color.parseColor("#0000ff"));
		final int temp = i;
		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
		    @SuppressLint("NewApi")
			@Override
		    public void onAnimationUpdate(ValueAnimator animation) {
		    	ticks[temp].setBackground((Drawable) animation.getAnimatedValue());
		    }
		});

		anim.start();*/
		ticks[i].setBackground(new ColorDrawable(Color.parseColor("#0000ff")));
	}
	void animateHealth(int i){
		//TODO
		//create new thread
		//LightUp lightup = new LightUp(ticks, lifeDisplay, this, percent, lifeCount);
		//new Thread(lightup).start();
		final int temp = i;
		new Handler().postDelayed(new Runnable() {
            @SuppressLint("NewApi")
			@Override
            public void run() {
            	ticks[temp].setBackground(new ColorDrawable(Color.parseColor("#00ff00")));
            	if((temp+1) < lifeCount)
            		animateHealth(temp + 1);
            }
       }, 50);
	}
	void startAnimation(){
		ArrayList<ObjectAnimator> arrayListObjectAnimators = new ArrayList<ObjectAnimator>();

		for(int i = 0; i < numTicks;  i++){
			ObjectAnimator temp =  ObjectAnimator.ofFloat(ticks[i], "X", (float)tickPos[i][0]);
			arrayListObjectAnimators.add(temp);
			ObjectAnimator temp2 =  ObjectAnimator.ofFloat(ticks[i], "Y", (float)tickPos[i][1]);
			arrayListObjectAnimators.add(temp2);
			ObjectAnimator temp3 =  ObjectAnimator.ofFloat(ticks[i], "Rotation", (float)tickPos[i][2]);
			arrayListObjectAnimators.add(temp3);
		}
		ObjectAnimator[] objectAnimators = arrayListObjectAnimators.toArray(new ObjectAnimator[arrayListObjectAnimators.size()]);
		AnimatorSet animSet = new AnimatorSet();
		animSet.playTogether(objectAnimators);
		animSet.setDuration(600);
		animSet.start();
		animSet.addListener(new Animator.AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				//do nothing
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				//do nothing
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				animateHealth(0);
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				animateHealth(0);
			}
		});
	}
	
	int[] calcPos(int num, double angle){
		int[] rVal = new int[2];
		double radius = radiusRatio * this.radius;
		if(angle < 90){
			angle = Math.toRadians(angle);
			rVal[0] = (int)(centerX + Math.sin(angle) * radius);
			rVal[1] = (int)(centerY - Math.cos(angle) * radius);
		}
		else if(angle < 180){
			angle = 180 - angle;
			angle = Math.toRadians(angle);
			rVal[0] = (int)(centerX + Math.sin(angle) * radius);
			rVal[1] = (int)(centerY + Math.cos(angle) * radius);
		}
		else if(angle < 270){
			angle -= 180;
			angle = Math.toRadians(angle);
			rVal[0] = (int)(centerX - Math.sin(angle) * radius);
			rVal[1] = (int)(centerY + Math.cos(angle) * radius);
		}
		else{
			angle = 360 - angle;
			angle = Math.toRadians(angle);
			rVal[0] = (int)(centerX - Math.sin(angle) * radius);
			rVal[1] = (int)(centerY - Math.cos(angle) * radius);
		}
		rVal[0] += (int)(-tickWidth/2);
		rVal[1] += (int)(-tickHeight/2);
		return rVal;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.badge, menu);
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
	//
	public void textClick(View v){
		
	}
}
