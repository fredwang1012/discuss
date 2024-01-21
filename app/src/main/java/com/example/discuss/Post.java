package com.example.discuss;

import android.os.Build;

import com.example.discuss.persistance.Writable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post implements Writable {


    private String title;
    private PostItem question;
    private List<PostItem> replies;

    public Post(String t, PostItem pt) {

        title = t;
        question = pt;
        replies = new ArrayList<>();

    }

    public void changeTitle(String s) {
        this.title = s;
    }
    public void changeQuestion(String newContent) {
        question.changeContent(newContent);
    }

    public String getTitle() {
        return this.title;
    }

    public PostItem getQuestion() {
        return question;
    }

    public List<PostItem> getReplies() {
        return replies;
    }

    public void addReplies(PostItem reply) {
        replies.add(reply);
    }

    public void useToAddRepliesForJsonOnly(List<PostItem> replies) {
        this.replies = replies;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("title", title);
            json.put("question",question.toJson());
            json.put("replies",repliesToJson());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private JSONArray repliesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PostItem postItem : replies) {
            jsonArray.put(postItem.toJson());
        }

        return jsonArray;
    }

}
