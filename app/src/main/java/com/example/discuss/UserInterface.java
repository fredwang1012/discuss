package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.DISPLAY_NAME);
        TextView textView = (TextView) findViewById(R.id.displayName);
        textView.setText(text);

        ListView posts = (ListView) findViewById(R.id.posts);
        String[] postTitles = {"a", "b", "c"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.activity_posts, postTitles
        );
        posts.setAdapter(adapter);

    }
}