package com.inficreations.instagramlite.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.inficreations.instagramlite.R;
import com.inficreations.instagramlite.adapters.SearchAdapter;
import com.inficreations.instagramlite.interfaces.SearchListener;
import com.inficreations.instagramlite.viewmodel.InstagramRepo;

import java.util.ArrayList;

import dev.niekirk.com.instagram4android.requests.payload.InstagramSearchUsersResultUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchListener, View.OnClickListener {
    private InstagramRepo instagramRepo;
    private Button searchButton;
    private ArrayList<InstagramSearchUsersResultUser> searchUsersResultUsers;
    private SearchAdapter searchAdapter;
    private Context context;
    private TextInputLayout editUsername;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        context = getContext();

        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.search_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        instagramRepo = new InstagramRepo(this);
        searchUsersResultUsers = new ArrayList<>();
        searchAdapter = new SearchAdapter(context, searchUsersResultUsers);
        recyclerView.setAdapter(searchAdapter);

        searchButton = view.findViewById(R.id.search_button);
        editUsername = view.findViewById(R.id.edit_username);

        searchButton.setOnClickListener(this);

        notifyData();

    }

    @Override
    public void onStartLoading() {
        searchButton.setEnabled(false);
        notifyData();
    }

    @Override
    public void onCompleteLoading() {
        searchButton.setEnabled(true);
        notifyData();

    }

    private void notifyData() {
        searchUsersResultUsers.addAll(instagramRepo.getSearchUsersResultUsers());
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String message) {
        searchButton.setEnabled(true);
    }


    @Override
    public void onClick(View view) {

        if (view == searchButton) {
            validation();
        }
    }


    private void validation() {
        String fullName = editUsername.getEditText().getText().toString().trim();
        editUsername.setError(null);
        if (fullName.isEmpty()) {
            editUsername.setError("Please enter username");
        } else if (fullName.contains("@")) {
            editUsername.setError("Invalid username");
        } else {
            instagramRepo.instagramSearchUser(fullName);
        }
    }
}