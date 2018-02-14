package com.jetruby.lottieexample.adapter;

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.jetruby.lottieexample.R;
import com.jetruby.lottieexample.items.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Leushin on 12.02.2018.
 */

public class TracksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int TYPE_TRACK = 1;
    private final static int TYPE_LOAD_MORE = 2;

    private static final float LIKE_START = 0.4f;
    private static final float LIKE_END = 0.7f;
    private static final float UNLIKE_START = 0.93f;
    private static final float UNLIKE_END = 1f;
    private static final float PLAY_START = 0f;
    private static final float PLAY_END = 0.5f;
    private static final float PAUSE_START = 0.5f;
    private static final float PAUSE_END = 1f;

    private List<Track> items = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TRACK:
                View trackView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track, parent, false);
                TrackViewHolder trackViewHolder = new TrackViewHolder(trackView);

                trackViewHolder.avLike.setOnClickListener(v -> {
                    int position = trackViewHolder.getAdapterPosition();
                    if (this.items.get(position).isLiked()) {
                        startUnlikeAnimation(this.items.get(position), trackViewHolder.avLike);
                    } else {
                        startLikeAnimation(this.items.get(position), trackViewHolder.avLike);
                    }
                });

                trackViewHolder.avPlayPause.setOnClickListener(v -> {
                    int position = trackViewHolder.getAdapterPosition();
                    if (this.items.get(position).isPlaying()) {
                        triggerPause(this.items.get(position), trackViewHolder.avPlayPause);
                    } else {
                        triggerPlay(this.items.get(position), trackViewHolder.avPlayPause);
                    }
                });
                return trackViewHolder;

            case TYPE_LOAD_MORE:
                View progressView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loader, parent, false);
                return new LoadMoreViewHolder(progressView);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TrackViewHolder) {
            ((TrackViewHolder) holder).bindData(items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return this.items != null ? this.items.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_LOAD_MORE;
        } else {
            return TYPE_TRACK;
        }
    }

    public void addItems(List<Track> newItems) {
        this.items.addAll(newItems);
        notifyItemRangeInserted(this.items.size(), newItems.size());
    }

    public void removeLastItem() {
        this.items.remove(items.size() - 1);
        notifyItemRemoved(items.size());
    }

    private void startLikeAnimation(Track track, LottieAnimationView avLike) {
        ValueAnimator animator = ValueAnimator.ofFloat(LIKE_START, LIKE_END).setDuration(500);
        animator.addUpdateListener(animation -> avLike.setProgress((float) animation.getAnimatedValue()));
        animator.start();
        track.setLiked(true);
    }

    private void startUnlikeAnimation(Track track, LottieAnimationView avLike) {
        ValueAnimator animator = ValueAnimator.ofFloat(UNLIKE_START, UNLIKE_END).setDuration(400);
        animator.addUpdateListener(animation -> avLike.setProgress((float) animation.getAnimatedValue()));
        animator.start();
        track.setLiked(false);
    }

    private void triggerPlay(Track track, LottieAnimationView avPlayPause) {
        ValueAnimator animator = ValueAnimator.ofFloat(PLAY_START, PLAY_END).setDuration(500);
        animator.addUpdateListener(animation -> avPlayPause.setProgress((float) animation.getAnimatedValue()));
        animator.start();
        track.setPlaying(true);
    }

    private void triggerPause(Track track, LottieAnimationView avPlayPause) {
        ValueAnimator animator = ValueAnimator.ofFloat(PAUSE_START, PAUSE_END).setDuration(500);
        animator.addUpdateListener(animation -> avPlayPause.setProgress((float) animation.getAnimatedValue()));
        animator.start();
        track.setPlaying(false);
    }

    private static class TrackViewHolder extends RecyclerView.ViewHolder {

        TextView tvArtist;
        TextView tvTitle;
        LottieAnimationView avPlayPause;
        LottieAnimationView avLike;

        TrackViewHolder(View itemView) {
            super(itemView);
            this.tvArtist = itemView.findViewById(R.id.tv_artist);
            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.avLike = itemView.findViewById(R.id.btn_like);
            this.avPlayPause = itemView.findViewById(R.id.btn_play_pause);
        }

        void bindData(Track track) {
            this.tvArtist.setText(track.getArtist());
            this.tvTitle.setText(track.getTitle());
            this.avPlayPause.setProgress(track.isPlaying() ? 0.5f : 0f);
            this.avLike.setProgress(track.isLiked() ? 0.9f : 0f);
        }
    }

    private static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
