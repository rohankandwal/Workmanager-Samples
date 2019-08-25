package com.itcse.workmanagersample.main.worker.listenable_worker

import android.content.Context
import androidx.concurrent.futures.CallbackToFutureAdapter
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import okhttp3.*
import timber.log.Timber
import java.io.IOException
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import sun.text.normalizer.UTF16.append
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


/**
 * Example of a Listenable Worker.
 *
 * @author Rohan Kandwal on 2019-08-25.
 */
class ListenableWorkerExample(context: Context, workerParameters: WorkerParameters) :
    ListenableWorker(context, workerParameters) {

    override fun startWork(): ListenableFuture<Result> {
        return CallbackToFutureAdapter.getFuture { completer ->
            val callback = object : Callback {
                var successes = 0

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    completer.setException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    Timber.d("Got the data")
                    ++successes
                    if (successes == 100) {
                        completer.set(Result.success())
                    }
                }

            }
            for (i in 0 until 100) {
                downloadAsynchronously("https://www.google.com", callback)
            }
            return@getFuture callback
        }
    }

    private fun downloadAsynchronously(url: String, callback: Callback) {
        // avoid creating several instances, should be singleon
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(callback)
    }

}