/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class ExampleJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*WebCompatLayout webLayout = new WebCompatLayout(this);
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
        });*/
    }
}
