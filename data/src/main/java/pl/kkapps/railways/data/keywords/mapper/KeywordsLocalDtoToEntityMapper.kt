package pl.kkapps.railways.data.keywords.mapper

import javax.inject.Inject
import pl.kkapps.railways.data.keywords.local.KeywordLocalDto
import pl.kkapps.railways.domain.base.BaseMapper
import pl.kkapps.railways.domain.base.MappingRequirements.requireNotNull
import pl.kkapps.railways.domain.model.StationKeywords

internal class KeywordsLocalDtoToEntityMapper @Inject constructor() :
    BaseMapper<KeywordLocalDto, StationKeywords>() {

    override fun map(data: KeywordLocalDto): StationKeywords =
        StationKeywords(
            id = data.id.toInt(),
            keyword = data.keyword.requireNotNull(),
            stationId = data.stationId.requireNotNull()
        )
}