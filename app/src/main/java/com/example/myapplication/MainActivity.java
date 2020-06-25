package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

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

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //   recyclerView.setItemAnimator(new DefaultItemAnimator());
        //  recyclerView.setNestedScrollingEnabled(false);

        Loadjson();


        setMainNav = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        Log.d(TAG, "onCreate: starting.");

        setMainNav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {

                    case R.id.share:
                       // startActivity(new Intent(Wall.this,
                       //         Share.class));
                       // overridePendingTransition(0,0);
                        return ;
                    case R.id.community:
                        //selectedFragment = new CommunityFragment();

                        break;
                    case R.id.meetings:
                       // selectedFragment = new MeetingsFragment();
                        break ;
                    case R.id.profile:
                        //selectedFragment = new ProfileFragment();
                        break ;
                }

                //assert selectedFragment != null;
               // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            }

        });
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

    }

    public void Loadjson() {

        Wall_API apiInterface = ApiClient.getApiClient().create(Wall_API.class);

        final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGVObyI6IjU1NTU1NTU1NTUiLCJ1c2VySWQiOiI1ZWU4ZTE5OGVlYzlkNzBjODY0NWI5OWEiLCJpYXQiOjE1OTMwNjUxMDksImV4cCI6MTU5MzA4NjcwOX0.dfhmDkXj_X9LExVnJLyvPPIIQlivh-LB_aKnQzAJ3zs";
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization",token);


        Call<List<Post>> call;
        call = apiInterface.getPosts(headers);

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(@NotNull Call<List<Post>> call, @NotNull Response<List<Post>> response) {
                int responseCode = response.code();

                //if(response.isSuccessful()){
                    //  Toast.makeText(MainActivity.this, "Success"+response.body(), Toast.LENGTH_LONG).show();
                    if (responseCode == 200) {
                        Log.i("TAG","helloooo");
                        List<Post> posts = response.body();

                        recyclerView.setAdapter(new Adapter(posts,MainActivity.this));
                        // adapter = new Adapter(posts, MainActivity.this);
                        //recyclerView.setAdapter(adapter);
                        // adapter.notifyDataSetChanged();
                    }


            }

            @Override
            public void onFailure(@NotNull Call<List<Post>> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}