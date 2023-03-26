package com.app.learnau;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewWeb extends AppCompatActivity {
    WebView website;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_web);
        website = findViewById(R.id.viewweb);
        website.getSettings().setJavaScriptEnabled(true);


        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading .......");

        website.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }

        });
        website.loadUrl("https://www.andhrauniversity.edu.in/" );
    }
}