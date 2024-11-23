package com.msaggik.featurehome.data.mappers

import com.msaggik.datanetwork.api.dto.HomeResponseDto
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
                errorHomeResponse = ErrorHomeResponse(
                    code = errorHomeResponseDto.code,
                    message = errorHomeResponseDto.message ?:""
                ),
                successHomeResponse = SuccessHomeResponse(
                    title = successHomeResponseDto.title ?:"",
                    image = successHomeResponseDto.image ?:"",
                    catalogCount = successHomeResponseDto.catalogCount ?:"",
                    blocks = Blocks(
                        vip = successHomeResponseDto.blocks.vip.map { vipDto ->
                            Vip(
                                id = vipDto.id ?:"",
                                image = Image(
                                    thumb = vipDto.image.thumb,
                                    origin = vipDto.image.origin
                                ),
                                name = vipDto.name ?:"",
                                categories = vipDto.categories,
                                award = vipDto.award ?:"",
                                vipTariff = vipDto.vipTariff
                            )
                        },
                        shares = Shares(
                            id = successHomeResponseDto.blocks.shares.id ?:"",
                            name = successHomeResponseDto.blocks.shares.name ?:"",
                            timeStart = successHomeResponseDto.blocks.shares.timeStart ?:"",
                            timeStop = successHomeResponseDto.blocks.shares.timeStop ?:"",
                            publicTimeStart = successHomeResponseDto.blocks.shares.publicTimeStart ?:"",
                            publicTimeStop = successHomeResponseDto.blocks.shares.publicTimeStop ?:"",
                            discountValue = successHomeResponseDto.blocks.shares.discountValue ?:"",
                            view = successHomeResponseDto.blocks.shares.view ?:"",
                            usedCount = successHomeResponseDto.blocks.shares.usedCount ?:"",
                            companyId = successHomeResponseDto.blocks.shares.companyId ?:"",
                            icon = successHomeResponseDto.blocks.shares.icon ?:"",
                            companyName = successHomeResponseDto.blocks.shares.companyName ?:"",
                            companyStreet = successHomeResponseDto.blocks.shares.companyStreet ?:"",
                            companyHouse = successHomeResponseDto.blocks.shares.companyHouse ?:"",
                            companyImage = successHomeResponseDto.blocks.shares.companyImage ?:""
                        ),
                        examples = successHomeResponseDto.blocks.examples ?:"",
                        examples2 = successHomeResponseDto.blocks.examples2 ?:"",
                        catalog = successHomeResponseDto.blocks.catalog.map { catalog ->
                            Catalog(
                                id = catalog.id ?:"",
                                name = catalog.name ?:"",
                                image = Image(
                                    thumb = catalog.image.thumb,
                                    origin = catalog.image.origin
                                ),
                                street = catalog.street ?:"",
                                house = catalog.house ?:"",
                                schedule = catalog.schedule,
                                lat = catalog.lat ?:"",
                                lng = catalog.lng ?:"",
                                categories = catalog.categories,
                                categoriesCatalog = catalog.categoriesCatalog,
                                rating = catalog.rating,
                                isMaster = catalog.isMaster,
                                award = catalog.award ?:"",
                                vipTariff = catalog.vipTariff,
                                reviewCount = catalog.reviewCount ?:"",
                                backgrounds = catalog.backgrounds,
                                currency = Currency(
                                    id = catalog.currency.id,
                                    title = catalog.currency.title,
                                    abbr = catalog.currency.abbr
                                ),
                                masterId = catalog.masterId ?:""
                            )
                        }
                    ),
                    order = successHomeResponseDto.order
                )
            )
        }
    }
}
