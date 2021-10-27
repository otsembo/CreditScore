package com.ian.clearscoreinterview.fake_sources

import com.ian.clearscoreinterview.data.remote.dto.CoachingSummary
import com.ian.clearscoreinterview.data.remote.dto.CreditReportDto
import com.ian.clearscoreinterview.data.remote.dto.CreditReportInfo
import com.ian.clearscoreinterview.data.repository.ScoreRepository

class FakeScoreRepository : ScoreRepository {

    private val reportInfo = CreditReportInfo(
        changeInLongTermDebt = -327,
        changeInShortTermDebt = 549,
        changedScore = 0,
        clientRef = "CS-SED-655",
        currentLongTermCreditLimit = null,
        currentLongTermCreditUtilisation = null,
        currentLongTermDebt = 549,
        currentLongTermNonPromotionalDebt = 24862,
        currentShortTermCreditLimit = 30600,
        currentShortTermCreditUtilisation = 44,
        currentShortTermDebt = 24682,
        currentShortTermNonPromotionalDebt = 24682,
        daysUntilNextReport = 9,
        equifaxScoreBand = 4,
        equifaxScoreBandDescription = "Excellent",
        hasEverBeenDelinquent = true,
        hasEverDefaulted = false,
        maxScoreValue = 700,
        minScoreValue = 0,
        monthsSinceLastDefaulted = -1,
        monthsSinceLastDelinquent = 1,
        numNegativeScoreFactors = 0,
        numPositiveScoreFactors = 9,
        percentageCreditUsed = 44,
        percentageCreditUsedDirectionFlag = 1,
        score = 514,
        scoreBand = 4,
        status = "MATCH"
    )

    //coaching summary
    private val coachingSummary = CoachingSummary(
        activeChat = true,
        activeTodo = false,
        numberOfCompletedTodoItems = 0,
        numberOfTodoItems = 0,
        selected = true)

    //creditReportDto
    private val reportDto = CreditReportDto(
        accountIDVStatus = "PASS",
        creditReportInfo = reportInfo,
        coachingSummary = coachingSummary,
        dashboardStatus = "PASS",
        personaType = "INEXPERIENCED",
        augmentedCreditScore = null
    )

    //show error
    private var showNetworkError = false

    //loading
    private var isLoading = true

    //network error
    fun showNetworkError(netError: Boolean){
        showNetworkError = netError
    }

    fun isLoading(isLoad: Boolean){
        isLoading = isLoad
    }

    override suspend fun fetchCreditReport(): CreditReportDto {
        return reportDto
    }


}