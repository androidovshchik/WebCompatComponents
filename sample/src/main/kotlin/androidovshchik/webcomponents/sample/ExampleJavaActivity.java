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

import androidovshchik.webcomponents.IWebViewListener;
import androidovshchik.webcomponents.WebCompat;
import androidovshchik.webcomponents.WebCompatLayout;

@SuppressLint("Registered")
public class ExampleJavaActivity extends AppCompatActivity {

    @Override
    @SuppressWarnings("ThrowableNotThrown")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebCompatLayout webLayout = new WebCompatLayout(this);
        webLayout.setListener(new IWebViewListener() {

            @Override
            public void onLoadStarted(@NonNull String url, @Nullable Bitmap favicon) {

            }

            @Override
            public void onLoadFinished(@NonNull String url) {

            }

            @Override
            public void onLoadError(@NonNull String url, int code, @NonNull String description) {

            }
        });

        // An example of calling [WebCompatActivity] through builder
        Throwable throwable = new WebCompat.IntentBuilder(getApplicationContext())
            .inputData("https://google.ru")
            .startActivity(getApplicationContext());
    }
}
