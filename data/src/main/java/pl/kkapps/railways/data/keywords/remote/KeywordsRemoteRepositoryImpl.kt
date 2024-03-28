package pl.kkapps.railways.data.keywords.remote

import javax.inject.Inject
import pl.kkapps.railways.data.base.remote.DefaultEndpointsCreator
import retrofit2.http.GET
import retrofit2.http.Headers

internal class KeywordsRemoteRepositoryImpl @Inject constructor(
    endpointsCreator: DefaultEndpointsCreator,
) : KeywordsRemoteRepository {

    private val stationsEndpoints by endpointsCreator.lazyCreate(KeywordsEndpoints::class)

    override suspend fun getStationKeywords(): List<KeywordsRemoteResult> =
        stationsEndpoints.fetchStationKeywords()

    internal interface KeywordsEndpoints {

        @Headers("X-KOLEO-Version: 1")
        @GET("station_keywords")
        suspend fun fetchStationKeywords(): List<KeywordsRemoteResult>
    }
}