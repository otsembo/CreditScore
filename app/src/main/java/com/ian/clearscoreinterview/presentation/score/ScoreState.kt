package com.ian.clearscoreinterview.presentation.score

import com.ian.clearscoreinterview.domain.model.CreditScore

data class ScoreState(
    val isLoading: Boolean = false,
    val creditScore: CreditScore? = null,
    val error: String = ""
)