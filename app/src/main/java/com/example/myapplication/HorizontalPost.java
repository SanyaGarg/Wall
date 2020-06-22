package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HorizontalPost {

    private String img;

    public HorizontalPost(String mImg){
        this.img = mImg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String mImg) {
        img = mImg;
    }

}
