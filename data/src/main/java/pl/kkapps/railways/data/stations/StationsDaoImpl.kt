package pl.kkapps.railways.data.stations

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import pl.kkapps.railways.data.base.utils.networkBound
import pl.kkapps.railways.data.stations.local.StationsLocalRepository
import pl.kkapps.railways.data.stations.mapper.StationsLocalDtoToEntityMapper
import pl.kkapps.railways.data.stations.mapper.StationsRemoteResultToLocalDtoMapper
import pl.kkapps.railways.data.stations.remote.StationsRemoteRepository
import pl.kkapps.railways.domain.data.StationsDao
import pl.kkapps.railways.domain.model.Station

internal class StationsDaoImpl @Inject constructor(
    private val stationsRemoteRepository: StationsRemoteRepository,
    private val stationsLocalRepository: StationsLocalRepository,
    private val stationsLocalDtoToEntityMapper: StationsLocalDtoToEntityMapper,
    private val stationsRemoteResultToLocalDtoMapper: StationsRemoteResultToLocalDtoMapper,
) : StationsDao {

    override fun getStations(): Flow<List<Station>> =
        networkBound(
            localQuery = {
                val stationsDto = stationsLocalRepository.getStations()
                stationsLocalDtoToEntityMapper.map(stationsDto)
            },
            remoteFetch = {
                stationsRemoteRepository.getStations()
            },
            saveRemoteResult = { remoteResult ->
                val stations = remoteResult.map { stationsRemoteResultToLocalDtoMapper.map(it) }
                stationsLocalRepository.saveStations(stations)
            },
            onError = { }
        )
}