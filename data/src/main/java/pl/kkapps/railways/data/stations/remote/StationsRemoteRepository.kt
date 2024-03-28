package pl.kkapps.railways.data.stations.remote

internal interface StationsRemoteRepository {

    suspend fun getStations(): List<StationsRemoteResult>
}