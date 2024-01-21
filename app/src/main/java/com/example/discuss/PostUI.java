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

import java.util.List;

public class PostUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ui);
        Intent intent = getIntent();

        String postTitle = intent.getStringExtra("post title");
        TextView titleView = (TextView) findViewById(R.id.postTitle);
        titleView.setText(Helpers.breakStringLines(postTitle, 34));
        Post post = MainActivity.allPosts.getPostFromTitle(postTitle);

        ListView posts = (ListView) findViewById(R.id.replies);

        List<PostItem> replies = post.getReplies();
        Helpers.FormattedString[] postContent = new Helpers.FormattedString[replies.size()];

        for (int i = 0; i < replies.size(); i++) {
            postContent[i] = new Helpers.FormattedString(
                    replies.get(i).getContent(), 16, Helpers.FormattedString.Mode.LINE_BROKEN
            );
        }

        ArrayAdapter<Helpers.FormattedString> adapter = new ArrayAdapter<>(
                this, R.layout.activity_posts, postContent
        );

        PostItem postQuestion = post.getQuestion();
        TextView questionView = (TextView) findViewById(R.id.postQuestion);
        questionView.setText(postQuestion.toString());

        Button btn = findViewById(R.id.button4);
        btn.setOnClickListener(view -> {
            Intent nextIntent = new Intent(this, PostReplyUI.class);
            nextIntent.putExtra("post title", postTitle);
            startActivity(nextIntent);
        });
    }

}
