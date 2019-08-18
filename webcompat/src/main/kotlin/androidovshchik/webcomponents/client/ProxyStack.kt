/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.client

import com.android.volley.Request
import com.android.volley.toolbox.HttpResponse
import com.android.volley.toolbox.HurlStack
import java.net.HttpURLConnection
import java.net.URL

class ProxyStack : HurlStack() {

    override fun executeRequest(request: Request<*>?, additionalHeaders: MutableMap<String, String>?): HttpResponse {
        return super.executeRequest(request, additionalHeaders)
    }

    override fun createConnection(url: URL?): HttpURLConnection {
        return super.createConnection(url)
    }
}