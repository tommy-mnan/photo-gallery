package com.aia.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class Photo {
    private String title;
    private String link;
    private String media;
    @JsonProperty("date_taken")
    private String dateTaken;
    private String description;
    private String published;
    private String author;
    @JsonProperty("author_id")
    private String authorId;
    private String tags;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    @JsonIgnore
    public void setLink(String link) {
        this.link = link;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(JsonNode media) {
        this.media = media.get("m").asText();
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished() {
        return published;
    }

    @JsonIgnore
    public void setPublished(String published) {
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
