package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        String[] postTitles = MainActivity.allPosts.getAllPostTitles().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.activity_posts, postTitles
        );
        posts.setAdapter(adapter);

        posts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adView, View view, int position, long itemID) {
                Intent onClickIntent = new Intent(getApplicationContext(), PostUI.class);
                onClickIntent.putExtra("post title", (String) adView.getItemAtPosition(position));
                startActivity(onClickIntent);
            }
        });

    }
}