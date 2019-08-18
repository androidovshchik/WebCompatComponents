/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents.models

import android.util.Base64
import androidovshchik.webcomponents.WebCompatClient
import java.io.UnsupportedEncodingException
import java.net.Proxy
import java.util.*

class WebProxy {

    private var proxyType: Proxy.Type = Proxy.Type.HTTP
    private var proxyHost = "http://0"
    private var proxyPort = 80
    private var proxyLogin: String? = null
    private var proxyPassword: String? = null

    fun type(type: Proxy.Type): WebCompatClient.MyProxy {
        proxyType = type
        return this
    }

    fun host(host: String): WebCompatClient.MyProxy {
        proxyHost = host
        return this
    }

    fun port(port: Int): WebCompatClient.MyProxy {
        proxyPort = port
        return this
    }

    fun login(login: String): WebCompatClient.MyProxy {
        proxyLogin = login
        return this
    }

    fun password(password: String): WebCompatClient.MyProxy {
        proxyPassword = password
        return this
    }

    fun create(): WebCompatClient.MyProxy {
        return this
    }

    @Throws(UnsupportedEncodingException::class)
    fun headers(): Map<String, String> {
        val headers = HashMap<String, String>()
        if (proxyLogin != null && proxyPassword != null) {
            val credentials = Base64.encodeToString("$proxyLogin:$proxyPassword"
                .toByteArray(charset("UTF-8")), Base64.NO_WRAP)
            headers["Authenticate"] = "Basic $credentials"
            headers["Authorization"] = "Basic $credentials"
            headers["Proxy-Authorization"] = "Basic $credentials"
            headers["Proxy-Authenticate"] = "Basic $credentials"
            headers["WWW-Authorization"] = "Basic $credentials"
            headers["WWW-Authenticate"] = "Basic $credentials"
        }
        return headers
    }

    override fun toString(): String {
        return "WebProxy{" +
            "proxyHost='" + proxyHost + '\''.toString() +
            ", proxyPort=" + proxyPort +
            ", proxyLogin='" + proxyLogin + '\''.toString() +
            ", proxyPassword='" + proxyPassword + '\''.toString() +
            ", proxyType=" + proxyType +
            '}'.toString()
    }
}