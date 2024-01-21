package com.example.discuss;

import android.os.Build;

import com.example.discuss.persistance.Writable;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class PostItem implements Writable {

    private String content;
    private String poster;
    private LocalDateTime creationTime;
    private boolean isQuestion;

    public PostItem(String c, String p, boolean q) {

        content = c;
        poster = p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creationTime = LocalDateTime.now();
        }
        isQuestion = q;

    }

    public PostItem(String c, String p, LocalDateTime localDateTime,boolean q) {
        content = c;
        poster = p;
        creationTime = localDateTime;
        isQuestion = q;
    }

    public void changeContent(String c) {
        this.content = c;
    }

    public String getContent() {
        return content;
    }

    public String getPoster() {
        return poster;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public LocalDateTime getCreationTime() {

        return creationTime;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("content", content);
            json.put("poster", (poster == null) ? "Unknown" : poster);
            json.put("LocalDateTime",creationTime);
            json.put("questioned?",isQuestion);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return json;
    }

    @Override
    public String toString() {
        return Helpers.breakStringLines(this.getContent(), 14) +
               "\n\n(" + (this.isQuestion() ? "QUESTION" : "REPLY") +
               " posted by " + this.getPoster() +
               "\non " + Helpers.formatDate(this.getCreationTime()) + ")";
    }

}