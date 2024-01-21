package com.example.discuss.persistance;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.discuss.Post;
import com.example.discuss.PostCollection;
import com.example.discuss.PostItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads PostCollection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PostCollection read() throws IOException {
        String jsonData = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            jsonData = readFile(source);
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonData);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return parsePostCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s));
            }
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses PostCollection from JSON object and returns it
    private PostCollection parsePostCollection(JSONObject jsonObject) {
        PostCollection postCollection = new PostCollection();
        addPosts(postCollection, jsonObject);
        addTitles(postCollection,jsonObject);
        return postCollection;
    }

    private void addPosts(PostCollection postCollection, JSONObject jsonObject) {

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("postList");
            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject nextPost = (JSONObject) jsonArray.getJSONObject(i);
                addPost(postCollection,nextPost);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void addPost(PostCollection postCollection, JSONObject nextPost) {

        try {
            String title = nextPost.getString("title");
            PostItem question = parsePostItem(nextPost.getJSONObject("question"));
            List<PostItem> replies = parseListPostItem(nextPost.getJSONArray("replies"));
            Post postToBeAdded = new Post(title,question);
            postToBeAdded.useToAddRepliesForJsonOnly(replies);
            try {
                postCollection.addPost(postToBeAdded);
            } catch (PostCollection.PostTitleDuplicateException e) {
                throw new RuntimeException(e);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }




    }



    private void addTitles(PostCollection postCollection, JSONObject jsonObject) {

        List<String> titles = new ArrayList<>();
        try {
            JSONArray titleArray = jsonObject.getJSONArray("titleList");
            for (int i=0; i < titleArray.length(); i++) {
                titles.add(titleArray.getString(i));
            }

            postCollection.usedToAddTitleListJustForJson(titles);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private PostItem parsePostItem(JSONObject postItemObject) {

        try {
            String content = postItemObject.getString("content");
            String poster = postItemObject.getString("poster");
            String localDateTimeString = postItemObject.getString("LocalDateTime");
            boolean isQuestion = postItemObject.getBoolean("questioned?");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                PostItem postItem = new PostItem(content,poster,
                        LocalDateTime.parse(localDateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        ,isQuestion);
                return postItem;
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        throw new RuntimeException();

    }

    private List<PostItem> parseListPostItem(JSONArray repliesArray) {

        List<PostItem> replies = new ArrayList<>();

        for (int i=0; i < repliesArray.length(); i++) {
            try {
                JSONObject nextPostItem = (JSONObject) repliesArray.getJSONObject(i);
                PostItem postItem = parsePostItem(nextPostItem);
                replies.add(postItem);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        return replies;


    }
}
