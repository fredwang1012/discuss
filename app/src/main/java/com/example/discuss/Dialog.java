package com.example.discuss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dialog extends AppCompatActivity {
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
}
