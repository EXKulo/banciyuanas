package com.exkulo.banciyuan.ui.customwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.exkulo.banciyuan.R;

/**
 * Created by exkulo on 9/18/2015.
 */
public class ExRelativeLayout extends RelativeLayout {

    private int mTotalWidth, mTotalHeight;

    private float mAspectRatio = -1;

    public ExRelativeLayout(Context context) {
        this(context, null);
    }

    public ExRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView);
        mAspectRatio = a.getFloat(R.styleable.RoundedImageView_aspect_ratio, -1);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTotalWidth = MeasureSpec.getSize(widthMeasureSpec);
        mTotalHeight = MeasureSpec.getSize(heightMeasureSpec);
        //if (mAspectRatio != -1) {
        //    mTotalHeight = (int) (mTotalWidth / mAspectRatio);
        //    setMeasuredDimension(mTotalWidth, mTotalHeight);
        //}
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setAspectRatio(float ratio) {
        mAspectRatio = ratio;
        if (mAspectRatio != -1) {
            mTotalHeight = (int) (mTotalWidth / mAspectRatio);
            getLayoutParams().height = mTotalHeight;
        }
    }
}
