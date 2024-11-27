package com.msaggik.featurehome.domain.model.network.success.blocks.vip

import com.msaggik.featurehome.domain.model.network.success.common.Image

data class Vip(
    val id: String,
    val image: Image,
    val name: String,
    val categories: List<String>,
    val award: String?,
    val vipTariff: Boolean
)
