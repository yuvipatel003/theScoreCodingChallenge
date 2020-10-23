package com.yuvrajpatel.thescoretask

import com.yuvrajpatel.thescoretask.model.Team
import com.yuvrajpatel.thescoretask.retrofit.ApiInterface
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


/**
 * NetworkCall unit test, which will execute on the development machine (host).
 * to test network call api (Retrofit)
 */
class NetworkCallUnitTest {

    // Mock Server to make network call
    val mockWebServer = MockWebServer()
    lateinit var retrofit: Retrofit

    @Before
    fun before(){
        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://raw.githubusercontent.com/scoremedia/nba-team-viewer/master/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Test
    @Throws(IOException::class)
    fun test_apiCall_response_JSON_isCorrect() {

        // Set a response for retrofit to handle.
        // On network call it will return (Mocked)correctResponse as network response
        mockWebServer.enqueue(MockResponse().setBody(dummyCorrectResponse))
        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        // call apiInterface method to get list of teams
        val call= apiInterface.getTeamList()

        // Network call executed successfully
        assertTrue(call.execute() != null)
        // Get correct response from network call

        //Finish web server
        mockWebServer.shutdown()
    }


    @Test
    @Throws(IOException::class)
    fun test_apiCall_response_JSON_isInCorrect() {

        // Set a response for retrofit to handle.
        // On network call it will return (Mocked)correctResponse as network response
        mockWebServer.enqueue(MockResponse().setBody(dummyIncorrectResponse))
        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

        // call apiInterface method to get list of teams
        val call : Call<List<Team>> = apiInterface.getTeamList()

        assertNotEquals(call.execute().body(), dummyCorrectResponse)

        //Finish web server
        mockWebServer.shutdown()
    }

    @After
    fun after(){

    }
}