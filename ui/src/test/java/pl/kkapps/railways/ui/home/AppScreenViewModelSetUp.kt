package pl.kkapps.railways.ui.home

import com.airbnb.mvrx.test.MavericksTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import org.junit.Rule
import org.mockito.kotlin.mock
import pl.kkapps.railways.domain.feature.keywords.GetStationKeywords
import pl.kkapps.railways.domain.feature.stations.GetAvailableStations
import pl.kkapps.railways.ui.home.mapper.StationKeywordsEntityToStateMapper
import pl.kkapps.railways.ui.home.mapper.StationsEntityToStateMapper

@OptIn(ExperimentalCoroutinesApi::class)
abstract class AppScreenViewModelSetUp {

    //TODO: do zamokowania są wszelkiego rodzaju dane jakie ustawiane są w stanie dla czystości
    // najlepiej z podziałem na defaulty i expecty po modyfikacjach. Dla każdego testu mokujemy
    // stan który będziemy sprawdzać i sprawdzamy razem z loadingami i errorami Async

    @get:Rule
    val mvRxTestRule = MavericksTestRule()

    protected val viewModel by lazy { createViewModel() }
    protected val getAvailableStations: GetAvailableStations = mock()
    protected val getStationKeywords: GetStationKeywords = mock()

    protected fun createViewModel(
        keywordsQuery: String? = null
    ) = AppScreenViewModel(
        state = AppScreenState(
            keywordsQuery = keywordsQuery
        ),
        getAvailableStations = getAvailableStations,
        stationsEntityToStateMapper = StationsEntityToStateMapper(),
        getStationKeywords = getStationKeywords,
        stationKeywordsEntityToStateMapper = StationKeywordsEntityToStateMapper()
    )

    protected fun TestScope.init() {
        viewModel::class
        runCurrent()
    }
}