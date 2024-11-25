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
import com.msaggik.featurehome.domain.model.success.blocks.shares.Share
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
        return with(errorDto) {
            ErrorHomeResponse(
                code = code,
                message = message ?: ""
            )
        }
    }

    private fun mapSuccessResponse(successDto: SuccessHomeResponseDto): SuccessHomeResponse {
        return with(successDto) {
            SuccessHomeResponse(
                title = title ?: "",
                image = image ?: "",
                catalogCount = catalogCount ?: "",
                blocks = mapBlocks(blocks),
                order = order
            )
        }
    }

    private fun mapBlocks(blocksDto: BlocksDto): Blocks {
        return with(blocksDto) {
            Blocks(
                vip = vip.map { mapVip(it) },
                shares = mapShares(shares),
                examples = examples ?: "",
                examples2 = examples2 ?: "",
                catalog = catalog.map { mapCatalog(it) }
            )
        }
    }

    private fun mapVip(vipDto: VipDto): Vip {
        return with(vipDto) {
            Vip(
                id = id ?: "",
                image = Image(
                    thumb = image.thumb,
                    origin = image.origin
                ),
                name = name ?: "",
                categories = categories,
                award = award ?: "",
                vipTariff = vipTariff
            )
        }
    }

    @Suppress("CyclomaticComplexMethod")
    private fun mapShares(sharesDto: SharesDto): Shares {
        return with(sharesDto) {
            Shares(
                list = list.map { shareDto ->
                    with(shareDto) {
                        Share(
                            id = id ?: "",
                            name = name ?: "",
                            timeStart = timeStart ?: "",
                            timeStop = timeStop ?: "",
                            publicTimeStart = publicTimeStart ?: "",
                            publicTimeStop = publicTimeStop ?: "",
                            discountValue = discountValue ?: "",
                            view = view ?: "",
                            usedCount = usedCount ?: "",
                            companyId = companyId ?: "",
                            icon = icon ?: "",
                            companyName = companyName ?: "",
                            companyStreet = companyStreet ?: "",
                            companyHouse = companyHouse ?: "",
                            companyImage = companyImage ?: ""
                        )
                    }
                },
                count = count
            )

        }
    }

    @Suppress("CyclomaticComplexMethod")
    private fun mapCatalog(catalogDto: CatalogDto): Catalog {
        return with(catalogDto) {
            Catalog(
                id = id ?: "",
                name = name ?: "",
                image = Image(
                    thumb = image.thumb,
                    origin = image.origin
                ),
                street = street ?: "",
                house = house ?: "",
                schedule = schedule,
                lat = lat ?: "",
                lng = lng ?: "",
                categories = categories,
                categoriesCatalog = categoriesCatalog,
                rating = rating,
                isMaster = isMaster,
                award = award ?: "",
                vipTariff = vipTariff,
                reviewCount = reviewCount ?: "",
                backgrounds = backgrounds,
                currency = Currency(
                    id = currency.id,
                    title = currency.title,
                    abbr = currency.abbr
                ),
                masterId = masterId ?: ""
            )
        }
    }
}
