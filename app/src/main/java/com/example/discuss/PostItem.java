package com.example.discuss;

import android.os.Build;

import java.time.LocalDateTime;

public class PostItem {

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
    public String toString() {
        throw new RuntimeException();
    }

}





