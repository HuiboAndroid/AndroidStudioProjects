package com.example.myapplication

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadFilesTask() : AsyncTask<URL, Integer, String>() {
    var delegate: AsyncResponse? = null

    override fun doInBackground(vararg urls: URL): String? {
        System.out.println("doInBackground")
        val count: Int = urls.size
        var totalSize: Long = 0
        var inputLine: String = ""
        for (i in 0 until count) {
            val url = urls.get(i)
            System.out.println(i)
            try {
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                val inStr: InputStreamReader = InputStreamReader(urlConnection.getInputStream())
                val inBuffReader = BufferedReader( inStr )
                System.out.println("readline")
                inputLine = inBuffReader.readLine()
                System.out.println(inBuffReader.readLine())
                inBuffReader.close()
                totalSize += inStr.toString().length
            } catch (e: Exception) {
                System.out.println("exception")
                System.out.println(e.stackTrace)
                System.out.println(e.message)
            }
        }
        System.out.println(inputLine)
        return inputLine
    }

    protected override fun onPostExecute(result: String?) {
        // Update activity UI
        System.out.println("onPostExecute")
        System.out.println(result)
        delegate?.processFinish(result);
    }
}