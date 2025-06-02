package com.example.unchain.Model;

public class NotificationModel {
    int profilePic;
    String notf, time;

    public NotificationModel(int profilePic, String notf, String time) {
        this.profilePic = profilePic;
        this.notf = notf;
        this.time = time;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public String getNotf() {
        return notf;
    }

    public void setNotf(String notf) {
        this.notf = notf;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
