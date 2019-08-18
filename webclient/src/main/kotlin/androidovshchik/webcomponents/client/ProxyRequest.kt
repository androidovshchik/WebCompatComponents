/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.client

import androidovshchik.webcomponents.WebCompatClient
import com.android.volley.AuthFailureError
import com.android.volley.VolleyLog
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import java.io.UnsupportedEncodingException
import java.util.*

class ProxyRequest(method: Int, url: String, var myProxy: WebCompatClient.MyProxy?, future: RequestFuture<String>) : StringRequest(method, url, future) {

    init {
        setShouldCache(false)
    }

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        val headers = HashMap(super.getHeaders())
        if (myProxy != null) {
            try {
                headers.putAll(myProxy!!.headers())
            } catch (e: UnsupportedEncodingException) {
                VolleyLog.e(e, "")
            }

        }
        VolleyLog.d("%s", headers.toString())
        return headers
    }
}