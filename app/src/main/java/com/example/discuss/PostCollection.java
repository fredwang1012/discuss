package com.example.discuss;

import java.util.List;

public class PostCollection {

    public static class PostTitleDuplicateException extends Exception {
        // nothing
    }

    public PostCollection() {
        // TODO: read all posts from JSON
        throw new RuntimeException();
    }

    public void addPost(Post post) throws PostTitleDuplicateException {
        throw new RuntimeException();
    }

    public List<String> getAllPostTitles() {
        // TODO: return post titles in the order they should be shown (latest post first)
        throw new RuntimeException();
    }

    public Post getPostFromTitle(String title) {
        throw new RuntimeException();
    }

    public void saveToJson() {
        throw new RuntimeException();
    }

    public void reset() {
        // TODO: delete every post in the collection
        throw new RuntimeException();
    }

}
