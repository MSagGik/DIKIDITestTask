package com.msaggik.featurehome.domain.model.success.blocks

import com.google.gson.annotations.SerializedName
import com.msaggik.featurehome.domain.model.success.blocks.catalog.Catalog
import com.msaggik.featurehome.domain.model.success.blocks.shares.Shares
import com.msaggik.featurehome.domain.model.success.blocks.vip.Vip

data class Blocks(
    @SerializedName("vip")
    val vip: List<Vip>,
    @SerializedName("shares")
    val shares: Shares,
    @SerializedName("examples")
    val examples: String,
    @SerializedName("examples2")
    val examples2: String,
    @SerializedName("catalog")
    val catalog: List<Catalog>,
)
