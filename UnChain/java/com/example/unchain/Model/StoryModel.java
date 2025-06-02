package com.example.unchain.Model;

public class StoryModel {

    int storyPic, profilePic, storyType;
    String profileName;

    public StoryModel(int storyPic, int profilePic, int storyType, String profileName) {
        this.storyPic = storyPic;
        this.profilePic = profilePic;
        this.storyType = storyType;
        this.profileName = profileName;
    }

    public int getStoryPic() {
        return storyPic;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public int getStoryType() {
        return storyType;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setStoryPic(int storyPic) {
        this.storyPic = storyPic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public void setStoryType(int storyType) {
        this.storyType = storyType;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
}
