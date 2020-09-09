package com.inficreations.instagramlite.interfaces;

public interface TimelineListener {
    void onStartLoading();

    void onFailed(String message);

    void onLoaded();

    void onStartLikingMedia();

    void onLikeCompleted(int position);

    void likingFailed(String message);
}
