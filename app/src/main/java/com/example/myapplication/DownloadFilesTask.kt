package com.example.myapplication

import android.os.AsyncTask
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class DownloadFilesTask() : AsyncTask<URL, Integer, Long>() {
    override fun doInBackground(vararg urls: URL): Long? {
        System.out.println("doInBackground")
        val count: Int = urls.size
        var totalSize: Long = 0
        for (i in 0 until count) {
            val url = URL("http://www.android.com/")
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                val inStr: InputStream = BufferedInputStream(urlConnection.getInputStream())
            } finally {
                urlConnection.disconnect()
            }
        }
        System.out.println(totalSize)
        return totalSize
    }
}