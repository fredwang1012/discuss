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

public class PostUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ui);
        Intent intent = getIntent();

        String postTitle = intent.getStringExtra("post title");
        TextView textView = (TextView) findViewById(R.id.postTitle);
        textView.setText(postTitle);

    }

}
