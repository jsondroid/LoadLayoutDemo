package skinsenor.jcgf.com.demolayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import skinsenor.jcgf.com.loadlayoutlibrary.LoadingFrameLayout;

public class MainActivity extends AppCompatActivity {
    LoadingFrameLayout activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity_main = (LoadingFrameLayout) findViewById(R.id.activity_main);

    }

    boolean b = true;
    public void onclickView(View view) {
        activity_main.showAnimaLayout("正在加载中...");
        activity_main.postDelayed(new Runnable() {
            @Override
            public void run() {
                activity_main.showErrorLayout("网络出错了！");
            }
        }, 1000 * 5);

        activity_main.setOnReLoadingListener(new LoadingFrameLayout.OnReLoadingListener() {
            @Override
            public void onLoading(View btnview) {
                activity_main.showImageLayout("拼命加载中...");
                activity_main.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activity_main.showContentView();
                    }
                },1000*5);
            }
        });
    }
}
