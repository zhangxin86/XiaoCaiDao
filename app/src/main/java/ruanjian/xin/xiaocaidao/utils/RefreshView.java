package ruanjian.xin.xiaocaidao.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import ruanjian.xin.xiaocaidao.R;

/**
 * Created by xin on 2016/11/22.
 */

public class RefreshView extends ScrollView{
    private final static int HORIZONTAL_SCROLL_INSTANCE = 100;
    private final static int VERTICAL_SCROLL_INSTANCE = 80;
    //刷新接口
    private RefrshListener refrshListener;

    private boolean isScrolledToTop = true;
    public boolean isStartScroll = false;
    public boolean isFreshCommplete = true;
    private boolean isFreshing = false;

    //动画相关：
    private ImageView ivWheel1,ivWheel2;    //轮组图片组件
    private ImageView ivRider;  //骑手图片组件
    private ImageView ivSun,ivBack1,ivBack2;    //太阳、背景图片1、背景图片2
    private Animation wheelAnimation,sunAnimation;  //轮子、太阳动画
    private Animation backAnimation1,backAnimation2;    //两张背景图动画
    private Animation scrollBack;       //回滚动画

    //下拉后添加框架：
    private RelativeLayout refresh;
    private RefreshView Sv_refresh;
    private LinearLayout.LayoutParams params;
    private int PreHeight = 400;


    //刷新状态：
    private static final int STATE_PREFRESH = 1;
    private static final int STATE_FRESHING = 2;
    private static final int STATE_FRESHED = 3;

    //事件坐标
    private float startX = 0;
    private float startY = 0;
    private float horizontal_scrollinstance = 0;
    private float vertical_scrollinstance = 0;

    public RefreshView(Context context) {
        super(context);
        Log.i("tag","调用了构造方法1");
        //init(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("tag","调用了构造方法2");
        //init(context);
        //Log.i("tag",context.toString());
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("tag","调用了构造方法3");
        //init(context);
    }

    //初始化函数：
    public void init(){
        setOverScrollMode(OVER_SCROLL_NEVER);
        refresh = (RelativeLayout)findViewById(R.id.refresh);
        Sv_refresh = (RefreshView)findViewById(R.id.Sv_refresh);
        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        orignalY = refresh.getY();
//        Log.i("tag","开始坐标是"+orignalY);
//        Log.i("tag", "preheight" + PreHeight);
//        if(Sv_refresh==null){
//            Log.i("tag","header是空的！！");
//        }
//        if(refresh==null){
//            Log.i("tag","refresh是空的！！");
//        }
        getView();
    }

    protected void getView(){
        //获取头布局图片组件
        ivRider = (ImageView)refresh.findViewById(R.id.iv_rider);
        ivSun = (ImageView)refresh.findViewById(R.id.ivsun);
        ivWheel1 = (ImageView) refresh.findViewById(R.id.wheel1);
        ivWheel2 = (ImageView)refresh.findViewById(R.id.wheel2);
        ivBack1 = (ImageView) refresh.findViewById(R.id.iv_back1);
        ivBack2 = (ImageView)refresh.findViewById(R.id.iv_back2);

        //获取动画
        wheelAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.tip);
        sunAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.tip1);
        LinearInterpolator lir = new LinearInterpolator();
        wheelAnimation.setInterpolator(lir);
        sunAnimation.setInterpolator(lir);

        backAnimation1 = AnimationUtils.loadAnimation(getContext(), R.anim.a);
        backAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.b);
        // Log.i("tag","全部完成");
    }

    public void ChangeHeaderState(int state){
        switch (state){
            case STATE_PREFRESH:
                break;
            case STATE_FRESHING:
                startAnim();
                break;
            case STATE_FRESHED:
                params.setMargins(0, -(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        105, getResources().getDisplayMetrics()), 0, 0);
                refresh.setLayoutParams(params);
                stopAnim();
                break;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        getView();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(isScrolledToTop) {
                    startX = ev.getX();
                    startY = ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                horizontal_scrollinstance = ev.getX()-startX;
                vertical_scrollinstance = ev.getY()-startY;
                Log.i("tag","滑动距离是"+vertical_scrollinstance);
                Log.i("tag","ev.gety="+ev.getY());
                Log.i("tag","startY=="+startY);
                if(scrollBack != null)
                    scrollBack.cancel();
                if(vertical_scrollinstance>VERTICAL_SCROLL_INSTANCE&&isFreshCommplete
                        &&horizontal_scrollinstance<HORIZONTAL_SCROLL_INSTANCE) {
                    if (!isStartScroll) startAnim();
                    int instance = (int) ((vertical_scrollinstance-200) * 0.1f);
                    params.setMargins(0, instance, 0, 0);
                    refresh.setLayoutParams(params);
                    isStartScroll = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isStartScroll){
                    params.setMargins(0, 0, 0, 0);
                    refresh.setLayoutParams(params);
                    startBackAnim();
                    isStartScroll = false;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if(scrollY==0){
            isScrolledToTop = clampedY;
        }else{
            isScrolledToTop = false;
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    protected void startBackAnim(){
        params.setMargins(0,0,0, 0);
        refresh.setLayoutParams(params);
        scrollBack = new TranslateAnimation(0,0,refresh.getY(),0);
        scrollBack.setInterpolator(new AccelerateInterpolator());
        scrollBack.setDuration(100);
        scrollBack.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(isFreshCommplete){
                    ChangeHeaderState(STATE_FRESHING);
                    refrshListener.onRefresh();
                    isFreshCommplete = false;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //isFreshCommplete = false;
            }

        });
        Sv_refresh.setAnimation(scrollBack);
        Sv_refresh.startAnim();
    }

    /**
     * 开启动画
     */
    public void startAnim(){
        //header.startAnimation(pull);
        ivBack1.startAnimation(backAnimation1);
        ivBack2.startAnimation(backAnimation2);
        ivSun.startAnimation(sunAnimation);
        ivWheel1.startAnimation(wheelAnimation);
        ivWheel2.startAnimation(wheelAnimation);
    }

    /**
     * 关闭动画
     */
    public void stopAnim(){
        ivBack1.clearAnimation();
        ivBack2.clearAnimation();
        ivSun.clearAnimation();
        ivWheel1.clearAnimation();
        ivWheel2.clearAnimation();
    }

    //刷新接口：
    public interface RefrshListener{
        void onRefresh();
    }
    //回调接口：
    public void setRefrshListener(RefrshListener listener){
        refrshListener = listener;
    }
}
