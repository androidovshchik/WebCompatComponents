/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.remote;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.RequestFuture;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

@SuppressWarnings("unused")
public class MyPostRequest extends MyStringRequest {

    private final Map<String, String> headers;

    private final String body;

    public MyPostRequest(String url, RequestFuture<String> future) {
        this(url, null, null, null, future);
    }

    public MyPostRequest(String url, String body, RequestFuture<String> future) {
        this(url, body, null, null, future);
    }

    public MyPostRequest(String url, String body, Map<String, String> headers, RequestFuture<String> future) {
        this(url, body, headers, null, future);
    }

    public MyPostRequest(String url, String body, Map<String, String> headers, HttpClient.MyProxy myProxy, RequestFuture<String> future) {
        super(POST, url, myProxy, future);
        this.headers = headers;
        this.body = body;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = (HashMap<String, String>) super.getHeaders();
        if (this.headers != null) {
            headers.putAll(this.headers);
        }
        return headers;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return body != null ? body.getBytes("UTF-8") : super.getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return super.getBody();
        }
    }
}