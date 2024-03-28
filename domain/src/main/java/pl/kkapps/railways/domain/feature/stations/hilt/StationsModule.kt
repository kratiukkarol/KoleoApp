package pl.kkapps.railways.domain.feature.stations.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kkapps.railways.domain.feature.stations.GetAvailableStations
import pl.kkapps.railways.domain.feature.stations.GetAvailableStationsImpl

@Module
@InstallIn(SingletonComponent::class)
interface StationsModule {

    @Binds
    fun bindGetAvailableStations(impl: GetAvailableStationsImpl): GetAvailableStations
}