package com.example.discuss;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Dialog extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        TextView text = findViewById(R.id.textView);
        TextView title = findViewById(R.id.title);

        Button post = findViewById(R.id.button5);
        String username = getIntent().getStringExtra(MainActivity.DISPLAY_NAME);

        post.setOnClickListener(view -> {
            String q = text.getText().toString();
            String t = title.getText().toString();
            PostItem p = new PostItem(q, username, true);
            Post post1 = new Post(t, p);
            String chatGPT = callAPI(q);
            PostItem chat = new PostItem(chatGPT, "chatGPT", false);
            post1.addReplies(chat);
            try {
                MainActivity.allPosts.addPost(post1);
                Intent newIntent = new Intent(this, UserInterface.class);
                newIntent.putExtra(MainActivity.DISPLAY_NAME, username);
                startActivity(newIntent);
            } catch (PostCollection.PostTitleDuplicateException e) {
                Toast.makeText(
                        Dialog.this, "POST WITH THIS TITLE ALREADY EXISTS", Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    public String callAPI(String question) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String responseBody = "";
        String answer = "";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "  {"
                + "    \"role\": \"system\","
                + "    \"content\": \"" + question + "\""
                + "  }"
                + "]"
                + "}");

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + "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")
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            responseBody = response.body().string();

        }  catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Parse JSON
            JSONObject jsonObject = new JSONObject(responseBody);

            // Extract content value
            answer = jsonObject
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");




        } catch (Exception e) {
            e.printStackTrace();
        }

        return answer;
    }
}
