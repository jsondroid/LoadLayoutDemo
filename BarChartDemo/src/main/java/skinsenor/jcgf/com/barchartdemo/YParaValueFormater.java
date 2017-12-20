package skinsenor.jcgf.com.barchartdemo;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by JCGF-OY on 2017/6/8.
 */

public class YParaValueFormater implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if((int)value%50==0){
            return (int)value+"";
        }
        return "";
    }
}
