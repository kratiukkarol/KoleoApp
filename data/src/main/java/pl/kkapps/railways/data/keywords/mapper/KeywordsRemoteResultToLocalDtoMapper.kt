package pl.kkapps.railways.data.keywords.mapper

import javax.inject.Inject
import pl.kkapps.railways.data.keywords.local.KeywordLocalDto
import pl.kkapps.railways.data.keywords.remote.KeywordsRemoteResult
import pl.kkapps.railways.domain.base.BaseMapper
import pl.kkapps.railways.domain.base.MappingRequirements.requireNotNull

internal class KeywordsRemoteResultToLocalDtoMapper @Inject constructor() :
    BaseMapper<KeywordsRemoteResult, KeywordLocalDto>() {

    override fun map(data: KeywordsRemoteResult): KeywordLocalDto =
        data.requireNotNull().let {
            KeywordLocalDto(
                id = it.id.toString(),
                keyword = it.keyword,
                stationId = it.stationId
            )
        }
}