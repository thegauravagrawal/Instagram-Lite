package com.inficreations.instagramlite.model;

public class UserModel {
    private long pkId;
    private String profilePic;
    private String username;
    private String fullName;

    //Default Constructor
    public UserModel() {
    }

    //Parameterized Constructor
    public UserModel(long pkId, String profilePic, String username, String fullName) {
        this.pkId = pkId;
        this.profilePic = profilePic;
        this.username = username;
        this.fullName = fullName;
    }

    public long getPkId() {
        return pkId;
    }

    public void setPkId(long pkId) {
        this.pkId = pkId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
