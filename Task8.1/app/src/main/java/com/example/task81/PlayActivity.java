package com.example.task81;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.clearCache(true);

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");

        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.loadUrl(url);
    }
}



