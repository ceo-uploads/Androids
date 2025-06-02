package com.example.unchain.Model;

public class FriendModel {
    int friend_profilePic;
    String friendName;

    public FriendModel(int friend_profilePic, String friendName) {
        this.friend_profilePic = friend_profilePic;
        this.friendName = friendName;
    }

    public int getFriend_profilePic() {
        return friend_profilePic;
    }

    public void setFriend_profilePic(int friend_profilePic) {
        this.friend_profilePic = friend_profilePic;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
