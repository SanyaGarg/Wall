package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private BottomNavigationView setMainNav;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Post> newPosts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (android.os.Build.VERSION.SDK_INT > 9)
//        {
//            StrictMode.ThreadPolicy policy = new
//                    StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);


        Loadjson();

        setMainNav = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        Log.d(TAG, "onCreate: starting.");

        setMainNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.share:
                        setMainNav.setItemBackgroundResource(R.color.colorGrey);
                        return;
                    case R.id.community:
                        setMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        return;
                    case R.id.meetings:
                        setMainNav.setItemBackgroundResource(R.color.colorAccent);
                        return;

                    default:
                        return;
                }
            }
        });
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

    }

    public void Loadjson() {

        Log.d(TAG, "Wassup");

        Wall_API apiInterface = Wall_API.retrofit.create(Wall_API.class);

      //  final String BASE_URL = "https://community-ebh.herokuapp.com/";

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGVObyI6IjU1NTU1NTU1NTUiLCJ1c2VySWQiOiI1ZWU4ZTE5OGVlYzlkNzBjODY0NWI5OWEiLCJpYXQiOjE1OTI4MDcxODEsImV4cCI6MTU5MjgyODc4MX0.MjkiaQl7AZ8F24bpmcBTIjRdxOyTdDNnwHV179CE_uo";        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", token);


        Call<List<Post>> call;
        call = apiInterface.getPosts(headers);


        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(@NotNull Call<List<Post>> call, @NotNull Response<List<Post>> response) {
                Log.e("TAG",response.message());
                Log.i(TAG,"working");

               List<Post> posts = new ArrayList<>();

                int responseCode = response.code();

                if (response.isSuccessful() && (response.body()!=null)) {

                    JSONArray data;
                    try {
                        data = new JSONArray(response.body().getClass());

                    for (int i = 0; i < data.length(); i++) {
                        try {

                                JSONObject object = data.getJSONObject(i);

                                if(object.getString("text")!=null) {
                                    String text = object.getString("text");

                                    String date = object.getString("createdAt"); // for data
                                    String userId = object.getString("userId");

                                    List<String> mLikes = new ArrayList<>();

                                    if(object.getJSONArray("likes")!=null) {
                                        JSONArray likes = object.getJSONArray("likes");

                                        if(likes.length()>0) {
                                            for (int j = 0; j < likes.length(); i++) {
                                                mLikes.add(likes.getString(j));
                                            }
                                        }
                                    }

                                        List<HorizontalPost> arrayList = new ArrayList<>();

                                    if(object.getJSONArray("postMedia")!=null) {
                                        JSONArray medialinks = object.getJSONArray("postMedia"); // gives the array of postMedia links

                                        if(medialinks.length()>0) {
                                            for (int j = 0; j < medialinks.length(); j++) {
                                                HorizontalPost horizontalPost = new HorizontalPost(medialinks.getString(j));
                                                // access each link using medialinks[j]
                                                arrayList.add(horizontalPost);
                                            }
                                        }
                                    }
                                        Post postItem = new Post(mLikes, userId, text, date, arrayList);
                                        postItem.setDate(date);
                                        postItem.setMedia(arrayList);
                                        postItem.setLikes(mLikes);
                                        postItem.setText(text);
                                        postItem.setUserId(userId);

                                        Log.i("sanya", "Hiiiii");
                                        posts.add(postItem);

                                }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                    }
                    adapter = new Adapter(posts, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

        }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}