package com.ian.clearscoreinterview.data.remote.dto

data class CreditReportDto(

    val accountIDVStatus: String,
    val augmentedCreditScore: Any?,
    val coachingSummary: CoachingSummary,
    val creditReportInfo: CreditReportInfo,
    val dashboardStatus: String,
    val personaType: String

)