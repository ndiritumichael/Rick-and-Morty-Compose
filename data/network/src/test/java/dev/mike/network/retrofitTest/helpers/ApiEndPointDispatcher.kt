package dev.mike.network.retrofitTest.helpers

import dev.mike.network.JsonData
import dev.mike.network.utils.CHARACTERSPATH
import dev.mike.network.utils.EPISODEID
import dev.mike.network.utils.EPISODESPATH
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

open class ApiEndPointDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        print("The request path is \n \n ${request.path} \n\n why is it failing")

        return when (val path = request.path) {

            "/$CHARACTERSPATH?page=1" -> MockResponse()
                .setResponseCode(200)
                .setBody(JsonData.characterspage1)
            "/$CHARACTERSPATH/?page=2" -> MockResponse()
                .setResponseCode(200)
                .setBody(JsonData.characterspage2)

            "/$CHARACTERSPATH/?page=1000" -> {
                MockResponse()
                    .setResponseCode(404)
                    .setBody("{\"error\":\"There is nothing here.\"}")
            }
            "/$CHARACTERSPATH/1" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(JsonData.mortyDetails)
            }
            "/$CHARACTERSPATH/10000" -> {
                MockResponse()
                    .setResponseCode(404)
                    .setBody("{\"error\":\"There is nothing here.\"}")
            }
            "/$EPISODESPATH?page=1" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(JsonData.episodespage1)
            }

            "/"+ EPISODEID+"28" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(JsonData.episode28)
            }

            else -> {
                MockResponse()
                    .setResponseCode(404)
                    .setBody("{\"error\":\"There is nothing here.\"}")
            }
        }
    }
}
