package com.inficreations.instagramlite.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inficreations.instagramlite.R;
import com.inficreations.instagramlite.adapters.FeedAdapter;
import com.inficreations.instagramlite.interfaces.TimelineListener;
import com.inficreations.instagramlite.viewmodel.InstagramRepo;

import java.util.ArrayList;

import dev.niekirk.com.instagram4android.requests.payload.InstagramTimelineFeedItem;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements TimelineListener, FeedAdapter.CustomOnClick {
    private Context context;
    private FeedAdapter feedAdapter;

    private ArrayList<InstagramTimelineFeedItem> timelineFeedItems;
    private InstagramRepo instagramRepo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        context = getContext();
        timelineFeedItems = new ArrayList<>();

        initView(view);

        instagramRepo = new InstagramRepo(this);

        getAndNotifyData();

        return view;
    }

    private void initView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(getContext(), timelineFeedItems, this);
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    public void onStartLoading() {
    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void onLoaded() {
        System.out.println("timelineFeedItems onLoaded " + timelineFeedItems.size());
        getAndNotifyData();
    }

    @Override
    public void onStartLikingMedia() {
        Toasty.info(context, "Like started", Toasty.LENGTH_SHORT).show();

    }

    @Override
    public void onLikeCompleted(int position) {
        Toasty.success(context, "Like successfully", Toasty.LENGTH_SHORT).show();

        timelineFeedItems.get(position).getMedia_or_ad().setHas_liked(true);
        int likeCount=timelineFeedItems.get(position).getMedia_or_ad().getLike_count()+1;
        timelineFeedItems.get(position).getMedia_or_ad().setLike_count(likeCount);

        feedAdapter.notifyDataSetChanged();
    }

    @Override
    public void likingFailed(String message) {

        Toasty.error(context, message, Toasty.LENGTH_SHORT).show();
    }


    private void getAndNotifyData() {
        timelineFeedItems.addAll(instagramRepo.getTimelineFeedItems());
        feedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLikeButtonClicked(int position) {

        instagramRepo.likeMedia(position);
    }

}
