package com.example.haeyoungjoo.hackerthon;

import android.graphics.drawable.Drawable;

/**
 * Created by hae young Joo on 2016-11-12.
 */

public class ListViewItem {

    private Drawable iconDrawable ;
    private String name;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }

   public void setName(String s){
        name = s;
    }
    public String getName(){
       return this.name;
   }

}
