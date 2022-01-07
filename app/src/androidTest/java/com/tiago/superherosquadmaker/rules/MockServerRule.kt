package com.tiago.superherosquadmaker.rules

import com.tiago.superherosquadmaker.responses.ApiResponse
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.rules.ExternalResource

class MockServerRule : ExternalResource() {


    lateinit var mockServer: MockWebServer

    @Before
    override fun before() {

        mockServer = MockWebServer()
        mockServer.start(8080)
    }

    @After
    override fun after() {
        mockServer.shutdown()
    }

}