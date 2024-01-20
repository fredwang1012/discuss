package com.example.discuss;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {


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
}
