package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
     //   recyclerView.setItemAnimator(new DefaultItemAnimator());
      //  recyclerView.setNestedScrollingEnabled(false);

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

       Wall_API apiInterface = ApiClient.getApiClient().create(Wall_API.class);

        final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGVObyI6IjExMTExMTExMTEiLCJ1c2VySWQiOiI1ZWRmZjEzYTRlZjEwMDBkMzQ4MjVmN2MiLCJpYXQiOjE1OTI3OTg0MDUsImV4cCI6MTU5MjgyMDAwNX0.-l53CitWl9PU_5udHRpL8blN7_LCYE1a-dXpKGKW-Rg";        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization",token);


        Call<List<Post>> call;
        call = apiInterface.getPosts(headers);

        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(@NotNull Call<List<Post>> call, @NotNull Response<List<Post>> response) {
                int responseCode = response.code();

                if(response.isSuccessful()){
                  //  Toast.makeText(MainActivity.this, "Success"+response.body(), Toast.LENGTH_LONG).show();
                      if (responseCode == 200) {

                        List<Post> posts = response.body();

                        recyclerView.setAdapter(new Adapter(posts,MainActivity.this));
                   // adapter = new Adapter(posts, MainActivity.this);
                    //recyclerView.setAdapter(adapter);
                   // adapter.notifyDataSetChanged();
                }
                }

            }

            @Override
            public void onFailure(@NotNull Call<List<Post>> call, @NotNull Throwable t) {
            }
            });
    }
}