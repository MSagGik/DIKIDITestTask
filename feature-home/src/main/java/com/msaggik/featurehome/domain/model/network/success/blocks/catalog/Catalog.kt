package com.msaggik.featurehome.domain.model.network.success.blocks.catalog

import com.msaggik.featurehome.domain.model.network.success.blocks.catalog.currency.Currency
import com.msaggik.featurehome.domain.model.network.success.common.Image

data class Catalog(
    val id: String,
    val name: String,
    val image: Image,
    val street: String,
    val house: String,
    val schedule: Any,
    val lat: String,
    val lng: String,
    val categories: List<Any>,
    val categoriesCatalog: List<Any>,
    val rating: Double,
    val isMaster: Boolean,
    val award: String,
    val vipTariff: Boolean,
    val reviewCount: String,
    val backgrounds: List<String>,
    val currency: Currency,
    val masterId: String
)
