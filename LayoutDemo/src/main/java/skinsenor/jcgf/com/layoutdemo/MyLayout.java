package skinsenor.jcgf.com.layoutdemo;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by wenbaohe on 2017/12/20.
 */

public class MyLayout extends RelativeLayout implements GestureDetector.OnGestureListener {
    private
    int verticalMinDistance = 20;

    private
    int minVelocity = 0;

    private MyLayoutCallBack myLayoutCallBack;
    private GestureDetector gestureDetector;
    Context context;

    public void setCallBack(MyLayoutCallBack myLayoutCallBack) {
        this.myLayoutCallBack = myLayoutCallBack;
    }

    public MyLayout(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, this);
        this.context = context;
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        rectF=new RectF(0,0,getWidth()/4,getHeight());
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(rectF.contains(event.getX(),event.getY())){
                this.gestureDetector.onTouchEvent(event);
                return  true;
            }
        }
        return super.onTouchEvent(event);
    }

    RectF rectF;
    boolean b=false;
    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("pingan", "onDown");
        verticalMinDistance= (int) rectF.width();
        if(rectF.contains(e.getX(),e.getY())){
            b=true;
        }else{
            b=false;
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        Log.d("pingan", "onScroll" + distanceX + "distanceY:" + distanceY);

//        if (distanceX < -verticalMinDistance) {
//            Log.d("pingan", "向右手势");
//            if(myLayoutCallBack!=null){
//                myLayoutCallBack.scrollByX(20);
//            }
//        } else if (distanceX > verticalMinDistance){
//            Log.d("pingan", "向左手势");
//            if(myLayoutCallBack!=null){
//                myLayoutCallBack.scrollByX(-20);
//            }
//
//        }else if (distanceY <- verticalMinDistance) {
//            Log.d("pingan", "向下手势");
//            if(myLayoutCallBack!=null){
//                myLayoutCallBack.scrollByY(20);
//            }
//        } else if (distanceY > verticalMinDistance ) {
//
//            Log.d("pingan", "向上手势");
//            if(myLayoutCallBack!=null){
//                myLayoutCallBack.scrollByY(-20);
//            }
//        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("pingan", "onFling e1-->"+e1.getX()+" e1-->"+e2.getX()+"  velocityX--->"+velocityX+" velocityY-->"+velocityY);
        if ((e1.getX() - e2.getX()>0)&&(Math.abs(e1.getX() - e2.getX()) < verticalMinDistance) && Math.abs(velocityX) > minVelocity) {
            Log.d("pingan", "向左手势");
            if(b){
                myLayoutCallBack.scrollToLeft((int) (e1.getX() - e2.getX()));
            }
        } else if ((e1.getX() - e2.getX()<0)&&(Math.abs(e1.getX() - e2.getX()) < verticalMinDistance)&& Math.abs(velocityX) > minVelocity) {
            Log.d("pingan", "向右手势");
            if(b){
                myLayoutCallBack.scrollToRigth((int) (e1.getX() - e2.getX()));
            }
        }
        return true;
    }
}