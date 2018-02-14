package com.jetruby.lottieexample.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Artem Leushin on 14.02.2018.
 */

public class TracksDataUtil {
    private static String[] bandNames = {
            "Protest The Hero",
            "The Fall Of Troy",
            "Erra",
            "Periphery",
            "Napoleon",
            "Animals As Leaders",
            "Dance Gavin Dance",
            "Elitist",
            "Veil Of Maya",
            "Counterparts",
            "August Burns Red",
            "Tony Danza Tapdance Extravaganza"};
    private static String[] trackTitles = {
            "Luck As A Constant",
            "Teleute",
            "Clarity",
            "Stargazer",
            "Mikasa",
            "Sturdy Wings",
            "Digression",
            "The Dissentience",
            "Bloodmeat",
            "The Dark Trail",
            "Awkward",
            "CAFO",
            "Orchid",
            "Lonely Giant",
            "Canadian Bacon"
    };

    // create dummy Track objects by randomly combining bands and tracks names
    public static List<Track> createRandomTracksList(int capacity) {
        ArrayList<Track> randomTracks = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            randomTracks.add(createRandomTrack());
        }
        return randomTracks;
    }

    private static Track createRandomTrack() {
        Random random = new Random();
        int artistIndex = random.nextInt(bandNames.length);
        int titleIndex = random.nextInt(trackTitles.length);
        return new Track(bandNames[artistIndex], trackTitles[titleIndex]);
    }
}
