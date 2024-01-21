package com.example.discuss;

import com.example.discuss.persistance.JsonWriter;
import com.example.discuss.persistance.Writable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PostCollection implements Writable {

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

    public void usedToAddTitleListJustForJson(List<String> titles) {
        this.titles = titles;
    }

    public void saveToJson() {
        final String JSON_STORE = "./data/PostCollection.json";
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        jsonWriter.write(this);
        jsonWriter.close();
    }

    public void reset() {
        // TODO: delete every post in the collection
        posts.clear();
        titles.clear();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("postList", postToJson());
            json.put("titleList",titleToJson());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private JSONArray postToJson() {

        JSONArray jsonArray = new JSONArray();

        for (Post post : posts) {
            jsonArray.put(post.toJson());
        }

        return jsonArray;
    }

    private JSONArray titleToJson() {

        JSONArray jsonArray = new JSONArray();

        for (String title : titles) {
            jsonArray.put(title);
        }

        return jsonArray;
    }

}
