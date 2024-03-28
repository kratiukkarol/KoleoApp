package pl.kkapps.railways.domain.data

import kotlinx.coroutines.flow.Flow
import pl.kkapps.railways.domain.model.Station

interface StationsDao {

    fun getStations(): Flow<List<Station>>
}