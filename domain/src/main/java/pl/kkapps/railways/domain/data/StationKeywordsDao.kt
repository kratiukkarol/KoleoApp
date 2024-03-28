package pl.kkapps.railways.domain.data

import kotlinx.coroutines.flow.Flow
import pl.kkapps.railways.domain.model.StationKeywords

interface StationKeywordsDao {

    fun getStationKeywords(): Flow<List<StationKeywords>>
}