package com.danielacedo.deportes.repository;

import com.danielacedo.deportes.R;
import com.danielacedo.deportes.model.Sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 23/11/16.
 */

public class SportsRepository {
    static private List<Sport> sportList;

    public static List<Sport> getSportList() {
        if(sportList == null){
            initializeList();
        }

        return sportList;
    }

    public static void setSportList(List<Sport> sportList){
        SportsRepository.sportList = sportList;
    }

    private static void initializeList(){
        sportList = new ArrayList<Sport>();

        sportList.add(new Sport(0, "Baloncesto", R.drawable.icon_basketball));
        sportList.add(new Sport(1, "Fútbol", R.drawable.icon_soccer));
        sportList.add(new Sport(2, "Motociclismo", R.drawable.icon_motorbike));
        sportList.add(new Sport(3, "Tenis", R.drawable.icon_tennis));
        sportList.add(new Sport(4, "Golf", R.drawable.icon_golf));
        sportList.add(new Sport(5, "Ping pong", R.drawable.icon_table_tennis));
        sportList.add(new Sport(6, "Balonmano", R.drawable.icon_handball));
    }
}
