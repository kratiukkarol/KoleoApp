package pl.kkapps.railways.ui.home.mapper

import javax.inject.Inject
import pl.kkapps.railways.domain.base.BaseMapper
import pl.kkapps.railways.domain.model.StationKeywords
import pl.kkapps.railways.ui.home.AppScreenState

class StationKeywordsEntityToStateMapper @Inject constructor() :
    BaseMapper<StationKeywords, AppScreenState.StationKeywords>() {

    override fun map(data: StationKeywords): AppScreenState.StationKeywords =
        AppScreenState.StationKeywords(
            id = data.id,
            keyword = data.keyword,
            stationId = data.stationId
        )
}