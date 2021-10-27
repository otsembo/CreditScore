package com.ian.clearscoreinterview.domain.use_case

import com.ian.clearscoreinterview.common.Constants
import com.ian.clearscoreinterview.common.Resource
import com.ian.clearscoreinterview.data.remote.dto.toCreditScore
import com.ian.clearscoreinterview.data.repository.ScoreRepository
import com.ian.clearscoreinterview.domain.model.CreditScore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class FetchScoreUseCase
    @Inject constructor(private val repository: ScoreRepository){

        operator fun invoke() : Flow<Resource<CreditScore>> = flow {

            try {
                //start loading
                emit(Resource.Loading<CreditScore>())
                //get score from repo
                val score = repository.fetchCreditReport().creditReportInfo.toCreditScore()
                //success
                emit(Resource.Success<CreditScore>(score))

            }catch (e: HttpException){
                //unexpected error
                emit(Resource.Error<CreditScore>(e.localizedMessage ?: Constants.UNEXPECTED))
            }catch (e: IOException){
                //network error
                emit(Resource.Error<CreditScore>(Constants.NETWORK_IO_ERROR))
            }

        }

    }