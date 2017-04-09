package com.monisha.android.moviefinder.details;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.monisha.android.moviefinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by monisha on 08/04/17.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {


    Context mContext;
    List<DetailModel> detailModel = new ArrayList<>();

    public DetailAdapter(Context mContext, List<DetailModel> detailModel){

        this.mContext = mContext;
        this.detailModel = detailModel;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie_details, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvTitle.setText(detailModel.get(position).getTitle());
        holder.tvGenre.setText(detailModel.get(position).getGenre());
        holder.tvReleasedDate.setText(detailModel.get(position).getReleasedDate());
        holder.tvRating.setText(detailModel.get(position).getRating());
        holder.tvPlot.setText(detailModel.get(position).getPlot());

        Picasso.with(mContext).load(detailModel.get(position).getImage()).fit().into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        return detailModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_genre)
        TextView tvGenre;

        @BindView(R.id.tv_released_date)
        TextView tvReleasedDate;

        @BindView(R.id.tv_plot)
        TextView tvPlot;

        @BindView(R.id.tv_rating)
        TextView tvRating;

        @BindView(R.id.iv_poster)
        ImageView ivPoster;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this,view);

        }
    }
}
