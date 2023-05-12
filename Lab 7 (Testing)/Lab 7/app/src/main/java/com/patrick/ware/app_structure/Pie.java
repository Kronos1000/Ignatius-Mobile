package com.patrick.ware.app_structure;

import androidx.annotation.NonNull;

public class Pie {
    private String name;
    private String description;
    private  int imageResource;



    private Pie(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    public static final Pie[] pies = {
            new Pie( "Steak N Cheese Pie ", "Get a face full of meat,what are you waiting for? get it down you ",
                    R.drawable.steakncheese ),

            new Pie( "Bacon N Egg  ", "here's a healthy breakfast option, ",
                    R.drawable.baconnegg ),
            new Pie("Live Plus Energy Drink", "Not a pie But i know josh likes them, they also pair well with a steak N cheese pie. So Drink Up and give me an A+",R.drawable.liveplus)


    };


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    @NonNull
    @Override
    public String toString() {
        return name.toString();
    }


}

