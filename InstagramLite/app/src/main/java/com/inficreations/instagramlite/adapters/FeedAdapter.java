package com.inficreations.instagramlite.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inficreations.instagramlite.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.niekirk.com.instagram4android.requests.payload.InstagramTimelineFeedItem;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<InstagramTimelineFeedItem> timelineFeedItems;
    private CustomOnClick customOnClick;

    public FeedAdapter(Context context, ArrayList<InstagramTimelineFeedItem> timelineFeedItems,
                       CustomOnClick customOnClick) {
        this.context = context;
        this.timelineFeedItems = timelineFeedItems;
        this.customOnClick = customOnClick;
    }

    @NonNull
    @Override
    public FeedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_feed, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.MyViewHolder holder, int position) {


        InstagramTimelineFeedItem item = timelineFeedItems.get(position);

        holder.username.setText(item.getMedia_or_ad().getUser().getUsername());

        Picasso.get().load(item.getMedia_or_ad().getUser().getProfile_pic_url())
                .into(holder.profilePic);

        if (item.getMedia_or_ad().getImage_versions2() != null &&
                item.getMedia_or_ad().getImage_versions2().getCandidates() != null) {

            Picasso.get().load(item.getMedia_or_ad().getImage_versions2()
                    .getCandidates().get(1).getUrl()).into(holder.feedItem);
        }
        holder.likes.setText(item.getMedia_or_ad().getLike_count() + " Likes");
        if (item.getMedia_or_ad().isHas_liked()) {
            holder.likes.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return timelineFeedItems.size();
    }

    public interface CustomOnClick {
        void onLikeButtonClicked(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView username, likes;
        ImageView profilePic, feedItem, likeBtn;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profilePic = itemView.findViewById(R.id.user_profile_pic);
            feedItem = itemView.findViewById(R.id.feed_item);

            likes = itemView.findViewById(R.id.like);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            likeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (customOnClick != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                customOnClick.onLikeButtonClicked(getAdapterPosition());
            }
        }
    }
}
