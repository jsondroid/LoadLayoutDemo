package skinsenor.jcgf.com.barchartdemo.wy;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;

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
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import skinsenor.jcgf.com.barchartdemo.R;
import skinsenor.jcgf.com.barchartdemo.YParaValueFormater;

/**
 * 作者：wenbaohe on 2017/1/18.
 */

public class RealtimeChartConfig {

    private WeakReference<Context> weakReference;
    private LineChart lineChart;

    public RealtimeChartConfig(Context context, LineChart lineChart) {
        weakReference = new WeakReference<Context>(context);
        this.lineChart = lineChart;
    }

    /**
     * 初始化图表
     */
    public void initlineChart() {
        if (lineChart != null) {
            lineChart.setNoDataText("");   //没有数据时显示在中央的字符串，参数是String对象
            lineChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
            lineChart.getAxisRight().setEnabled(false);//右边不显示刻度值
            //Interaction with the Chart 图表的交互
            lineChart.setTouchEnabled(true); // 设置是否可以触摸
            lineChart.setDragEnabled(true);// 是否可以拖拽
            lineChart.setScaleEnabled(true);// 是否可以缩放 x和y轴, 默认是true
            lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
            lineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴

            lineChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
            lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
            lineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
            // Chart fling / deceleration
            lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
            lineChart.setDragDecelerationFrictionCoef(0.95f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
            lineChart.setHighlightPerDragEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
            lineChart.setHighlightPerTapEnabled(false);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true


            LineData data = new LineData();
            lineChart.setData(data);

            Legend legend = lineChart.getLegend();
            legend.setEnabled(false);
            setX(lineChart.getXAxis());
            setY(lineChart.getAxisLeft());
            lineChart.invalidate();
        }
    }

    /**
     * 设置Y轴文字不倾斜
     */
    private void setY(YAxis yAxis) {
        yAxis.setLabelCount(300, false);
        yAxis.setAxisLineWidth(1f);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxis.setDrawGridLines(true);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.enableGridDashedLine(10f, 10f, 10f);
        yAxis.setLabelRotationAngle(0);//文字倾斜45度，该设置是修改源码实现
        yAxis.setTextSize(dp2px(11));
        yAxis.setDrawTopYLabelEntry(true);
        yAxis.setDrawZeroLine(true);
        yAxis.setStartAtZero(true);
        yAxis.setAxisMinimum(-150);//从0坐标开始
        yAxis.setAxisMaximum(150);//最大值
        yAxis.setSpaceMin(50);
        yAxis.setSpaceMax(50);
        yAxis.setValueFormatter(new YParaValueFormater());
    }

    private void setX(XAxis xAxis) {
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(true);
        xAxis.enableGridDashedLine(10f, 10f, 10f);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setEnabled(true);
        xAxis.setDrawLabels(true);
        xAxis.setAxisMinimum(0);//从0坐标开始
    }


    public LineDataSet creaLineDataSet(){
        LineDataSet set = new LineDataSet(null, "");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(Color.RED);
        set.setCircleColor(Color.TRANSPARENT);
        set.setLineWidth(1f);
        set.setCircleRadius(0f);
        set.setFillAlpha(65);
        set.setDrawCircleHole(false); //启用圆心
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }


    public void addEntry(float x,float y) {
        LineData data = lineChart.getData();
        if (data != null) {
            ILineDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = creaLineDataSet();
                data.addDataSet(set);
            }
            data.addEntry(new Entry(set.getEntryCount(), y), 0);
//            data.addEntry(new Entry(x, y), 0);
            data.notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.setVisibleXRangeMaximum(60);
//            lineChart.moveViewToX(data.getEntryCount());
            lineChart.moveViewTo(data.getXMax()-7, 55f, YAxis.AxisDependency.LEFT);
        }
    }

    private int dp2px(float dpValue) {
        return (int) (dpValue * (weakReference.get().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
