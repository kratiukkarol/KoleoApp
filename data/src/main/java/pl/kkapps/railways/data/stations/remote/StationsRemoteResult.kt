package pl.kkapps.railways.data.stations.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationsRemoteResult(
    val id: Int,
    val name: String?,
    @SerialName("name_slug")
    val nameSlug: String?,
    val latitude: Double?,
    val longitude: Double?,
    val hits: Int?,
    val ibnr: Int?,
    val city: String?,
    val region: String?,
    val country: String?,
    @SerialName("localised_name")
    val localisedName: String?,
    @SerialName("is_group")
    val isGroup: Boolean?,
    @SerialName("has_announcements")
    val hasAnnouncements: Boolean?,
    @SerialName("is_nearby_station_enabled")
    val isNearbyStationEnabled: Boolean?,
)