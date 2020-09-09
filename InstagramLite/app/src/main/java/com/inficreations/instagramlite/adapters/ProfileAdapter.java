package com.inficreations.instagramlite.adapters;

import android.content.Context;
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

import dev.niekirk.com.instagram4android.requests.payload.InstagramFeedItem;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private Context context;
    private ArrayList<InstagramFeedItem> profileFeedItems;

    public ProfileAdapter(Context context, ArrayList<InstagramFeedItem> profileFeedItems) {
        this.context = context;
        this.profileFeedItems = profileFeedItems;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_profile, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        InstagramFeedItem item = profileFeedItems.get(position);

        if (item.getImage_versions2()!=null &&
                item.getImage_versions2().getCandidates()!=null)
            Picasso.get().load(item.getImage_versions2().getCandidates().get(0).getUrl())
                    .into(holder.image);
        holder.likes.setText(item.getLike_count() + " Likes");
    }

    @Override
    public int getItemCount() {
        return profileFeedItems.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView likes;
        ImageView image;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.post_image);
            likes = itemView.findViewById(R.id.post_likes);
        }
    }
}
