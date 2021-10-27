package com.ian.clearscoreinterview.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.clearscoreinterview.common.Constants
import com.ian.clearscoreinterview.common.Resource
import com.ian.clearscoreinterview.domain.model.CreditScore
import com.ian.clearscoreinterview.domain.use_case.FetchScoreUseCase
import com.ian.clearscoreinterview.presentation.score.ScoreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val fetchScoreUseCase: FetchScoreUseCase
    ) : ViewModel(){


    //state of the score
    private val _state = MutableLiveData<ScoreState>()
    val state : LiveData<ScoreState>
        get() = _state

        init {
            fetchScore()
        }

        //invoke the fetch score use case
        fun fetchScore(){
            //call use case
            fetchScoreUseCase().onEach { resource ->
                when(resource){
                    is Resource.Success -> {
                        //set values
                        _state.value = ScoreState(
                            creditScore = resource.data ?: CreditScore(0, 0)
                        )

                    }
                    is Resource.Error -> {
                        //set values
                        _state.value = ScoreState(
                            error = resource.message ?: Constants.UNEXPECTED
                        )
                    }
                    is Resource.Loading -> {
                        //set values
                        _state.value = ScoreState(
                            isLoading = true
                        )
                    }

                }

            }.launchIn(viewModelScope)
        }

}