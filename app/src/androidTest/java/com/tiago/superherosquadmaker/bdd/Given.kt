package com.tiago.superherosquadmaker.bdd

import com.tiago.superherosquadmaker.responses.ApiResponse
import com.tiago.superherosquadmaker.rules.MockServerRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.lang.Thread.sleep

class Given(private val mockServerRule: MockServerRule) {


    fun apiReturnsSuperheroes(){
        mockServerRule.mockServer.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(ApiResponse.superheroesResponse))

        sleep(5000) //TODO implement idling resource
    }

}
