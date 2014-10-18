package co.mide.boilermake;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;
import android.widget.TextView;

@SuppressLint("NewApi")
public class LightUp implements Runnable {
	private ImageButton[] ticks;
	private Badge badge;
	private TextView percentView;
	private float percent;
	private float num;
	public LightUp(ImageButton[] ticks, TextView percentView, Badge b, float percent, float num){
		this.ticks = ticks;
		this.badge = b;
		this.num = num;
		this.percentView = percentView;
		this.percent = percent;
	}
	@Override
	public void run(){
		int count = (int)Math.ceil(num);
		for(int i = 0; i < count; i++){
			//turn on the i-th light
			//badge.animateLight(i);
			//update the percent
			//wait
		}
	}
}
