package greendao;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by wenbaohe on 2017/11/27.
 */

public class FontsUtils {
    private static Typeface cmtTypeFace;

    /**
     * 非中文内容使用此字体样式（设计需求）设置字体样式
     */
    public static void setCMTFonts(Context context, TextView textView) {
        textView.setTypeface(getCMTTypeface(context));// 设置字体样式
    }

    /**
     * 非中文内容使用此字体样式（设计需求） 获取Typeface
     */
    public static Typeface getCMTTypeface(Context context) {
        if (cmtTypeFace == null) {
            cmtTypeFace = Typeface.createFromAsset(context.getAssets(),
                    "fonts/font_w3.otf");// 根据路径得到Typeface
        }
        return cmtTypeFace;
    }

    /**
     * 设置全局字体样式
     *
     * @param context 上下文
     */
    public static void setAppTypeface(Context context) {
        try {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, getCMTTypeface(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}