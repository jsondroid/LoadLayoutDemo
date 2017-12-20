package skinsenor.jcgf.com.loadlayoutlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wenbaohe on 2017/12/18.
 */

public class LoadingFrameLayout extends FrameLayout {

    /**
     * 第一种加载不带动画的
     */
    private View imgLoadView;
    private TextView tv_imgload;
    /**
     * 动画加载
     */
    private ImageView anim_load;
    private View animLoadView;
    private String mAnimText;
    private TextView tvanim_text;
    private Animation animRotate;

    /**
     * 加载失败
     */
    private View mErrorView;
    private TextView error_tv;
    private ImageView error_img;
    private Button error_btn;

    /**
     * 没有数据
     */
    private View mEmptyView;
    private TextView empty_tv;
    private ImageView empty_img;
    private Button empty_btn;
    private boolean isShowEmptyLayout=false;

    // 用以存储控件显示状态
    private ArrayMap<View, Integer> mVisibilityMap = new ArrayMap<>();


    public LoadingFrameLayout(Context context) {
        this(context, null);
    }

    public LoadingFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingFrameLayout, defStyleAttr, 0);

        int imgloadviewId = ta.getResourceId(R.styleable.LoadingFrameLayout_imgLoadView, 0);
        int animloadviewId = ta.getResourceId(R.styleable.LoadingFrameLayout_animLoadView, 0);
        int erroviewId = ta.getResourceId(R.styleable.LoadingFrameLayout_mErrorView, 0);
        int emptyviewId = ta.getResourceId(R.styleable.LoadingFrameLayout_mEmptyView, 0);
        isShowEmptyLayout = ta.getBoolean(R.styleable.LoadingFrameLayout_isShowEmptyLayout, false);
        Drawable emptyDrawableId = ta.getDrawable(R.styleable.LoadingFrameLayout_emptyDrawable);

        if (imgloadviewId != 0) {
            try {
                imgLoadView = LayoutInflater.from(context).inflate(imgloadviewId, null);
                tv_imgload= (TextView)imgLoadView.findViewById(R.id.imgtv_load);
                mVisibilityMap.put(imgLoadView,View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (animloadviewId != 0) {
            try {
                animLoadView = LayoutInflater.from(context).inflate(animloadviewId, null);
                animRotate = AnimationUtils.loadAnimation(context,R.anim.load_animation);
                anim_load = (ImageView)animLoadView.findViewById(R.id.anim_load);
                tvanim_text= (TextView)animLoadView.findViewById(R.id.tv_animload);
                mVisibilityMap.put(animLoadView,View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (erroviewId != 0) {
            try {
                mErrorView = LayoutInflater.from(context).inflate(erroviewId, null);
                error_tv= (TextView)mErrorView.findViewById(R.id.tv_error);
                error_img= (ImageView)mErrorView.findViewById(R.id.img);
                error_btn= (Button)mErrorView.findViewById(R.id.button);
                error_btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onReLoadingListener!=null){
                            onReLoadingListener.onLoading(v);
                        }
                    }
                });
                mVisibilityMap.put(mErrorView,View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (emptyviewId != 0) {
            try {
                mEmptyView = LayoutInflater.from(context).inflate(emptyviewId, null);
                empty_tv= (TextView)mEmptyView.findViewById(R.id.tv_error);
                empty_img= (ImageView)mEmptyView.findViewById(R.id.img);
                if(!isInEditMode()){
                    ResCompat.setBackground(empty_img,emptyDrawableId);
                }
                empty_btn= (Button)mEmptyView.findViewById(R.id.button);
                empty_btn.setVisibility(GONE);
                mVisibilityMap.put(mEmptyView,View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ta.recycle();


    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildAt(0)==null||isShowEmptyLayout){
            showEmptyLayout("暂无数据！");
        }
    }

    private void addview(View view){
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(view, params);
    }



    public void showImageLayout(String text){
        setLoadView(imgLoadView);
        tv_imgload.setText(TextUtils.isEmpty(text)?"":text);
    }

    public void showAnimaLayout(String text){
        setLoadView(animLoadView);
        anim_load.startAnimation(animRotate);
        tvanim_text.setText(TextUtils.isEmpty(text)?"":text);
    }

    public void showErrorLayout(String text){
        setLoadView(mErrorView);
        error_tv.setText(TextUtils.isEmpty(text)?"":text);
    }

    public void showEmptyLayout(String text){
        setLoadView(mEmptyView);
        empty_tv.setText(TextUtils.isEmpty(text)?"":text);
    }

    private void setLoadView(View loadView){
        for (View view:mVisibilityMap.keySet()){
            if(view==loadView){
                addview(view);
                view.setVisibility(VISIBLE);
                mVisibilityMap.put(view,VISIBLE);
            }else{
                if(mVisibilityMap.get(view)==View.VISIBLE){
                    removeView(view);
                }
            }
        }

        if(!mVisibilityMap.containsKey(getChildAt(0))&&isShowEmptyLayout) {
            getChildAt(0).setVisibility(GONE);
        }
    }

    public void showContentView(){
        if(getChildAt(0)!=null){
            getChildAt(0).setVisibility(VISIBLE);
            for(View view:mVisibilityMap.keySet()){
               if( view.getVisibility()==VISIBLE){
                   removeView(view);
                   mVisibilityMap.put(view,GONE);
               }
            }
        }
    }



    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animRotate.cancel();
        if(onReLoadingListener!=null){
            onReLoadingListener=null;
        }
        mVisibilityMap.clear();
    }

    private OnReLoadingListener onReLoadingListener;

    public void setOnReLoadingListener(OnReLoadingListener onReLoadingListener) {
        this.onReLoadingListener = onReLoadingListener;
    }

    public interface OnReLoadingListener{
        public void onLoading(View btnview);
    }
}
