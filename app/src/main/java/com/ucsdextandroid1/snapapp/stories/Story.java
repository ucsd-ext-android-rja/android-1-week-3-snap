package com.ucsdextandroid1.snapapp.stories;

/**
 * Created by rjaylward on 2019-04-20
 */
public class Story {

    private String title;
    private String subtitle;
    private String imageUrl;
    private String storyLink;

    public Story(String title, String subtitle, String imageUrl, String storyLink) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageUrl = imageUrl;
        this.storyLink = storyLink;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getStoryLink() {
        return storyLink;
    }

}
