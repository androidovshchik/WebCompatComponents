/*
 * AppCompatWebComponents (https://github.com/androidovshchik/AppCompatWebComponents)
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.webcomponents

import android.content.Context
import androidovshchik.webcomponents.client.ProxyRequest
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import java.io.BufferedInputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

class WebCompatClient @JvmOverloads constructor(context: Context, builder: Builder = Builder().create()) {

    protected var queue: RequestQueue

    private val timeout: Int
    private val userAgent: String? = null

    init {
        timeout = builder.timeout
        if (builder.certificate <= 0) {
            queue = Volley.newRequestQueue(context, ProxyStack(builder.userAgent))
            return
        }
        var sf: SSLSocketFactory? = null
        try {
            //https://stackoverflow.com/questions/28191023/ssl-pinning-with-volley-network-library-on-android
            //https://stackoverflow.com/questions/39264056/android-java-security-cert-certpathvalidatorexception-trust-anchor-for-certific
            val cf = CertificateFactory.getInstance("X.509")
            val caInput = BufferedInputStream(context.resources.openRawResource(builder.certificate))
            val ca = cf.generateCertificate(caInput)
            caInput.close()
            val keyStoreType = KeyStore.getDefaultType()
            val trusted = KeyStore.getInstance(keyStoreType)
            trusted.load(null, null)
            trusted.setCertificateEntry("ca", ca)
            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
            val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
            tmf.init(trusted)
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, tmf.trustManagers, null)
            sf = sslContext.socketFactory
        } catch (e: Exception) {
            VolleyLog.e(e, "")
        }

        queue = Volley.newRequestQueue(context, ProxyStack(builder.userAgent, null, sf))
    }

    @JvmOverloads
    operator fun get(url: String, headers: Map<String, String>? = null, myProxy: WebCompatClient.MyProxy? = null): String? {
        val future = RequestFuture.newFuture<String>()
        return execute(MyGetRequest(url, headers, myProxy, future), future)
    }

    @JvmOverloads
    fun post(url: String, body: String? = null, headers: Map<String, String>? = null, myProxy: WebCompatClient.MyProxy? = null): String? {
        val future = RequestFuture.newFuture<String>()
        return execute(MyPostRequest(url, body, headers, myProxy, future), future)
    }

    fun <T> execute(myRequest: ProxyRequest<T>, future: RequestFuture<T>): T? {
        queue.add<String>(myRequest)
        try {
            return future.get(timeout.toLong(), TimeUnit.SECONDS)
        } catch (e: Exception) {
            VolleyLog.e(e, "")
        }

        return null
    }

    fun release() {
        queue.stop()
    }

    class Builder {

        private var certificate = 0
        private var timeout = 30
        private var userAgent: String? = null

        fun certificate(rawId: Int): Builder {
            certificate = rawId
            return this
        }

        fun timeout(seconds: Int): Builder {
            timeout = seconds
            return this
        }

        fun userAgent(value: String): Builder {
            userAgent = value
            return this
        }

        fun create(): Builder {
            return this
        }
    }

    companion object {

        init {
            VolleyLog.DEBUG = BuildConfig.DEBUG
        }
    }
}