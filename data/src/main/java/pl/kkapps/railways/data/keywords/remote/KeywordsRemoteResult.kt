package pl.kkapps.railways.data.keywords.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordsRemoteResult(
    val id: Int,
    val keyword: String,
    @SerialName("station_id")
    val stationId: Int,
)