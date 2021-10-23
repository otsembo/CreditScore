package com.ian.clearscoreinterview.data.remote

import com.ian.clearscoreinterview.data.remote.dto.CreditReportDto
import retrofit2.http.GET

interface CreditAPI {

    //retrieve data from endpoint
    @GET("/endpoint.json")
    suspend fun fetchScore() : CreditReportDto

}