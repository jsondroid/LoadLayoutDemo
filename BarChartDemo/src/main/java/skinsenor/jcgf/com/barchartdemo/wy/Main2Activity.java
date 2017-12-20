package skinsenor.jcgf.com.barchartdemo.wy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import skinsenor.jcgf.com.barchartdemo.R;

public class Main2Activity extends AppCompatActivity {


    RealtimeChartConfig realtimeChartConfig;
    private LineChart mChart;
    private  ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mChart= (LineChart) findViewById(R.id.linechart);
        imageview= (ImageView) findViewById(R.id.imageview);
        realtimeChartConfig=new RealtimeChartConfig(this,mChart);
        realtimeChartConfig.initlineChart();

    }


    private float x=0.1f;
    public void addEntry(View view){
//        realtimeChartConfig.addEntry(x,(float) ((Math.random() * 40) + 30f));
//        x=x+0.1f;
        imageview.setImageBitmap(getStorePic("id","img"));
    }
    public void clearValues(View view){
//        mChart.clearValues();
//        imageview.setImageBitmap(mChart.getChartBitmap());
        storePic("id","img",mChart.getChartBitmap());
        Toast.makeText(this, "Chart cleared!", Toast.LENGTH_SHORT).show();
    }
    public void feedMultiple(View view){
        feedMultiple();
    }



    private Thread thread;
    private boolean b=false;
    private void feedMultiple() {

        if (thread != null)
            thread.interrupt();

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                float y=0;
                if(b){
                   y= (float) ((Math.random() * 40) + 30f);
                    b=false;
                }else{
                    y= -(float) ((Math.random() * 40) + 30f);
                    b=true;
                }
                realtimeChartConfig.addEntry(0,y);
            }
        };

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {

                    // Don't generate garbage runnables inside the loop.
                    runOnUiThread(runnable);

                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }




    public void storePic(String tabid, String key, Bitmap bitmap) {
        if(tabid == null || key == null || tabid.isEmpty() || key.isEmpty() || bitmap == null) {
            return;
        }
        FileOutputStream fos = null;
        try {
            fos = this.openFileOutput(tabid + "_" + key, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            Log.e("storePic", "storePic FileNotFoundException e = " +e);
        } finally {
            if(fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    Log.e("storePic",  "storePic IOException e = " +e);
                }
            }
        }
    }

    public Bitmap getStorePic(String tabid, String key) {
        if(tabid == null || key == null || tabid.isEmpty() || key.isEmpty()) {
            return null;
        }
        FileInputStream fin = null;
        Bitmap bitmap = null;
        try {
            fin = openFileInput(tabid + "_" + key);
            bitmap = BitmapFactory.decodeStream(fin);
        } catch (FileNotFoundException e) {
            Log.e("storePic", "getStorePic FileNotFoundException e = " + e);
        }
        return bitmap;
    }
}
