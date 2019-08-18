/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.remote;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.RequestFuture;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.GET;

@SuppressWarnings("unused")
public class MyGetRequest extends MyStringRequest {

    private final Map<String, String> headers;

    public MyGetRequest(String url, RequestFuture<String> future) {
        this(url, null, null, future);
    }

    public MyGetRequest(String url, Map<String, String> headers, RequestFuture<String> future) {
        this(url, headers, null, future);
    }

    public MyGetRequest(String url, Map<String, String> headers, HttpClient.MyProxy myProxy, RequestFuture<String> future) {
        super(GET, url, myProxy, future);
        this.headers = headers;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = (HashMap<String, String>) super.getHeaders();
        if (this.headers != null) {
            headers.putAll(this.headers);
        }
        return headers;
    }
}