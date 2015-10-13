package com.exkulo.banciyuan.ui.adapter;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.exkulo.banciyuan.R;
import com.exkulo.banciyuan.ui.ui.BaseActivity;
import com.exkulo.banciyuan.ui.ui.PictureDetailActivity;
import com.exkulo.banciyuan.ui.model.PostDetail;
import com.exkulo.banciyuan.ui.utils.ImageLoader;
import com.exkulo.banciyuan.ui.utils.WrappedString;
import com.squareup.picasso.Callback;
import java.util.ArrayList;

/**
 * Created by exkulo on 10/2/2015.
 */
public class PostDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;

    private BaseActivity mContext;
    private PostDetail mEntity;
    private LayoutInflater mLayoutInflater;

    public PostDetailAdapter(BaseActivity context, PostDetail entity) {
        mContext = context;
        mEntity = entity;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        int count = mEntity == null ? 0
            : (mEntity.getImgUrls() == null ? 1 : (mEntity.getImgUrls().size() + 1));
        return count;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        HeaderHolder headerAdapter = null;
        NormalHolder normalAdapter = null;
        if (viewType == TYPE_HEADER) {
            v = mLayoutInflater.inflate(R.layout.header_activity_post_detail, parent, false);
            headerAdapter = new HeaderHolder(v);
            return headerAdapter;
        } else if (viewType == TYPE_NORMAL) {
            v = mLayoutInflater.inflate(R.layout.imageview, parent, false);
            normalAdapter = new NormalHolder(v);
            return normalAdapter;
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerAdapter = (HeaderHolder) holder;
            ImageLoader.show(headerAdapter.headerIv,
                ((ArrayList<WrappedString>) mEntity.getImgUrls()).get(0).getS());
            String txt = mEntity.getTxt();
            if (txt == null || txt.isEmpty()) {
                headerAdapter.txtCard.setVisibility(View.GONE);
            } else {
                headerAdapter.contentTv.setText(txt);
                headerAdapter.txtCard.setVisibility(View.VISIBLE);
            }
            headerAdapter.charTv.setText(mEntity.getCharacter());
            String cn = mEntity.getCn();
            if(cn == null || cn.isEmpty()) {
                headerAdapter.cnTitleTv.setVisibility(View.GONE);
                headerAdapter.cnTv.setVisibility(View.GONE);
            } else {
                headerAdapter.cnTv.setText(cn);
                headerAdapter.cnTitleTv.setVisibility(View.VISIBLE);
                headerAdapter.cnTv.setVisibility(View.VISIBLE);
            }
            headerAdapter.orinalArt.setText(mEntity.getOrignalArt());
        } else if (holder instanceof NormalHolder) {
            final NormalHolder normalHolder = (NormalHolder) holder;
            ImageLoader.show(normalHolder.mImageView,
                ((ArrayList<WrappedString>) mEntity.getImgUrls()).get(position - 1).getS(),
                new Callback() {
                    @Override
                    public void onSuccess() {
                        normalHolder.mImageView.setOnClickListener(
                            new OnPicClickListener(position));
                    }

                    @Override
                    public void onError() {

                    }
                });
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.activity_post_detail_header_iv) ImageView headerIv;
        @InjectView(R.id.activity_post_detail_text_tv) TextView contentTv;
        @InjectView(R.id.header_activity_post_detail_char_tv) TextView charTv;
        @InjectView(R.id.header_activity_post_detail_cn_tv) TextView cnTv;
        @InjectView(R.id.header_activity_post_detail_infomation_orignal_art_tv) TextView orinalArt;
        @InjectView(R.id.header_activity_post_detail_cn_title) TextView cnTitleTv;
        @InjectView(R.id.activity_post_detail_text_card) CardView txtCard;

        public HeaderHolder(View parent) {
            super(parent);
            ButterKnife.inject(this, parent);
        }
    }

    static class NormalHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.app_imageview) ImageView mImageView;

        public NormalHolder(View parent) {
            super(parent);
            ButterKnife.inject(this, parent);
        }
    }

    private class OnPicClickListener implements View.OnClickListener {
        int pos;

        public OnPicClickListener(int pos) {
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(mContext, PictureDetailActivity.class);

            //mContext.startActivity(intent);
            Intent intent = new Intent(mContext, PictureDetailActivity.class);
            intent.putExtra(PictureDetailActivity.DATA_CN, mEntity.getCn() + "_" + mEntity.getCharacter());
            intent.putExtra(PictureDetailActivity.DATA_PIC_URL,
                ((ArrayList<WrappedString>) mEntity.getImgUrls()).get(pos - 1).getS());
            String transitionName = mContext.getString(R.string.transition_home_post_to_detail);
            ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, v,
                    // The view which starts the transition
                    transitionName
                    // The transitionName of the view we’re transitioning to
                );
            ActivityCompat.startActivity(mContext, intent, options.toBundle());
        }
    }
}
