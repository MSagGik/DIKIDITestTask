package com.msaggik.featurehome.data.mappers

import com.msaggik.datanetwork.api.dto.HomeResponseDto
import com.msaggik.datanetwork.api.dto.response.error.ErrorHomeResponseDto
import com.msaggik.datanetwork.api.dto.response.success.SuccessHomeResponseDto
import com.msaggik.datanetwork.api.dto.response.success.blocks.BlocksDto
import com.msaggik.datanetwork.api.dto.response.success.blocks.catalog.CatalogDto
import com.msaggik.datanetwork.api.dto.response.success.blocks.shares.SharesDto
import com.msaggik.datanetwork.api.dto.response.success.blocks.vip.VipDto
import com.msaggik.featurehome.domain.model.HomeResponse
import com.msaggik.featurehome.domain.model.error.ErrorHomeResponse
import com.msaggik.featurehome.domain.model.success.SuccessHomeResponse
import com.msaggik.featurehome.domain.model.success.blocks.Blocks
import com.msaggik.featurehome.domain.model.success.blocks.catalog.Catalog
import com.msaggik.featurehome.domain.model.success.blocks.catalog.currency.Currency
import com.msaggik.featurehome.domain.model.success.blocks.shares.Shares
import com.msaggik.featurehome.domain.model.success.blocks.vip.Vip
import com.msaggik.featurehome.domain.model.success.common.Image

object Mappers {

    fun map(homeResponseDto: HomeResponseDto): HomeResponse {
        return with(homeResponseDto) {
            HomeResponse(
                resultCode = resultCode,
                errorHomeResponse = mapErrorResponse(errorHomeResponseDto),
                successHomeResponse = mapSuccessResponse(successHomeResponseDto)
            )
        }
    }

    private fun mapErrorResponse(errorDto: ErrorHomeResponseDto): ErrorHomeResponse {
        return ErrorHomeResponse(
            code = errorDto.code,
            message = errorDto.message ?: ""
        )
    }

    private fun mapSuccessResponse(successDto: SuccessHomeResponseDto): SuccessHomeResponse {
        return SuccessHomeResponse(
            title = successDto.title ?: "",
            image = successDto.image ?: "",
            catalogCount = successDto.catalogCount ?: "",
            blocks = mapBlocks(successDto.blocks),
            order = successDto.order
        )
    }

    private fun mapBlocks(blocksDto: BlocksDto): Blocks {
        return Blocks(
            vip = blocksDto.vip.map { mapVip(it) },
            shares = mapShares(blocksDto.shares),
            examples = blocksDto.examples ?: "",
            examples2 = blocksDto.examples2 ?: "",
            catalog = blocksDto.catalog.map { mapCatalog(it) }
        )
    }

    private fun mapVip(vipDto: VipDto): Vip {
        return Vip(
            id = vipDto.id ?: "",
            image = Image(
                thumb = vipDto.image.thumb,
                origin = vipDto.image.origin
            ),
            name = vipDto.name ?: "",
            categories = vipDto.categories,
            award = vipDto.award ?: "",
            vipTariff = vipDto.vipTariff
        )
    }

    private fun mapShares(sharesDto: SharesDto): Shares {
        return Shares(
            id = sharesDto.id ?: "",
            name = sharesDto.name ?: "",
            timeStart = sharesDto.timeStart ?: "",
            timeStop = sharesDto.timeStop ?: "",
            publicTimeStart = sharesDto.publicTimeStart ?: "",
            publicTimeStop = sharesDto.publicTimeStop ?: "",
            discountValue = sharesDto.discountValue ?: "",
            view = sharesDto.view ?: "",
            usedCount = sharesDto.usedCount ?: "",
            companyId = sharesDto.companyId ?: "",
            icon = sharesDto.icon ?: "",
            companyName = sharesDto.companyName ?: "",
            companyStreet = sharesDto.companyStreet ?: "",
            companyHouse = sharesDto.companyHouse ?: "",
            companyImage = sharesDto.companyImage ?: ""
        )
    }

    private fun mapCatalog(catalogDto: CatalogDto): Catalog {
        return Catalog(
            id = catalogDto.id ?: "",
            name = catalogDto.name ?: "",
            image = Image(
                thumb = catalogDto.image.thumb,
                origin = catalogDto.image.origin
            ),
            street = catalogDto.street ?: "",
            house = catalogDto.house ?: "",
            schedule = catalogDto.schedule,
            lat = catalogDto.lat ?: "",
            lng = catalogDto.lng ?: "",
            categories = catalogDto.categories,
            categoriesCatalog = catalogDto.categoriesCatalog,
            rating = catalogDto.rating,
            isMaster = catalogDto.isMaster,
            award = catalogDto.award ?: "",
            vipTariff = catalogDto.vipTariff,
            reviewCount = catalogDto.reviewCount ?: "",
            backgrounds = catalogDto.backgrounds,
            currency = Currency(
                id = catalogDto.currency.id,
                title = catalogDto.currency.title,
                abbr = catalogDto.currency.abbr
            ),
            masterId = catalogDto.masterId ?: ""
        )
    }
}
