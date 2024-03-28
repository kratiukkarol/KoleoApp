package pl.kkapps.railways.ui.home

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import pl.kkapps.railways.domain.feature.keywords.GetStationKeywords
import pl.kkapps.railways.domain.feature.stations.GetAvailableStations
import pl.kkapps.railways.ui.home.AppScreenState.NoSelection
import pl.kkapps.railways.ui.home.AppScreenState.Station
import pl.kkapps.railways.ui.home.AppScreenState.StationSelection
import pl.kkapps.railways.ui.home.AppScreenState.StationType
import pl.kkapps.railways.ui.home.functions.sortByHitsAndFilter
import pl.kkapps.railways.ui.home.mapper.StationKeywordsEntityToStateMapper
import pl.kkapps.railways.ui.home.mapper.StationsEntityToStateMapper

class AppScreenViewModel @AssistedInject constructor(
    @Assisted state: AppScreenState,
    private val getAvailableStations: GetAvailableStations,
    private val stationsEntityToStateMapper: StationsEntityToStateMapper,
    private val getStationKeywords: GetStationKeywords,
    private val stationKeywordsEntityToStateMapper: StationKeywordsEntityToStateMapper,
) : MavericksViewModel<AppScreenState>(state) {

    private val inputDebounceMillis = 250L

    init {
        fetchAvailableStations()
        fetchStationKeywords()
        observeKeywordsSearch()
    }

    private fun fetchAvailableStations() {
        getAvailableStations()
            .map { stationsEntityToStateMapper.map(it) }
            .execute(dispatcher = Dispatchers.IO) { stationsAsync ->
                if (stationsAsync is Success) {
                    copy(stations = stationsAsync())
                } else this
            }
    }

    private fun fetchStationKeywords() {
        getStationKeywords()
            .map { stationKeywordsEntityToStateMapper.map(it) }
            .execute(dispatcher = Dispatchers.IO) { keywordsAsync ->
                if (keywordsAsync is Success) {
                    copy(stationKeywords = keywordsAsync())
                } else this
            }
    }

    @OptIn(FlowPreview::class)
    private fun observeKeywordsSearch() {
        stateFlow.filter { it.selectionMode is StationSelection }
            .map { it.keywordsQuery }
            .filterNotNull()
            .debounce { if (it.isNotEmpty()) inputDebounceMillis else 0 }
            .combine(stateFlow.map {
                it.stationKeywords to it.stations
            }) { query, (keywords, stations) ->
                keywords.sortByHitsAndFilter(query, stations)
            }
            .execute(
                dispatcher = Dispatchers.IO,
                retainValue = AppScreenState::queriedStations
            ) {
                copy(queriedStations = it)
            }
    }

    fun onStationSearchClick(type: StationType) {
        setState {
            copy(selectionMode = StationSelection(type))
        }
    }

    fun onKeywordsQuery(query: String) {
        setState { copy(keywordsQuery = query) }
    }

    fun onChoseStation(station: Station) {
        setState {
            when (selectionMode) {
                is StationSelection -> {
                    onCancelSelection()
                    when (selectionMode.type) {
                        StationType.FROM -> copy(stationFrom = station)
                        StationType.TO -> copy(stationTo = station)
                    }
                }

                NoSelection -> this
            }
        }
    }

    fun onClearSearch() {
        setState {
            copy(
                keywordsQuery = null,
                queriedStations = Uninitialized,
            )
        }
    }

    fun onCancelSelection() {
        setState {
            copy(
                selectionMode = NoSelection,
                queriedStations = Uninitialized,
                keywordsQuery = null
            )
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<AppScreenViewModel, AppScreenState>

    companion object : MavericksViewModelFactory<AppScreenViewModel, AppScreenState>
    by hiltMavericksViewModelFactory()
}