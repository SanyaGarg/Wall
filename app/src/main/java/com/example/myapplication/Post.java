package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Post {

    @SerializedName("likes")
    private List<String> likes;

    @SerializedName("_id")
    private String postId;

    @SerializedName("userId")
    private String userId;

    @SerializedName("text")
    private String text;

    @SerializedName("createdAt")
    private String date;

    @SerializedName("postMedia")
    private List<String> img;

    public Post(ArrayList<String> mlikes, String mUserId, String mText,String mDate,ArrayList<String> mImg) {
        this.likes = mlikes;
        this.userId = mUserId;
        this.text = mText;
        this.date = mDate;
        this.img = mImg;
    }

    public int getLikes() {
        return likes.size();
    }

    public void setLikes(ArrayList<String> mLikes) {
        likes = mLikes;
    }

    public String getPostId(){ return postId; }

    public void setPostId(String mpostId) {
        postId = mpostId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String mUserId) {
        userId = mUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String mtext) {
        text = mtext;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String mdate) {
        text = mdate;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> mImg) {
        img = mImg;
    }

}
