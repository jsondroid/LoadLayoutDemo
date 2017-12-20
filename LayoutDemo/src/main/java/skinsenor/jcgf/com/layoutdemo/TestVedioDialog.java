package skinsenor.jcgf.com.layoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by wenbaohe on 2017/12/12.
 */

public class TestVedioDialog extends AppCompatActivity {

    private LinearLayout colse_layout;
    private Button test_btngettest;

    private int PUTREQ_KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setFinishOnTouchOutside(true);//点击外部关闭
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
    }

    public void clickOut(View view) {
        finish();
        //关闭窗体动画显示
        this.overridePendingTransition(0,R.anim.top_out);
    }

    @Override
    protected void onStart() {
        super.onStart();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width =(int)(d.getWidth()*0.8f); //设置dialog的宽度为当前手机屏幕的宽度
        lp.height =(int)( d.getHeight()*0.88f); //设置dialog的宽度为当前手机屏幕的宽度
        lp.alpha = 1f;
        lp.dimAmount = 0f;//背景透明
        getWindow().setAttributes(lp);
    }
}
