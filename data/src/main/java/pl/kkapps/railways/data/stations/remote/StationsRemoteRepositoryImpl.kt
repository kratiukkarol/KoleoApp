package pl.kkapps.railways.data.stations.remote

import javax.inject.Inject
import pl.kkapps.railways.data.base.remote.DefaultEndpointsCreator
import retrofit2.http.GET
import retrofit2.http.Headers

internal class StationsRemoteRepositoryImpl @Inject constructor(
    endpointsCreator: DefaultEndpointsCreator,
) : StationsRemoteRepository {

    private val stationsEndpoints by endpointsCreator.lazyCreate(StationsEndpoints::class)

    override suspend fun getStations(): List<StationsRemoteResult> =
        stationsEndpoints.fetchStations()

    internal interface StationsEndpoints {

        @Headers(
            "X-KOLEO-Version: 1",
            "Cache-Control: max-age=86400"
        )
        @GET("stations")
        suspend fun fetchStations(): List<StationsRemoteResult>
    }
}