package com.inficreations.instagramlite.interfaces;

import dev.niekirk.com.instagram4android.requests.payload.InstagramLoggedUser;

public interface LoginListener {
    void onStartLogin();

    void onFailed(String message);

    void onSuccess(InstagramLoggedUser loggedUser, String password);

}
