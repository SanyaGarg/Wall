package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);




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

       Wall_API apiInterface = ApiClient.getClient();

        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGVObyI6IjU1NTU1NTU1NTUiLCJ1c2VySWQiOiI1ZWU4ZTE5OGVlYzlkNzBjODY0NWI5OWEiLCJpYXQiOjE1OTI1Nzg1MzQsImV4cCI6MTU5MjYwMDEzNH0.mz4vKJbtjyKgeLGjRzDQdyfZ923BQiV3Kumj5vxhAbg";
        Map<String,String> headers = new HashMap<>();
        headers.put("authorization",token);


        Call<List<Post>> call;
        call = apiInterface.getPosts(headers);

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(@NotNull Call<List<Post>> call, @NotNull Response<List<Post>> response) {
                Log.e("Response",response.toString());
                int responseCode = response.code();

                if (responseCode == 200) {

                    List<Post> posts = response.body();
//                    for (Post post : posts) {
//
//                    }
                    adapter = new Adapter(posts, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Post>> call, @NotNull Throwable t) {
            }
            });
    }
}