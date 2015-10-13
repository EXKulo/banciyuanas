package com.exkulo.banciyuan.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
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
import com.exkulo.banciyuan.ui.ui.PostDetailActivity;
import com.exkulo.banciyuan.ui.ui.Top10Activity;
import com.exkulo.banciyuan.ui.customwidget.EasyMaterialSearchBar;
import com.exkulo.banciyuan.ui.model.HomePageEntity;
import com.exkulo.banciyuan.ui.model.HotestPostDigest;
import com.exkulo.banciyuan.ui.utils.ImageLoader;
import com.exkulo.banciyuan.ui.utils.ViewUtils;

import java.util.List;

/**
 * Created by exkulo on 9/14/2015.
 */
public class MainPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private BaseActivity mContext;
    private HomePageEntity mEntity;
    private LayoutInflater mLayoutInflater;

    public MainPageAdapter(BaseActivity context, HomePageEntity entity) {
        mContext = context;
        mEntity = entity;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public static enum ITEM_TYPE {
        SEARCH_BAR,
        RANK,
        NORMAL;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return (mEntity == null ? 0
            : (mEntity.getPosts() == null ? 3 : (mEntity.getPosts().size() + 3)));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == ITEM_TYPE.SEARCH_BAR.ordinal()) {
            View searchBar = new EasyMaterialSearchBar(mContext);
            viewHolder = new SearchViewHolder((EasyMaterialSearchBar) searchBar);
            return viewHolder;
        }
        if (viewType == ITEM_TYPE.RANK.ordinal()) {
            View root = mLayoutInflater.inflate(R.layout.cus_card_home_hotest, parent, false);
            viewHolder = new RankViewHolder(root);
            return viewHolder;
        } else {
            View root = mLayoutInflater.inflate(R.layout.cus_card_home_normal, parent, false);
            viewHolder = new NormalViewHolder(root);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchViewHolder) {
            final SearchViewHolder viewHolder = (SearchViewHolder) holder;
            viewHolder.searchBar.setOnSearchIconClickListener(
                new EasyMaterialSearchBar.OnSearchIconClickListener() {
                    @Override
                    public void onSearchIconClicked(String key) {
                        //TODO
                        StringBuilder builder = new StringBuilder("http://bcy.net/coser/detail/");
                        String[] info = key.split(" ");
                        if (info == null || info.length != 2) {
                            ViewUtils.t(mContext, "输入格式有误");
                            return;
                        }
                        builder.append(info[0] + "/" + info[1]);
                        Intent intent = new Intent(mContext, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.INTENT_POST_URL, builder.toString());
                        mContext.startActivity(intent);
                    }
                });
        } else if (holder instanceof NormalViewHolder) {
            final NormalViewHolder viewHolder = (NormalViewHolder) holder;
            viewHolder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        } else if (holder instanceof RankViewHolder) {
            final RankViewHolder viewHolder = (RankViewHolder) holder;

            if (position == 1) {
                final List<HotestPostDigest> hots = mEntity.getHotCos();
                ImageLoader.show(viewHolder.iv1, hots.get(0).getPicUrl());
                ImageLoader.show(viewHolder.iv2, hots.get(1).getPicUrl());
                ImageLoader.show(viewHolder.iv3, hots.get(2).getPicUrl());
                viewHolder.tvCn1.setText(hots.get(0).getCn());
                viewHolder.tvCn2.setText(hots.get(1).getCn());
                viewHolder.tvCn3.setText(hots.get(2).getCn());
                viewHolder.iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.INTENT_POST_URL,
                            hots.get(0).getPostUri());
                        mContext.startActivity(intent);
                    }
                });
                viewHolder.iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.INTENT_POST_URL,
                            hots.get(1).getPostUri());
                        mContext.startActivity(intent);
                    }
                });
                viewHolder.iv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.INTENT_POST_URL,
                            hots.get(2).getPostUri());
                        mContext.startActivity(intent);
                    }
                });
                viewHolder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Top10Activity.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra(Top10Activity.INTENT_DATA_TOP_10, mEntity);
                        intent.putExtra(Top10Activity.INTENT_DATA_TYPE, Top10Activity.TYPE_COSER);
                        mContext.startActivity(intent);
                    }
                });
            } else if (position == 2) {
                final List<HotestPostDigest> hots = mEntity.getHotDraw();
                ImageLoader.show(viewHolder.iv1, hots.get(0).getPicUrl());
                ImageLoader.show(viewHolder.iv2, hots.get(1).getPicUrl());
                ImageLoader.show(viewHolder.iv3, hots.get(2).getPicUrl());
                viewHolder.tvCn1.setText(hots.get(0).getCn());
                viewHolder.tvCn2.setText(hots.get(1).getCn());
                viewHolder.tvCn3.setText(hots.get(2).getCn());
                viewHolder.iv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.INTENT_POST_URL,
                            hots.get(0).getPostUri());
                        mContext.startActivity(intent);
                    }
                });
                viewHolder.iv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.INTENT_POST_URL,
                            hots.get(1).getPostUri());
                        mContext.startActivity(intent);
                    }
                });
                viewHolder.iv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.INTENT_POST_URL,
                            hots.get(2).getPostUri());
                        mContext.startActivity(intent);
                    }
                });
                viewHolder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Top10Activity.class);
                        Bundle bundle = new Bundle();
                        intent.putExtra(Top10Activity.INTENT_DATA_TOP_10, mEntity);
                        intent.putExtra(Top10Activity.INTENT_DATA_TYPE, Top10Activity.TYPE_DRAW);
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.SEARCH_BAR.ordinal();
        } else if (position == 1 || position == 2) {
            return ITEM_TYPE.RANK.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    public static class RankViewHolder extends RecyclerView.ViewHolder {

        View layout1, layout2, layout3;
        ImageView iv1;
        TextView tvCn1;
        TextView tvBrowse1;
        ImageView iv2;
        TextView tvCn2;
        TextView tvBrowse2;
        ImageView iv3;
        TextView tvCn3;
        TextView tvBrowse3;
        View root;

        public RankViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.cus_card_home_hotest_root);
            layout1 = itemView.findViewById(R.id.cus_card_home_hotest_sublayout_1);
            layout2 = itemView.findViewById(R.id.cus_card_home_hotest_sublayout_2);
            layout3 = itemView.findViewById(R.id.cus_card_home_hotest_sublayout_3);
            iv1 = (ImageView) layout1.findViewById(R.id.item_hotest_unit_iv);
            iv2 = (ImageView) layout2.findViewById(R.id.item_hotest_unit_iv);
            iv3 = (ImageView) layout3.findViewById(R.id.item_hotest_unit_iv);
            tvCn1 = (TextView) layout1.findViewById(R.id.item_hotest_unit_cn);
            tvCn2 = (TextView) layout2.findViewById(R.id.item_hotest_unit_cn);
            tvCn3 = (TextView) layout3.findViewById(R.id.item_hotest_unit_cn);
            tvBrowse1 = (TextView) layout1.findViewById(R.id.item_hotest_unit_browse_count);
            tvBrowse2 = (TextView) layout2.findViewById(R.id.item_hotest_unit_browse_count);
            tvBrowse3 = (TextView) layout3.findViewById(R.id.item_hotest_unit_browse_count);
        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cus_card_home_normal_root) CardView content;
        @InjectView(R.id.cus_card_home_normal_art_name_tv) TextView artName;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        EasyMaterialSearchBar searchBar;

        public SearchViewHolder(EasyMaterialSearchBar parent) {
            super(parent);
            searchBar = (EasyMaterialSearchBar) parent;
        }
    }
}
