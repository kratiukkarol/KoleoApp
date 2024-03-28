package pl.kkapps.railways.ui.home.mapper

import javax.inject.Inject
import pl.kkapps.railways.domain.base.BaseMapper
import pl.kkapps.railways.domain.model.Station
import pl.kkapps.railways.ui.home.AppScreenState

class StationsEntityToStateMapper @Inject constructor() : BaseMapper<Station, AppScreenState.Station>() {

    override fun map(data: Station): AppScreenState.Station =
        AppScreenState.Station(
            id = data.id,
            name = data.name,
            latitude = data.latitude.orEmpty(),
            longitude = data.longitude.orEmpty(),
            hits = data.hits
        )
}