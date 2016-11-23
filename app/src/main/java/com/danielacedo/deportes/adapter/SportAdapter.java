package com.danielacedo.deportes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielacedo.deportes.R;
import com.danielacedo.deportes.model.Sport;
import com.danielacedo.deportes.repository.SportsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 23/11/16.
 */

public class SportAdapter extends ArrayAdapter<Sport> {

    List<Sport> original;
    List<Sport> filtered;

    public SportAdapter(Context context){
        super(context, R.layout.listsport_layout);
        original = SportsRepository.getSportList();

        filtered = new ArrayList<Sport>();
        filtered.addAll(original);
    }

    @Override
    public void add(Sport object) {
        filtered.add(object);
    }

    @Override
    public int getCount() {
        return filtered.size();
    }

    @Nullable
    @Override
    public Sport getItem(int position) {
        return filtered.get(position);
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

            v.setTag(holder);
        }else{
            holder = (SportHolder) v.getTag();
        }

        holder.txt_name.setText(getItem(position).getName());
        holder.imv_icon.setImageResource(getItem(position).getIcon());

        holder.cb_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { //Have to place this here or it won't actually edit the objects
                getItem(position).setLike(isChecked);
            }
        });
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

    public void filterObjects(String filter){
        getFilter().filter(String.valueOf(filter));
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new SportFilter();
    }

    public static class SportHolder{
        TextView txt_name;
        ImageView imv_icon;
        CheckBox cb_like;
    }

    class SportFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults result = new FilterResults();
            List<Sport> filteredResults = new ArrayList<Sport>();

            if(constraint.toString().equals("")){ //If constraint is empty, return all.
                result.values = original;
                result.count = original.size();
            }else{                               //Else, filter
                for(int i = 0; i < original.size(); i++){
                    Sport sport = original.get(i);

                    if(sport.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        filteredResults.add(sport);
                    }
                }

                result.values = filteredResults;
                result.count = filteredResults.size();
            }

            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered = (List<Sport>) results.values;
            notifyDataSetChanged();
        }
    }
}
