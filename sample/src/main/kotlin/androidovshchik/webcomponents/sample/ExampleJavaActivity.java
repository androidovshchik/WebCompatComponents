/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.sample;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidovshchik.webcomponents.AppCompatWebLayout;
import androidovshchik.webcomponents.IWebViewListener;

@SuppressLint("Registered")
public class ExampleJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatWebLayout webLayout = new AppCompatWebLayout(this);
        webLayout.setListener(new IWebViewListener() {

            @Override
            public void onPageStarted(@NonNull String url, @Nullable Bitmap favicon) {

            }

            @Override
            public void onPageFinished(@NonNull String url) {

            }

            @Override
            public void onPageError(@NonNull String url, int code, @NonNull String description) {

            }
        });
    }
}
