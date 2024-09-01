package com.husph.mymemory.utils;

import com.husph.mymemory.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {
    public static final List<Integer> DEFAULT_ICONS = List.of(
            R.drawable.ic_face,
            R.drawable.ic_flower,
            R.drawable.ic_gift,
            R.drawable.ic_heart,
            R.drawable.ic_home,
            R.drawable.ic_lightning,
            R.drawable.ic_moon,
            R.drawable.ic_plane,
            R.drawable.ic_school,
            R.drawable.ic_send,
            R.drawable.ic_star,
            R.drawable.ic_work
    );

    public static List<Integer> getShuffledImages(int numOfPairs) {
        List<Integer> shuffledImages = new ArrayList<>(DEFAULT_ICONS);
        Collections.shuffle(shuffledImages);

        List<Integer> takenImages =  shuffledImages.subList(0, Math.min(shuffledImages.size(), numOfPairs));

        List<Integer> randomizedImages = new ArrayList<>(takenImages);
        randomizedImages.addAll(takenImages);
        Collections.shuffle((randomizedImages));

        return randomizedImages;
    }

}
