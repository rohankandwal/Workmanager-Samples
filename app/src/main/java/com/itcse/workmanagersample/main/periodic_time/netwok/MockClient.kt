package com.itcse.workmanagersample.main.periodic_time.netwok

import android.content.Context
import android.util.Log
import com.itcse.workmanagersample.R
import okhttp3.*
import timber.log.Timber
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

/**
 * Class containing a Retrofit mock client for emulating the server.
 * @author Rohan Kandwal on 2019-08-04.
 */
class MockClient (private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()
        val response = readFromFile()

        when (url.encodedPath()) {
            "/getConfig" -> return Response.Builder()
                .code(200)
                .message(response)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(MediaType.parse("application/json"), response.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
            else -> return Response.Builder()
                .code(200)
                .message("Server Updated").request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(MediaType.parse("application/json"), response.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        }
    }


    /**
     * Reads JSON from the api_config_response.json file
     */
    private fun readFromFile(): String {
        var ret = ""

        try {
            val inputStream = context.resources.openRawResource(R.raw.api_config_response)

            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = null;
            val stringBuilder = StringBuilder()

            while ({ receiveString = bufferedReader.readLine(); receiveString }() != null) {
                stringBuilder.append(receiveString)
            }

            inputStream.close()
            ret = stringBuilder.toString()
        } catch (e: FileNotFoundException) {
            Timber.e("File not found: %s", e.toString())
        } catch (e: IOException) {
            Timber.e( "Can not read file: %s", e.toString())
        }

        return ret
    }
}
