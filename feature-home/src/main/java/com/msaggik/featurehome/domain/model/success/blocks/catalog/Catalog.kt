package com.msaggik.featurehome.domain.model.success.blocks.catalog

import com.google.gson.annotations.SerializedName
import com.msaggik.featurehome.domain.model.success.blocks.catalog.currency.Currency
import com.msaggik.featurehome.domain.model.success.common.Image

data class Catalog(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("house")
    val house: String?,
    @SerializedName("schedule")
    val schedule: Any,
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?,
    @SerializedName("categories")
    val categories: List<Any>,
    @SerializedName("categories_catalog")
    val categoriesCatalog: List<Any>,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("isMaster")
    val isMaster: Boolean,
    @SerializedName("award")
    val award: String?,
    @SerializedName("vip_tariff")
    val vipTariff: Boolean,
    @SerializedName("reviewCount")
    val reviewCount: String?,
    @SerializedName("backgrounds")
    val backgrounds: List<String>,
    @SerializedName("currency")
    val currency: Currency,
    @SerializedName("master_id")
    val masterId: String?
)
