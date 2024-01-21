package com.example.discuss;

import java.util.ArrayList;
import java.util.List;

public class PostCollection {

    private List<Post> posts;
    private List<String> titles;

    public static class PostTitleDuplicateException extends Exception {
        // nothing
    }

    public PostCollection() {
        // TODO: read all posts from JSON
        posts = new ArrayList<>();
        titles = new ArrayList<>();
        //throw new RuntimeException();
    }

    public void addPost(Post post) throws PostTitleDuplicateException {
        String title = post.getTitle();

        if (titles.contains(title)) {
            throw new PostTitleDuplicateException();
        } else {
            posts.add(post);
            titles.add(0,title);

        }
    }

    public List<String> getAllPostTitles() {
        // TODO: return post titles in the order they should be shown (latest post first)
        return this.titles;
    }

    public Post getPostFromTitle(String title) {

        int indexTitle = titles.indexOf(title);

        int indexPost = posts.size()-1-indexTitle;

        return posts.get(indexPost);


    }

    public void saveToJson() {
        //throw new RuntimeException();
    }

    public void reset() {
        // TODO: delete every post in the collection
        posts.clear();
        titles.clear();
    }

}
