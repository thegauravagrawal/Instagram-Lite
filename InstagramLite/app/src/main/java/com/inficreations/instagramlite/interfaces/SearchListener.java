package com.inficreations.instagramlite.interfaces;

public interface SearchListener {
    void onStartLoading();

    void onCompleteLoading();

    void onFailed(String message);
}
