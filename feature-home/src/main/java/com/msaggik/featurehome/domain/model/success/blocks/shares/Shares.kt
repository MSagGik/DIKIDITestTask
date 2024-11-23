package com.msaggik.featurehome.domain.model.success.blocks.shares

data class Shares(
    val id: String,
    val name: String,
    val timeStart: String,
    val timeStop: String,
    val publicTimeStart: String,
    val publicTimeStop: String,
    val discountValue: String,
    val view: String,
    val usedCount: String,
    val companyId: String,
    val icon: String,
    val companyName: String,
    val companyStreet: String,
    val companyHouse: String,
    val companyImage: String
)
