package pl.kkapps.railways.ui.home.functions

import pl.kkapps.railways.ui.home.AppScreenState.Station
import pl.kkapps.railways.ui.home.AppScreenState.StationKeywords

fun List<StationKeywords>.sortByHitsAndFilter(
    query: String,
    stations: List<Station>,
) = if (query.isEmpty()) {
    this
} else {
    filter { it.keyword.startsWith(query, true) }
}.mapNotNull { keyword ->
    stations.find {
        it.id == keyword.stationId
    }
}.sortedByDescending { it.hits }