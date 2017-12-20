package skinsenor.jcgf.com.barchartdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 作者：wenbaohe on 2017/1/18.
 */

public class BarChartConfig {

    private WeakReference<Context> weakReference;
    private BarChart barChart;

    public BarChartConfig(Context context, BarChart barChart) {
        weakReference = new WeakReference<Context>(context);
        this.barChart = barChart;
    }

    /**
     * 初始化图表
     */
    public void initBarChart() {
        if (barChart != null) {
            barChart.setNoDataText("");   //没有数据时显示在中央的字符串，参数是String对象
            barChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
            barChart.getAxisRight().setEnabled(false);//右边不显示刻度值
            //Interaction with the Chart 图表的交互
            barChart.setTouchEnabled(false); // 设置是否可以触摸
            barChart.setDragEnabled(false);// 是否可以拖拽
            barChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
            barChart.setScaleXEnabled(false); //是否可以缩放 仅x轴
            barChart.setScaleYEnabled(false); //是否可以缩放 仅y轴

            barChart.setPinchZoom(false);  //设置x轴和y轴能否同时缩放。默认是否
            barChart.setDoubleTapToZoomEnabled(false);//设置是否可以通过双击屏幕放大图表。默认是true
            barChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
            // Chart fling / deceleration
            barChart.setDragDecelerationEnabled(false);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
            barChart.setDragDecelerationFrictionCoef(0.95f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
            barChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
            barChart.setHighlightPerTapEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
            barChart.setPinchZoom(false);
            Legend legend = barChart.getLegend();
            legend.setEnabled(false);

            setX(barChart.getXAxis());
            setY(barChart.getAxisLeft());

        }
    }

    /**
     * 设置Y轴文字不倾斜
     */
    private void setY(YAxis yAxis) {
//        yAxis.setLeftYtopDes("%");
        yAxis.setLabelCount(10, false);
        yAxis.setAxisLineWidth(1f);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.enableGridDashedLine(10f, 10f, 10f);
        yAxis.setLabelRotationAngle(0);//文字倾斜45度，该设置是修改源码实现
        yAxis.setTextSize(dp2px(15));
        yAxis.setDrawTopYLabelEntry(true);
        yAxis.setDrawZeroLine(true);
        yAxis.setStartAtZero(true);
        yAxis.setAxisMinimum(0);//从0坐标开始
        yAxis.setAxisMaximum(110);//最大值
        yAxis.setValueFormatter(new YParaValueFormater());

        for(int i=10;i<120;i=i+10){
            LimitLine limitLine=new LimitLine(i);
            limitLine.enableDashedLine(dp2px(10), dp2px(10), dp2px(10));
            limitLine.setLineColor(Color.argb(50, Color.red(Color.GRAY), Color.green(Color.GRAY), Color.blue(Color.GRAY)));
            yAxis.addLimitLine(limitLine);
        }
    }

    private void setX(XAxis xAxis) {
        xAxis.setLabelCount(8, false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setAxisLineWidth(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(11f);
        xAxis.setAxisMinimum(1);
        xAxis.setDescription("");
        xAxis.setSpaceMin(1);//setLabelsToSkip(1);    //设置坐标相隔多少，参数是int类型
        xAxis.setSpaceMin(1);//resetLabelsToSkip();   //将自动计算坐标相隔多少
        xAxis.setAvoidFirstLastClipping(false);
//        xAxis.setGranularity(100);
    }


    /**
     * 设置每条柱状的样式
     */
    private BarDataSet setBarStyle(BarDataSet barDataSet, int colorid) {
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);     //以左边坐标轴为准 还是以右边坐标轴为基准
        barDataSet.setBarBorderWidth(0f);//线的大小
        barDataSet.setColor(ContextCompat.getColor(weakReference.get(), colorid),255);
        barDataSet.setDrawValues(false);
        barDataSet.setHighLightColor(Color.TRANSPARENT);
        barDataSet.setHighlightEnabled(true);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(24);
//        barDataSet.setValueTypeface();
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "" + (int) value;
            }
        });
        return barDataSet;
    }

    public void setData(int value1, int value2, int value3) {
        ArrayList<BarEntry> barEntries1 = new ArrayList<>();
        barEntries1.add(new BarEntry(3, value1));

        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(5, value2));

        ArrayList<BarEntry> barEntries3 = new ArrayList<>();
        barEntries3.add(new BarEntry(7, value3));

        ArrayList<BarEntry> barEntries4 = new ArrayList<>();
        barEntries4.add(new BarEntry(8, 0));
        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "");
        BarDataSet barDataSet3 = new BarDataSet(barEntries3, "");
        BarDataSet barDataSet4 = new BarDataSet(barEntries4, "");

        setBarStyle(barDataSet1, R.color.color_FF87BAED);
        setBarStyle(barDataSet2, R.color.color_097aec);
        setBarStyle(barDataSet3, R.color.color_559ce2);
        setBarStyle(barDataSet4, R.color.color_transparent);

        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3);
        barChart.setData(barData);
        barChart.setDrawBarShadow(false);
        barChart.setVisibleXRangeMinimum(8);
        barChart.animateY(1500);//数据显示动画，从左往右依次显示
    }


    private int dp2px(float dpValue) {
        return (int) (dpValue * (weakReference.get().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
