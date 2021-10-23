package com.ian.clearscoreinterview.domain.repository

import com.ian.clearscoreinterview.data.remote.CreditAPI
import com.ian.clearscoreinterview.data.remote.dto.CreditReportDto
import com.ian.clearscoreinterview.data.repository.ScoreRepository
import javax.inject.Inject

class ScoreRepositoryImpl
    @Inject constructor(private val api:CreditAPI) : ScoreRepository{

    override suspend fun fetchCoins(): CreditReportDto {
        return api.fetchScore()
    }
}
