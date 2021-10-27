package com.ian.clearscoreinterview.fake_sources

import com.ian.clearscoreinterview.common.Constants
import com.ian.clearscoreinterview.common.Resource
import com.ian.clearscoreinterview.data.remote.dto.toCreditScore
import com.ian.clearscoreinterview.domain.model.CreditScore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeFetchScoreUseCase (private val fakeScoreRepository: FakeScoreRepository, private val showError:Boolean = false, private val isLoading:Boolean = false) {

    operator fun invoke() : Flow<Resource<CreditScore>> = flow{

        //loading
        if(showError){
           fakeScoreRepository.showNetworkError(true)
            //unexpected error
            emit(Resource.Error<CreditScore>(Constants.UNEXPECTED))

        }else{
            if(isLoading) {
                fakeScoreRepository.isLoading(true)
                //start loading
                emit(Resource.Loading<CreditScore>())
            }else{
                //get score from repo
                val score = fakeScoreRepository.fetchCreditReport().creditReportInfo.toCreditScore()
                //success
                emit(Resource.Success<CreditScore>(score))
            }
        }

    }

}