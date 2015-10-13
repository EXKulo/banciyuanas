package com.exkulo.banciyuan.ui.customwidget;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.exkulo.banciyuan.R;

/**
 * Created by exkulo on 10/5/2015.
 */
public class EasyMaterialSearchBar extends FrameLayout {

    @InjectView(R.id.activity_main_search_bar_ed) EditText mEditText;
    @InjectView(R.id.activity_main_search_bar_icon) ImageView mImageView;

    public interface OnSearchIconClickListener {
        public void onSearchIconClicked(String key);
    }

    public void setOnSearchIconClickListener(OnSearchIconClickListener onSearchIconClickListener) {
        mOnSearchIconClickListener = onSearchIconClickListener;
    }

    private OnSearchIconClickListener mOnSearchIconClickListener;

    public EasyMaterialSearchBar(Context context) {
        this(context, null);
    }

    public EasyMaterialSearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyMaterialSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View root = LayoutInflater.from(context).inflate(R.layout.activity_main_search_bar, this);
        ButterKnife.inject(this, root);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSearchIconClickListener != null) {
                    mOnSearchIconClickListener.onSearchIconClicked(mEditText.getText().toString());
                    mEditText.setText("");
                }
            }
        });
    }

    public void setInputType(int inputType) {
        mEditText.setInputType(inputType);
    }
}
