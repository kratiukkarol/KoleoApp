package pl.kkapps.railways.data.stations.local

internal interface StationsLocalRepository {

    suspend fun saveStations(stations: List<StationLocalDto>)

    suspend fun getStations(): List<StationLocalDto>
}