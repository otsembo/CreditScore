package com.ian.clearscoreinterview.use_cases

import com.ian.clearscoreinterview.common.Constants
import com.ian.clearscoreinterview.common.Resource
import com.ian.clearscoreinterview.data.remote.dto.CreditReportDto
import com.ian.clearscoreinterview.data.remote.dto.toCreditScore
import com.ian.clearscoreinterview.data.repository.ScoreRepository
import com.ian.clearscoreinterview.domain.model.CreditScore
import com.ian.clearscoreinterview.domain.repository.ScoreRepositoryImpl
import com.ian.clearscoreinterview.domain.use_case.FetchScoreUseCase
import com.ian.clearscoreinterview.fake_sources.FakeFetchScoreUseCase
import com.ian.clearscoreinterview.fake_sources.FakeScoreRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class FetchScoreUseCaseTest {

    //variables
    private lateinit var repository: FakeScoreRepository
    private lateinit var useCase: FakeFetchScoreUseCase

    @Before
    fun setUp(){
        //init variables
        repository = FakeScoreRepository()

    }


    //retrieved data correctly
    @Test
    fun `data received successfully`() = runBlocking {
        useCase = FakeFetchScoreUseCase(repository)
        //use case emission
        useCase.invoke().collect {
            assertEquals(Resource.Success(CreditScore(0,0)).javaClass, it.javaClass)
        }

    }

    @Test
    fun `data is still loading`() = runBlocking {
        useCase = FakeFetchScoreUseCase(repository, isLoading = true)
        //emission
        useCase.invoke().collect{
            assertEquals(Resource.Loading<CreditScore>().javaClass, it.javaClass)
        }
    }

    @Test
    fun `error while fetching`() = runBlocking {
        useCase = FakeFetchScoreUseCase(repository, showError = true)
        useCase.invoke().collect{
            assertEquals(Resource.Error<CreditScore>(Constants.UNEXPECTED).javaClass, it.javaClass)
        }
    }




}