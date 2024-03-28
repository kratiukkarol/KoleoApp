package pl.kkapps.railways.domain.feature.keywords

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import pl.kkapps.railways.domain.data.StationKeywordsDao
import pl.kkapps.railways.domain.model.StationKeywords

interface GetStationKeywords {

    operator fun invoke(): Flow<List<StationKeywords>>
}

class GetStationKeywordsImpl @Inject constructor(
    private val stationKeywordsDao: StationKeywordsDao,
) : GetStationKeywords {

    override fun invoke(): Flow<List<StationKeywords>> = stationKeywordsDao.getStationKeywords()
}