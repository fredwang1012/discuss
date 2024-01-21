package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UserInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.DISPLAY_NAME);
//        TextView textView = (TextView) findViewById(R.id.displayName);
//        textView.setText(text);

        ListView posts = (ListView) findViewById(R.id.posts);
        String[] postTitles = MainActivity.allPosts.getAllPostTitles().toArray(new String[0]);

        Helpers.FormattedString[] formattedPostTitles = new Helpers.FormattedString[postTitles.length];
        for (int i = 0; i < postTitles.length; i++) {
            formattedPostTitles[i] = new Helpers.FormattedString(
                    postTitles[i], 16, Helpers.FormattedString.Mode.LINE_BROKEN
            );
        }

        ArrayAdapter<Helpers.FormattedString> adapter = new ArrayAdapter<>(
                this, R.layout.activity_posts, formattedPostTitles
        );
        posts.setAdapter(adapter);

        posts.setOnItemClickListener((adView, view, position, itemID) -> {
            Intent onClickIntent = new Intent(getApplicationContext(), PostUI.class);
            onClickIntent.putExtra(
                    "post title",
                    ((Helpers.FormattedString) adView.getItemAtPosition(position)).originalString
            );
            onClickIntent.putExtra(MainActivity.DISPLAY_NAME, text);
            startActivity(onClickIntent);
        });

        TextView footer = (TextView) findViewById(R.id.footer);


        Button btn = (Button) findViewById(R.id.button2);


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openDialog(text);
            }
        });

    }

    private void openDialog(String text) {
        Intent intent = new Intent(this, Dialog.class);
        intent.putExtra(MainActivity.DISPLAY_NAME, text);
        startActivity(intent);
    }
}