package nl.fontys.stolpersteine.models

import okhttp3.*


class StolpersteineManager {
    val urlString = "http://i490231.hera.fhict.nl/stolpersteine-api/stolpersteine?half"
    val client = OkHttpClient()
}

    /*private fun buildApiRequest(callback: Callback) {

        val request = Request.Builder()
            .url(urlString)
            .build()

        client.newCall(request).enqueue(callback)
    }

    fun makeApiRequest() {
        val request: Request = Request.Builder()
            .url(urlString)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //call.cancel()
                //callback(null)
                Log.d("API_ERROR", e.toString())
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body
                if (responseString != null) {
                    Log.d("API_RESPONSE", responseString.toString())
                    val listType: TypeToken<List<Stolperstein>?> = object : TypeToken<List<Stolperstein>?>() {}
                    val list = Gson().fromJson(responseString.string(), listType)
                    stolpersteine = list
                    //callback(list)
                }
            }
        })
    }
    /*buildApiRequest( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle request failure
                Log.d("API_CALL_ERROR", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                Log.d("API_RESPONSE", responseBody.toString())
                if (responseBody != null) {
                    val list = Klaxon().parseArray<Stolperstein>(responseBody)
                    // Handle response data
                    stolpersteine = list
                }
            }
        })*/
}*/