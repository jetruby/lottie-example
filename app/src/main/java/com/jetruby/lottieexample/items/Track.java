package com.jetruby.lottieexample.items;

/**
 * Created by Artem Leushin on 12.02.2018.
 */

public class Track {

    private String artist;
    private String title;
    private boolean isLiked = false;
    private boolean isPlaying = false;

    public Track(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }


}
