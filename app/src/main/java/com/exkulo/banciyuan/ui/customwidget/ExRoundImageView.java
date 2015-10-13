package com.exkulo.banciyuan.ui.customwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.exkulo.banciyuan.R;

/**
 * Created by exkulo on 9/15/2015.
 */
public class ExRoundImageView extends ImageView {
    public static final String TAG = "tag_my_view";

    private Context mContext;
    private Paint mBitPaint;
    private PorterDuffXfermode mXfermode;

    private int mTotalWidth, mTotalHeight;
    private float mAspectRatio;

    private int mRadius;

    public ExRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RatioViewGroup);
        mAspectRatio = a.getFloat(R.styleable.RatioViewGroup_aspect_ratio, -1);
        // 设置paint
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);// 设置波纹
        mBitPaint.setDither(true);// 设置抖动
        // 设置叠加层
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTotalWidth = MeasureSpec.getSize(widthMeasureSpec);
        if(mAspectRatio == -1) {
            mTotalHeight =
                MeasureSpec.getSize(heightMeasureSpec);
            return;
        } else {
            mTotalHeight = (int) (mTotalWidth / mAspectRatio);
            setMeasuredDimension(mTotalWidth, mTotalHeight);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    private void log(String msg) {
        Log.v(TAG, msg);
    }


    public void setAspectRatio(float ratio) {
        setMeasuredDimension(mTotalWidth, (int) (mTotalWidth / ratio));
    }
}
