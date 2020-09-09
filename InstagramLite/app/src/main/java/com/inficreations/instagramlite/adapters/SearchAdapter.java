package com.inficreations.instagramlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inficreations.instagramlite.R;

import java.util.ArrayList;

import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsersResultUser;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private ArrayList<InstagramSearchUsersResultUser> users;
    private Context context;

    public SearchAdapter(Context context, ArrayList<InstagramSearchUsersResultUser> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_search, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        InstagramSearchUsersResultUser user = users.get(position);

        holder.userName.setText(user.getUsername());

        holder.fullName.setText(user.getFull_name());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName, fullName;
        Button followButton;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.user_pic);
            userName = itemView.findViewById(R.id.username);
            fullName = itemView.findViewById(R.id.fullname);
            followButton = itemView.findViewById(R.id.follow);
        }
    }
}
