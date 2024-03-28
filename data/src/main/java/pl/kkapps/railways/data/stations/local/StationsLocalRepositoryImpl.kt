package pl.kkapps.railways.data.stations.local

import javax.inject.Inject
import pl.kkapps.railways.data.base.realm.access.RealmOperations

internal class StationsLocalRepositoryImpl @Inject constructor(
    private val realmOperations: RealmOperations,
) : StationsLocalRepository {

    override suspend fun saveStations(stations: List<StationLocalDto>) =
        realmOperations.executeTransaction {
            stations.forEach { insertOrUpdate(it) }
        }

    override suspend fun getStations(): List<StationLocalDto> = realmOperations.readAll {
        where(StationLocalDto::class.java)
    }
}