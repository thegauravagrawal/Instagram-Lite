package com.inficreations.instagramlite.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.inficreations.instagramlite.R;
import com.inficreations.instagramlite.adapters.ProfileAdapter;
import com.inficreations.instagramlite.interfaces.ProfileListener;
import com.inficreations.instagramlite.viewmodel.InstagramRepo;

import java.util.ArrayList;

import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedItem;

public class ProfileActivity extends AppCompatActivity implements ProfileListener {
    private ArrayList<InstagramFeedItem> profileFeedItems;
    private InstagramRepo instagramRepo;

    RecyclerView recyclerView;
    ProfileAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileFeedItems = new ArrayList<>();

        recyclerView = findViewById(R.id.profile_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);

        profileAdapter = new ProfileAdapter(this,profileFeedItems);
        recyclerView.setAdapter(profileAdapter);

        instagramRepo = new InstagramRepo(this);

        getAndNotifyData();


        if (profileFeedItems.size()==0){
            instagramRepo.instagramProfileFeed();
        }
    }

    @Override
    public void onLoaded() {
        System.out.println("profileFeedItems onLoaded " + profileFeedItems.size());
        getAndNotifyData();
    }
    private void getAndNotifyData() {
        profileFeedItems.addAll(instagramRepo.getProfileFeedItems());
        profileAdapter.notifyDataSetChanged();
    }
}
