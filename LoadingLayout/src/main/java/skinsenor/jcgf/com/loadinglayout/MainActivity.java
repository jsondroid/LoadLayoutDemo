package skinsenor.jcgf.com.loadinglayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import skinsenor.jcgf.com.loadlayoutlibrary.LoadingFrameLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

//    boolean b=true;
//    public void onclickView(View view){
//        activity_main.showAnimLoadingView("正在加载中...");
//        activity_main.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                activity_main.showErroView("网络出错了！");
//            }
//        },1000*5);
//
//        activity_main.setOnReLoadingListener(new LoadingFrameLayout.OnReLoadingListener() {
//            @Override
//            public void onLoading(View btnview) {
//                activity_main.showImgLoadingView("正在加载中...");
//            }
//        });
//    }
}
