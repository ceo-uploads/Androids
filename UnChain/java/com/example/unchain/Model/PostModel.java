package com.example.unchain.Model;

public class PostModel {
    int profilePic, postImg;
    String profileName, time, like, comment, share, save;

    public PostModel(int profilePic, int postImg, String profileName, String time, String like, String comment, String share) {
        this.profilePic = profilePic;
        this.postImg = postImg;
        this.profileName = profileName;
        this.time = time;
        this.like = like;
        this.comment = comment;
        this.share = share;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public int getPostImg() {
        return postImg;
    }

    public void setPostImg(int postImg) {
        this.postImg = postImg;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

}
