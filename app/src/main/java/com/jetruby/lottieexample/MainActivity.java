package com.jetruby.lottieexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jetruby.lottieexample.adapter.EndlessScrollListener;
import com.jetruby.lottieexample.adapter.TracksAdapter;
import com.jetruby.lottieexample.items.TracksDataUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTracksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate Recycler and set linear layout manager for vertical-scrolling list
        this.rvTracksList = findViewById(R.id.rv_tracks_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        this.rvTracksList.setLayoutManager(llm);

        // instantiate adapter and set it to the RecyclerView
        TracksAdapter adapter = new TracksAdapter();
        this.rvTracksList.setAdapter(adapter);

        // set EndlessScrollListener to load more items with a 3 seconds delay when reached the end of the list
        this.rvTracksList.addOnScrollListener(new EndlessScrollListener(llm) {
            @Override
            public void onLoadMore(int current_page) {
                loadItemsWithDelay(adapter, 3000);
            }
        });

        // add first portion of dummy data
        adapter.addItems(TracksDataUtil.createRandomTracksList(20));

    }

    // add a new portion of data, remove progress loader
    // must be run on UI thread
    // we use delay to simulate network call and display progress bar, data comes in 3 seconds
    private void loadItemsWithDelay(TracksAdapter adapter, int delayMs) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    adapter.removeLastItem();
                    adapter.addItems(TracksDataUtil.createRandomTracksList(20));
                });
            }
        }, delayMs);
    }
}
