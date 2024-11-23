package com.msaggik.datanetwork.api.dto.response.success.blocks

import com.google.gson.annotations.SerializedName
import com.msaggik.datanetwork.api.dto.response.success.blocks.catalog.CatalogDto
import com.msaggik.datanetwork.api.dto.response.success.blocks.shares.SharesDto
import com.msaggik.datanetwork.api.dto.response.success.blocks.vip.VipDto

data class BlocksDto(
    @SerializedName("vip")
    val vip: List<VipDto>,
    @SerializedName("shares")
    val shares: SharesDto,
    @SerializedName("examples")
    val examples: String,
    @SerializedName("examples2")
    val examples2: String,
    @SerializedName("catalog")
    val catalog: List<CatalogDto>,
)
