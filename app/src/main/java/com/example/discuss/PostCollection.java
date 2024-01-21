package com.example.discuss;

import android.os.Environment;

import com.example.discuss.persistance.JsonReader;
import com.example.discuss.persistance.JsonWriter;
import com.example.discuss.persistance.Writable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostCollection implements Writable {

    private List<Post> posts;
    private List<String> titles;
    private final String JSON_STORE = Environment.getExternalStorageDirectory().toString() + "/" + Environment.DIRECTORY_DOWNLOADS + "/PostCollection.json";

    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    public static class PostTitleDuplicateException extends Exception {
        // nothing
    }

    public PostCollection(boolean shouldCallRead) {
        // TODO: read all posts from JSON
        //throw new RuntimeException();

        PostCollection postCollection = readFromJson();

        posts = postCollection.getPostList();
        titles = postCollection.getAllPostTitles();




    }

    public PostCollection() {
        posts = new ArrayList<>();
        titles = new ArrayList<>();
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

    public List<Post> getPostList() {
        return posts;
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
//        File root = new File(Environment.getExternalStorageDirectory(),"PostCollection.json");
//        final String JSON_STORE = "PostCollection.json";


        try {
            jsonWriter.open();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        jsonWriter.write(this);
        jsonWriter.close();
    }

    public PostCollection readFromJson() {
        try {
            return jsonReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
