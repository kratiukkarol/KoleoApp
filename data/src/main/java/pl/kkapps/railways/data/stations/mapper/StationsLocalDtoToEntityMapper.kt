package pl.kkapps.railways.data.stations.mapper

import javax.inject.Inject
import pl.kkapps.railways.data.stations.local.StationLocalDto
import pl.kkapps.railways.domain.base.BaseMapper
import pl.kkapps.railways.domain.model.Station

internal class StationsLocalDtoToEntityMapper @Inject constructor() : BaseMapper<StationLocalDto, Station>() {

    override fun map(data: StationLocalDto): Station =
        Station(
            id = data.id.toInt(),
            name = data.name.orEmpty(),
            latitude = data.latitude,
            longitude = data.longitude,
            hits = data.hits ?: 0
        )
}