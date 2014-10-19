package co.mide.boilermake;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyImageView extends ImageView{

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	private Badge badge;
	boolean notMove = false;
	public MyImageView(Context context) {
		super(context);
	}
	@Override
	public boolean performClick(){
		boolean b = super.performClick();
		return b;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//badge.bigClick();
	    int x = (int)event.getX();
	    int y = (int)event.getY();
	    int i = event.getAction();
	    System.out.println(i);
	    if((i == MotionEvent.ACTION_DOWN)){
	    	notMove = true;
	    	System.out.println("Down");
	    }else if((i == MotionEvent.ACTION_MOVE)){
	    	notMove = false;
	    	System.out.println("Move");
	    }
    	if((notMove)&&(Math.hypot(x-badge.getCentX(), y-badge.getCentY()) < 0.5*badge.getRad() )){
	    	System.out.println("Miracle");
    		badge.bigClick();
    		performClick();
    	}
	return true;
	}
	void setBadge(Badge b){
		badge = b;
	}
}
