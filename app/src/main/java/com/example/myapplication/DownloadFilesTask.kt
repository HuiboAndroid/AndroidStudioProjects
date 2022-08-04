package com.example.myapplication

import android.os.AsyncTask
import android.os.Build
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
            val url = urls.get(i)
            try {
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                val inStr: InputStream = BufferedInputStream(urlConnection.getInputStream())
                totalSize += inStr.toString().length
            } catch (e: Exception) {
                System.out.println(e.message)
            }
        }
        System.out.println(totalSize)
        return totalSize
    }
}