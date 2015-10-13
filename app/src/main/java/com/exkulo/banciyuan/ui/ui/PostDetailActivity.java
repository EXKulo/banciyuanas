package com.exkulo.banciyuan.ui.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.exkulo.banciyuan.R;
import com.exkulo.banciyuan.ui.adapter.PostDetailAdapter;
import com.exkulo.banciyuan.ui.data.LogicBus;
import com.exkulo.banciyuan.ui.data.OnFetched;
import com.exkulo.banciyuan.ui.model.PostDetail;
import com.exkulo.banciyuan.ui.utils.ContextAccessor;
import com.exkulo.banciyuan.ui.utils.L;
import com.exkulo.banciyuan.ui.utils.ViewUtils;

public class PostDetailActivity extends BaseActivity {

    public static final String INTENT_POST_URL = "postUrl";
    public static final String INTENT_ART_NAME = "artName";
    private String mArtName = "";
    private String mPostUrl = "";

    private int mDeviceWidth = ViewUtils.getDiviceWidthPixis();
    private int mHeaderPicHeight;

    private int mOverallYScroll = 0;

    private PostDetail mPostDetail = new PostDetail();

    @InjectView(R.id.activity_post_detail_tb) Toolbar mToolbar;
    @InjectView(R.id.activity_post_detail_rv) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        mHeaderPicHeight = (int) (mDeviceWidth / 1.33f);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mArtName = getIntent().getStringExtra(INTENT_ART_NAME);
        mPostUrl = getIntent().getStringExtra(INTENT_POST_URL);
        checkIntentData(mPostDetail, INTENT_POST_URL);
        initToolbar();

        LogicBus.getPostDetail(mPostUrl, new OnFetched() {
            @Override
            public void succeed(Object obj) {
                hideProgressBar();
                mPostDetail = (PostDetail) obj;
                mToolbar.setTitle(mPostDetail.getCn() + "--" + mPostDetail.getCharacter());
                mRecyclerView.setAdapter(
                    new PostDetailAdapter(PostDetailActivity.this, mPostDetail));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(PostDetailActivity.this));
            }

            @Override
            public void fail(String msg, int status) {
                hideProgressBar();
                L.i("fail");
            }
        });
    }

    public void initToolbar() {
        int darkPink = ContextAccessor.getContextAccessor().getColor(R.color.dark_pink);
        final ColorDrawable bgColorDrawable = new ColorDrawable(darkPink);
        //between 0 and 255
        bgColorDrawable.setAlpha(255);
        mToolbar.setBackground(bgColorDrawable);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mOverallYScroll += dy;
                float alpha = (float) mOverallYScroll / (float) mHeaderPicHeight;
                alpha = alpha > 1.0f ? 1.0f : alpha;
                alpha *= 255;
                bgColorDrawable.setAlpha((int) alpha);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_detail, menu);
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
}
