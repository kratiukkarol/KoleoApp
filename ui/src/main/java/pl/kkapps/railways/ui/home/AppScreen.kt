package pl.kkapps.railways.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import pl.kkapps.railways.ui.R
import pl.kkapps.railways.ui.home.AppScreenState.StationType
import pl.kkapps.railways.ui.home.component.StationSelector
import pl.kkapps.railways.ui.home.component.StationSelectorDialog
import pl.kkapps.railways.ui.home.functions.haversine

@Composable
fun AppScreen(viewModel: AppScreenViewModel = mavericksViewModel()) {
    val state by viewModel.collectAsState()
    val selectionMode = state.selectionMode

    if (selectionMode is AppScreenState.StationSelection) {
        StationSelectorDialog(
            asyncStations = state.queriedStations,
            query = state.keywordsQuery,
            onKeywordsQuery = viewModel::onKeywordsQuery,
            onClearQuery = viewModel::onClearSearch,
            onChoseStation = viewModel::onChoseStation,
            onDismiss = viewModel::onCancelSelection,
        )
    }
    ScreenContent(
        state = state,
        onSearchStationClick = viewModel::onStationSearchClick
    )
}

@Composable
fun ScreenContent(
    state: AppScreenState,
    onSearchStationClick: (StationType) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Headline()
        StationSelector(
            stationName = state.stationFrom?.name,
            onClick = { onSearchStationClick(StationType.FROM) }
        )
        StationSelector(
            stationName = state.stationTo?.name,
            onClick = { onSearchStationClick(StationType.TO) }
        )
        DistanceInfoRow(
            distance = if (state.stationFrom != null && state.stationTo != null)
                state.stationFrom.haversine(state.stationTo) else 0
        )
    }
}

@Composable
fun Headline() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.headline),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun DistanceInfoRow(distance: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.distance_info, distance),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}