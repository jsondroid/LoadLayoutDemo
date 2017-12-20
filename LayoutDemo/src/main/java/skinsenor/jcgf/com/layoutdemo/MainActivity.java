package skinsenor.jcgf.com.layoutdemo;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    LinearLayout linearLayout;
    MyLayout activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        activity_main = (MyLayout) findViewById(R.id.activity_main);

//        LayoutTransition lt = new LayoutTransition();
//        lt.disableTransitionType(LayoutTransition.DISAPPEARING);
//        linearLayout.setLayoutTransition(lt);


        activity_main.setCallBack(new MyLayoutCallBack() {
            @Override
            public void scrollToLeft(int x) {
                linearLayout.setVisibility(View.GONE);
                // 向左边移出
                linearLayout.setAnimation(AnimationUtils.makeOutAnimation(MainActivity.this, false));
            }

            @Override
            public void scrollToRigth(int y) {
                linearLayout.setVisibility(View.VISIBLE);
                // 向左边移入
                linearLayout.setAnimation(AnimationUtils.makeInAnimation(MainActivity.this, true));
            }
        });

    }


    public void clickOut(View view) {
        linearLayout.setVisibility(View.GONE);
        // 向左边移出
        linearLayout.setAnimation(AnimationUtils.makeOutAnimation(this, false));

        startActivity(new Intent(this,TestVedioDialog.class));
        overridePendingTransition(R.anim.top_in,0);
    }

    public void clickIn(View view) {
        linearLayout.setVisibility(View.VISIBLE);
        // 向左边移入
        linearLayout.setAnimation(AnimationUtils.makeInAnimation(this, true));
    }
}
