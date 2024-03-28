package pl.kkapps.railways.ui.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class AppScreenState(
    val stations: List<Station> = emptyList(),
    val stationKeywords: List<StationKeywords> = emptyList(),
    val selectionMode: SelectionMode = NoSelection,
    val stationFrom: Station? = null,
    val stationTo: Station? = null,
    val keywordsQuery: String? = null,
    val queriedStations: Async<List<Station>> = Uninitialized,
) : MavericksState {

    data class Station(
        val id: Int,
        val name: String,
        val latitude: String,
        val longitude: String,
        val hits: Int,
    )

    data class StationKeywords(
        val id: Int,
        val keyword: String,
        val stationId: Int,
    )

    sealed interface SelectionMode

    data object NoSelection : SelectionMode

    data class StationSelection(val type: StationType) : SelectionMode

    enum class StationType {
        FROM, TO
    }
}