package com.danielacedo.deportes.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import com.danielacedo.deportes.model.Sport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by usuario on 23/11/16.
 */

public class SportPreference {

    private static String LIKED_LIST_KEY = "liked";

    private static SharedPreferences sharedPreferences;
    private static SportPreference sportPreference;

    private SportPreference(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SportPreference getInstance(Context context){
        if(sportPreference == null){
            sportPreference = new SportPreference(context);
        }

        return sportPreference;
    }

    public void saveLikedObjectsId(List<Integer> likedSports){

        HashSet<String> likedSet = new HashSet<>();

        for (Integer id : likedSports) {
            likedSet.add(String.valueOf(id));
        }

        sharedPreferences.edit().putStringSet(LIKED_LIST_KEY, likedSet).apply();
    }

    public List<Integer> readLikedObjectsId(){
        ArrayList<Integer> sportsId = new ArrayList<Integer>();

        HashSet<String> likedSet = (HashSet<String>) sharedPreferences.getStringSet(LIKED_LIST_KEY, null);

        if(likedSet != null){
            for(String value : likedSet){
                int id = Integer.parseInt(value);
                sportsId.add(id);
            }
        }

        return sportsId;
    }
}
