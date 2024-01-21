package com.example.discuss;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostReplyUI extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_reply_ui);
        Intent intent = getIntent();

        String postTitle = intent.getStringExtra("post title");
        Post post = MainActivity.allPosts.getPostFromTitle(postTitle);
        String poster = intent.getStringExtra("poster");

        TextView text = findViewById(R.id.editTextText);

        Button btn = findViewById(R.id.button3);

        btn.setOnClickListener(view -> {
            String q = text.getText().toString();
            PostItem p = new PostItem(q, poster, false);
            post.addReplies(p);
            Intent intent1 = new Intent(this, PostUI.class);
            intent1.putExtra("post title", postTitle);
            startActivity(intent1);
        });
    }

}
