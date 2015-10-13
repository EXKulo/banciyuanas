package com.exkulo.banciyuan.ui.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.exkulo.banciyuan.R;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

import static com.exkulo.banciyuan.ui.utils.ViewUtils.*;

/**
 * Created by exkulo on 9/15/2015.
 */
public class BaseActivity extends AppCompatActivity {

    final protected BaseActivity context() {
        return this;
    }

    private MaterialProgressBar mMaterialProgressBar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
        LayoutInflater layoutInflater = LayoutInflater.from(context());
        mMaterialProgressBar =
            (MaterialProgressBar) layoutInflater.inflate(R.layout.material_progress_bar, null)
                                                .findViewById(R.id.app_material_progress_bar);
        FrameLayout.LayoutParams layoutParams =
            new FrameLayout.LayoutParams(dip2px(48), dip2px(48));
        layoutParams.gravity = Gravity.CENTER;
        mMaterialProgressBar.setLayoutParams(layoutParams);
        content.addView(mMaterialProgressBar);
    }

    protected void hideProgressBar() {
        mMaterialProgressBar.setVisibility(View.GONE);
    }

    protected void showProgressBar() {
        mMaterialProgressBar.setVisibility(View.VISIBLE);
    }

    protected void checkIntentData(Object object, String key) {
        if (object == null) {
            throw new IllegalStateException(
                "启动这个Activity的时候没有传入必要的数据。" + "\nkey = " + key + " , 类型 = " + object.getClass()
                                                                                    .getName());
        }
    }

    protected void checkIntentData(int i, String key) {
        if (i == -1) {
            throw new IllegalStateException(
                "启动这个Activity的时候没有传入必要的数据。" + "\nkey = " + key + " , 类型 = int");
        }
    }
}
