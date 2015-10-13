package com.exkulo.banciyuan.ui.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.exkulo.banciyuan.R;
import com.exkulo.banciyuan.ui.ui.BaseActivity;
import com.exkulo.banciyuan.ui.ui.PostDetailActivity;
import com.exkulo.banciyuan.ui.model.HotestPostDigest;
import com.exkulo.banciyuan.ui.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exkulo on 10/4/2015.
 */
public class Top10VpAdapter extends PagerAdapter {

    private List<HotestPostDigest> mPostDigests;
    private BaseActivity mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<View> mRoots;

    public Top10VpAdapter(BaseActivity context, List<HotestPostDigest> postDigests) {
        mPostDigests = postDigests;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mRoots = new ArrayList<View>();
    }

    @Override
    public int getCount() {
        return mPostDigests == null ? 0 : mPostDigests.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
        mRoots.remove(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View v = mLayoutInflater.inflate(R.layout.item_top_10_vp, null);
        ImageView cnIv = (ImageView) v.findViewById(R.id.item_top_10_vp_cn_iv);
        ImageView mainIv = (ImageView) v.findViewById(R.id.item_top_10_vp_iv);
        TextView cnTv = (TextView) v.findViewById(R.id.item_top_10_vp_cn_tv);
        String url = mPostDigests.get(position).getPicUrl().replace("/2X3", "");
        url = url.replace("cover", "post");
        ImageLoader.showByGlide(mainIv, url);
        cnTv.setText(mPostDigests.get(position).getCn());
        mainIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PostDetailActivity.class);
                intent.putExtra(PostDetailActivity.INTENT_POST_URL,
                    mPostDigests.get(position).getPostUri());
                mContext.startActivity(intent);
            }
        });
        mRoots.add(v);
        container.addView(v);
        return v;
    }
}
