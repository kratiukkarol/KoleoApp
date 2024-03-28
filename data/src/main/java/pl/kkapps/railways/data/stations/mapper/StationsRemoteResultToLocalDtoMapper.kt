package pl.kkapps.railways.data.stations.mapper

import javax.inject.Inject
import pl.kkapps.railways.data.stations.local.StationLocalDto
import pl.kkapps.railways.data.stations.remote.StationsRemoteResult
import pl.kkapps.railways.domain.base.BaseMapper
import pl.kkapps.railways.domain.base.MappingRequirements.requireNotNull

internal class StationsRemoteResultToLocalDtoMapper @Inject constructor() :
    BaseMapper<StationsRemoteResult, StationLocalDto>() {

    override fun map(data: StationsRemoteResult): StationLocalDto =
        data.requireNotNull().let {
            StationLocalDto(
                id = it.id.toString(),
                name = it.name,
                latitude = it.latitude.toString(),
                longitude = it.longitude.toString(),
                hits = it.hits
            )
        }
}