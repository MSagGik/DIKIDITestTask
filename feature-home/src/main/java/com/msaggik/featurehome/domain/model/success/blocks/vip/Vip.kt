package com.msaggik.featurehome.domain.model.success.blocks.vip

import com.google.gson.annotations.SerializedName
import com.msaggik.featurehome.domain.model.success.common.Image

data class Vip(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: Image,
    @SerializedName("name")
    val name: String,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("award")
    val award: String?,
    @SerializedName("vip_tariff")
    val vipTariff: Boolean
)
