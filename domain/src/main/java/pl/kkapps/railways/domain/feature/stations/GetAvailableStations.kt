package pl.kkapps.railways.domain.feature.stations

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import pl.kkapps.railways.domain.data.StationsDao
import pl.kkapps.railways.domain.model.Station

interface GetAvailableStations {

    operator fun invoke(): Flow<List<Station>>
}

class GetAvailableStationsImpl @Inject constructor(
    private val stationsDao: StationsDao,
) : GetAvailableStations {

    override fun invoke(): Flow<List<Station>> = stationsDao.getStations()
}