package com.exkulo.banciyuan.ui.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import com.exkulo.banciyuan.R;
import com.exkulo.banciyuan.ui.adapter.MainPageAdapter;
import com.exkulo.banciyuan.ui.data.LogicBus;
import com.exkulo.banciyuan.ui.data.OnFetched;
import com.exkulo.banciyuan.ui.data.asynctask.LoginTask;
import com.exkulo.banciyuan.ui.model.HomePageEntity;
import com.exkulo.banciyuan.ui.utils.ViewUtils;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.activity_main_srl) SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.activity_main_rv) RecyclerView mRecyclerView;
    @InjectView(R.id.activity_main_dl) DrawerLayout mDrawerLayout;

    @InjectView(R.id.activity_main_tb) Toolbar mToolbar;
    ActionBarDrawerToggle mActionBarDrawerToggle;

    private HomePageEntity mEntity;

    private long mFirstClickTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolbar.setTitle("首页");
        ///////////////////////////////////////////////////////
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
            R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        ///////////////////////////////////////////////////////
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEntity = new HomePageEntity();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refresh();

        new LoginTask().execute(new Bundle());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            //super.onBackPressed();
            long currTime = System.currentTimeMillis();
            if(mFirstClickTime == 0L) {
                mFirstClickTime = currTime;
                ViewUtils.t(context(), "再按一次退出");
            } else {
                if(System.currentTimeMillis() - mFirstClickTime < 1000) {
                    ViewUtils.dismissT();
                    super.onBackPressed();
                } else {
                    mFirstClickTime = currTime;
                    ViewUtils.t(context(), "再按一次退出");
                }
            }
        }
    }

    private void refresh() {
        LogicBus.getHotestPost(new OnFetched() {
            @Override
            public void succeed(Object obj) {
                mSwipeRefreshLayout.setRefreshing(false);
                hideProgressBar();
                HomePageEntity entity = (HomePageEntity) obj;
                mRecyclerView.setAdapter(new MainPageAdapter(MainActivity.this, entity));
            }

            @Override
            public void fail(String msg, int status) {
                mSwipeRefreshLayout.setRefreshing(false);
                hideProgressBar();
                ViewUtils.t(MainActivity.this, "失败，失败");
            }
        });
    }
}
