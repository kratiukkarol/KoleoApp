package pl.kkapps.railways.data.keywords.remote

internal interface KeywordsRemoteRepository {

    suspend fun getStationKeywords(): List<KeywordsRemoteResult>
}