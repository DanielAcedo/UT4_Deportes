package com.danielacedo.deportes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielacedo.deportes.R;
import com.danielacedo.deportes.model.Sport;
import com.danielacedo.deportes.repository.SportsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 23/11/16.
 */

public class SportAdapter extends ArrayAdapter<Sport> {

    public SportAdapter(Context context){
        super(context, R.layout.listsport_layout, SportsRepository.getSportList());
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        SportHolder holder;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.listsport_layout, null);
            holder = new SportHolder();

            holder.txt_name = (TextView)v.findViewById(R.id.txv_sport_name);
            holder.imv_icon = (ImageView) v.findViewById(R.id.imv_sport_icon);

            holder.cb_like = (CheckBox)v.findViewById(R.id.cb_sport_like);
            holder.cb_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    getItem(position).setLike(isChecked);
                }
            });

            v.setTag(holder);
        }else{
            holder = (SportHolder) v.getTag();
        }

        holder.txt_name.setText(getItem(position).getName());
        holder.imv_icon.setImageResource(getItem(position).getIcon());
        holder.cb_like.setChecked(getItem(position).isLike());

        return v;
    }

    /**
     * Returns a list of Objects that are liked
     * @return List of liked objects
     */
    public List<Integer> getLikedObjectsId(){
        ArrayList<Integer> likedObjects = new ArrayList<Integer>();

        for(int i = 0; i<getCount(); i++){
            if(getItem(i).isLike()){
                likedObjects.add(getItem(i).getId());
            }
        }

        return likedObjects;
    }

    /**
     * Set a number of objects in the list with the liked attribute true based on the id
     * @param likedObjects List of ids of liked objects
     * @return Success of operation
     */
    public boolean setLikedObjects(List<Integer> likedObjects){
        boolean changed = false;

        if(likedObjects != null){
            for(int i = 0; i < getCount(); i++){
                Sport sport = getItem(i);

                if(likedObjects.contains(sport.getId())){
                    sport.setLike(true);
                    changed = true;
                }
            }

            if(changed)
                notifyDataSetChanged();
        }

        return changed;
    }

    public void filterObjects(char c){
        getFilter().filter(String.valueOf(c));
    }

    public static class SportHolder{
        TextView txt_name;
        ImageView imv_icon;
        CheckBox cb_like;
    }
}
