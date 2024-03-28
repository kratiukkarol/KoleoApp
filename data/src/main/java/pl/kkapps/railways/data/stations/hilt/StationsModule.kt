package pl.kkapps.railways.data.stations.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kkapps.railways.data.stations.StationsDaoImpl
import pl.kkapps.railways.data.stations.local.StationsLocalRepository
import pl.kkapps.railways.data.stations.local.StationsLocalRepositoryImpl
import pl.kkapps.railways.data.stations.remote.StationsRemoteRepository
import pl.kkapps.railways.data.stations.remote.StationsRemoteRepositoryImpl
import pl.kkapps.railways.domain.data.StationsDao

@Module
@InstallIn(SingletonComponent::class)
internal interface StationsModule {

    @Binds
    fun bindStationsDao(impl: StationsDaoImpl): StationsDao

    @Binds
    fun bindStationsRemoteRepository(impl: StationsRemoteRepositoryImpl): StationsRemoteRepository

    @Binds
    fun bindStationsLocalRepository(impl: StationsLocalRepositoryImpl): StationsLocalRepository
}