package com.example.discuss;

import android.os.Build;

import java.time.LocalDateTime;

public class PostItem {

    private String content;
    private String poster;
    private LocalDateTime creationTime;

    public PostItem(String c, String p) {

        content = c;
        poster = p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creationTime = LocalDateTime.now();
        }

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

    public LocalDateTime getCreationTime() {

        return creationTime;

    }
}





