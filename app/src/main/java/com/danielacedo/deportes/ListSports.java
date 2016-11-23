package com.danielacedo.deportes;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.danielacedo.deportes.Preferences.SportPreference;
import com.danielacedo.deportes.adapter.SportAdapter;
import com.danielacedo.deportes.model.Sport;

import java.util.ArrayList;
import java.util.List;

public class ListSports extends AppCompatActivity {

    private static final String LIKED_SPORTS_KEY = "liked";
    public static final String DIALOG_TAG = "dialog";

    private Button btn_Confirm;
    private ListView lv_Sports;
    private SportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sports);

        adapter = new SportAdapter(ListSports.this);
        lv_Sports = (ListView) findViewById(R.id.lv_Sports);
        lv_Sports.setAdapter(adapter);

        btn_Confirm = (Button) findViewById(R.id.btn_btn_Confirm);
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SportPreference.getInstance(ListSports.this).saveLikedObjectsId(adapter.getLikedObjectsId());
                Toast.makeText(ListSports.this, getResources().getString(R.string.toast_liked_confirmed), Toast.LENGTH_SHORT).show();
            }
        });

        //Read liked objects from preference
        if(adapter.setLikedObjects(SportPreference.getInstance(ListSports.this).readLikedObjectsId())){
            Toast.makeText(ListSports.this, getResources().getString(R.string.toast_liked_readed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.listsport_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_filterSport:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /*private void openCharacterDialog(){
        FilterDialog dialog = new FilterDialog();
        dialog.show(getSupportFragmentManager(), DIALOG_TAG);

        dialog.onDismiss(new FilterDialog.FilterDialogCallback() {
            @Override
            public void dismiss(char c) {
                Toast.makeText(ListSports.this, String.valueOf(c), Toast.LENGTH_SHORT).show();
            }
        });

    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save liked sports in an array and save it
        ArrayList<Integer> likedSports = new ArrayList<Integer>();

        for(int i = 0; i<lv_Sports.getCount(); i++){
            Sport sport = (Sport) lv_Sports.getItemAtPosition(i);

            if(sport.isLike()){
                likedSports.add(sport.getId());
            }
        }

        if(!likedSports.isEmpty())
            outState.putIntegerArrayList(LIKED_SPORTS_KEY, likedSports);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Get liked sports and update adapter
        ArrayList<Integer> likedSports = savedInstanceState.getIntegerArrayList(LIKED_SPORTS_KEY);

        adapter.setLikedObjects(likedSports);
    }
}
