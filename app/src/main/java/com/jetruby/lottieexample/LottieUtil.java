package com.jetruby.lottieexample;

import android.content.Context;
import android.content.res.AssetManager;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Artem Leushin on 13.02.2018.
 */

public class LottieUtil {

    public static void loadAndStartAnimation(String jsonName, LottieAnimationView animationView) {
        animationView.clearAnimation();
        animationView.setAnimation(jsonName);
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        animationView.playAnimation();
    }

    public static void loadAnimation(String jsonName, LottieAnimationView animationView) {
        animationView.setAnimation(jsonName);
        animationView.setRepeatCount(0);
    }

    public static List<String> parseAssetsForAnimations(Context context) {
        List<String> animationsList = new ArrayList<>();

        try {
            AssetManager assetManager = context.getApplicationContext().getAssets();
            for (String animation : Arrays.asList(assetManager.list(""))) {
                if (animation.contains(".json")) {
                    animationsList.add(animation);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return animationsList;
    }
}
