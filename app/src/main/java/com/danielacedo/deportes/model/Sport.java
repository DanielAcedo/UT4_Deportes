package com.danielacedo.deportes.model;

/**
 * Created by Daniel on 23/11/16.
 */

public class Sport {
    private int id;
    private String name;
    private int icon;
    private boolean like;

    public Sport(int id, String name, int icon, boolean like){
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.like = like;
    }

    public Sport(int id, String name, int icon){
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.like = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
