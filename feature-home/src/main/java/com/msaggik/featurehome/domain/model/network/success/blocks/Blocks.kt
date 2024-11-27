package com.msaggik.featurehome.domain.model.network.success.blocks

import com.msaggik.featurehome.domain.model.network.success.blocks.catalog.Catalog
import com.msaggik.featurehome.domain.model.network.success.blocks.shares.Shares
import com.msaggik.featurehome.domain.model.network.success.blocks.vip.Vip

data class Blocks(
    val vip: List<Vip>,
    val shares: Shares,
    val examples: String,
    val examples2: String,
    val catalog: List<Catalog>,
)
