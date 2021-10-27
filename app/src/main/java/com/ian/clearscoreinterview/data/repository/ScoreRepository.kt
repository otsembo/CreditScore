package com.ian.clearscoreinterview.data.repository

import com.ian.clearscoreinterview.data.remote.dto.CreditReportDto

interface ScoreRepository {

    //retrieve credit report
    suspend fun fetchCreditReport() : CreditReportDto

}