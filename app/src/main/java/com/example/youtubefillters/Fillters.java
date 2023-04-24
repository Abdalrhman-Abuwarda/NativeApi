package com.example.youtubefillters;

public class Fillters {
    private String videoId;
    private String thumbnailUrl;
    private String title;
    private String publishedAt;
    private String channelTitle;
    private String description;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Fillters(String videoId, String thumbnailUrl, String title, String publishedAt, String channelTitle, String description) {

        this.videoId = videoId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.publishedAt = publishedAt;
        this.channelTitle = channelTitle;
        this.description = description;


    }
}
