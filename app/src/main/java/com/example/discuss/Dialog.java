package com.example.discuss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Dialog extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        TextView text = findViewById(R.id.textView);
        TextView title = findViewById(R.id.title);

        Button post = findViewById(R.id.button5);

        post.setOnClickListener(view -> {
            String q = text.getText().toString();
            String t = title.getText().toString();
            PostItem p = new PostItem(q, "", true);
            Post post1 = new Post(t, p);
            try {
                MainActivity.allPosts.addPost(post1);
            } catch (PostCollection.PostTitleDuplicateException e) {
                throw new RuntimeException(e);
            }
            finish();
        });
    }
}
