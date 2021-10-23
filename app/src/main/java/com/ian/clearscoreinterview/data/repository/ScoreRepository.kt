package com.ian.clearscoreinterview.data.repository

import com.ian.clearscoreinterview.data.remote.dto.CreditReportDto

interface ScoreRepository {

    //retrieve coins
    suspend fun fetchCoins() : CreditReportDto

}