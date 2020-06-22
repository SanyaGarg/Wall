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
    private List<HorizontalPost> media;


    public Post(List<String> mlikes, String mUserId, String mText,String mDate,List<HorizontalPost> mMedia) {
        this.likes = mlikes;
        this.userId = mUserId;
        this.text = mText;
        this.date = mDate;
        this.media = mMedia;
    }

    public String getLikes() {
        return Integer.toString(likes.size());
    }

    public void setLikes(List<String> mLikes) {
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

    public List<HorizontalPost> getMedia() {
        return media;
    }

    public void setMedia(List<HorizontalPost> mMedia) {
        this.media = mMedia;
    }

}
