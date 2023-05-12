package com.patrick.ware.app_structure;

import androidx.annotation.NonNull;

public class Cake {
    private String name;
    private String description;
  private  int imageResource;



    private Cake(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }



public static final Cake[] cakes = {
        new Cake( "Sponge", "Sponge cake is a light cake made with eggs, flour and sugar, sometimes leavened with baking powder.",
        R.drawable.sponge ),

        new Cake( "Red Velvet", "Red velvet cake is a delicacy originating from the Victorian Era.", R.drawable.red_velvet ),
        new Cake( "Carrot Cake", "Carrot cake is cake that contains carrots mixed into the batter.", R.drawable.carrot )

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



