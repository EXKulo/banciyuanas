package com.exkulo.banciyuan.ui.ui;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.exkulo.banciyuan.R;
import com.exkulo.banciyuan.ui.adapter.Top10VpAdapter;
import com.exkulo.banciyuan.ui.model.HomePageEntity;
import com.exkulo.banciyuan.ui.model.HotestPostDigest;

import java.util.List;

public class Top10Activity extends BaseActivity {

    public static final String INTENT_DATA_TOP_10 = "top10";
    public static final String INTENT_DATA_TYPE = "type";

    public static final int TYPE_COSER = 0;
    public static final int TYPE_DRAW = 1;

    private HomePageEntity mHomePageEntity;
    private int mType = -1;

    @InjectView(R.id.activity_top10_vp) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        mHomePageEntity = (HomePageEntity) getIntent().getSerializableExtra(INTENT_DATA_TOP_10);
        mType = getIntent().getIntExtra(INTENT_DATA_TYPE, -1);
        checkIntentData(mHomePageEntity, INTENT_DATA_TOP_10);
        checkIntentData(mType, INTENT_DATA_TYPE);
        List<HotestPostDigest> digests = null;
        switch (mType) {
            case TYPE_COSER:
                digests = mHomePageEntity.getHotCos();
                break;
            case TYPE_DRAW:
                digests = mHomePageEntity.getHotDraw();
                break;
        }
        ButterKnife.inject(this);
        mViewPager.setAdapter(new Top10VpAdapter(context(), digests));
        hideProgressBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top10, menu);
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
