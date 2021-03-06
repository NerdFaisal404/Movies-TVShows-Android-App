package com.example.acer.movies.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.movies.AboutTVShowActivity;
import com.example.acer.movies.utils.IntentConstants;
import com.example.acer.movies.R;
import com.example.acer.movies.models.TVShow;
import com.example.acer.movies.network.URLConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by KeshavAggarwal on 12/02/17.
 */

public class RecyclerAdapterSeeAllTvshows extends RecyclerView.Adapter<RecyclerAdapterSeeAllTvshows.ViewHolder> {

    private ArrayList<TVShow> mTvShows;
    Context mContext;

    public RecyclerAdapterSeeAllTvshows(ArrayList<TVShow> tvShows, Context context) {
        mTvShows = tvShows;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_all_recyclerview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mTvShows != null) {
            holder.movieName.setText(mTvShows.get(position).getTitle());
            Picasso.with(mContext).load(URLConstants.IMAGE_BASE_URL + mTvShows.get(position).getPosterPath()).into(holder.movieThumbnailImage);
            if (mTvShows.get(position).getDate().length() >= 5) {
                String date = mTvShows.get(position).getDate().substring(0, 4);
                holder.movieReleaseDate.setText(date);
            }
            String rating = Double.toString(mTvShows.get(position).getRating());
            holder.rating.setText(rating);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.movieThumbnailImage, holder.movieThumbnailImage.getTransitionName()).toBundle();
                    intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_ID, mTvShows.get(position).getId());
                    intent.putExtra(IntentConstants.INTENT_KEY_POSTER_PATH, mTvShows.get(position).getPosterPath());
                    intent.putExtra(IntentConstants.INTENT_KEY_TVSHOW_NAME, mTvShows.get(position).getTitle());
                    intent.setClass(mContext, AboutTVShowActivity.class);
                    mContext.startActivity(intent, bundle);

                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView movieThumbnailImage;
        TextView movieName;
        TextView movieReleaseDate;
        TextView rating;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            movieThumbnailImage = (ImageView) itemView.findViewById(R.id.thumbnailImageView);
            movieName = (TextView) itemView.findViewById(R.id.nameTextView);
            movieReleaseDate = (TextView) itemView.findViewById(R.id.releaseDateTextView);
            rating = (TextView) itemView.findViewById(R.id.ratingTextView);

        }
    }
}
