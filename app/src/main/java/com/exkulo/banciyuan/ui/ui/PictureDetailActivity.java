package com.exkulo.banciyuan.ui.ui;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.exkulo.banciyuan.R;
import com.exkulo.banciyuan.ui.data.OnFetched;
import com.exkulo.banciyuan.ui.data.asynctask.SavePicTask;
import com.exkulo.banciyuan.ui.utils.ImageLoader;
import com.exkulo.banciyuan.ui.utils.ViewUtils;
import com.squareup.picasso.Callback;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureDetailActivity extends BaseActivity {

    public static final String DATA_PIC_URL = "pic_url";
    public static final String DATA_CN = "cn";

    @InjectView(R.id.activity_picture_detail_iv) ImageView mMainIv;
    @InjectView(R.id.activity_picture_detail_fab) FloatingActionButton mFab;
    @InjectView(R.id.picture_detail_indeterminate_progress_library) MaterialProgressBar
        mProgressBar;
    PhotoViewAttacher mPhotoViewAttacher;

    String mPicUrl;
    String mCn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.inject(this);
        mPicUrl = getIntent().getStringExtra(DATA_PIC_URL);
        mCn = getIntent().getStringExtra(DATA_CN);
        checkIntentData(mCn, DATA_CN);
        mPhotoViewAttacher = new PhotoViewAttacher(mMainIv, true);
        mPhotoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                onBackPressed();
            }
        });
        ImageLoader.show(mMainIv, mPicUrl, new Callback() {
            @Override
            public void onSuccess() {
                hideProgressBar();
                mPhotoViewAttacher.update();
            }

            @Override
            public void onError() {

            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePicture();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_picture_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void savePicture() {
        mFab.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        new SavePicTask(new OnFetched() {
            @Override
            public void succeed(Object obj) {
                ViewUtils.t(PictureDetailActivity.this, "成功下载啦主人~");
                mFab.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void fail(String msg, int status) {
                ViewUtils.t(PictureDetailActivity.this, "sad..看起来好像失败了呢");
            }
        }).execute(mPicUrl, mCn);
    }
}
