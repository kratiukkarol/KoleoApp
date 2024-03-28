package pl.kkapps.railways.ui.home.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Uninitialized
import pl.kkapps.railways.ui.R
import pl.kkapps.railways.ui.home.AppScreenState.Station
import pl.kkapps.railways.ui.theme.AppTheme

@Composable
fun StationSelectorDialog(
    query: String?,
    asyncStations: Async<List<Station>>,
    onKeywordsQuery: (String) -> Unit,
    onClearQuery: () -> Unit,
    onChoseStation: (Station) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false,
        )
    ) {
        DialogContent(
            query = query,
            asyncStations = asyncStations,
            onChoseStation = onChoseStation,
            onClearQuery = onClearQuery,
            onKeywordsQuery = onKeywordsQuery,
            onDismiss = onDismiss,
        )
    }
}

@Composable
private fun DialogContent(
    query: String? = null,
    asyncStations: Async<List<Station>> = Uninitialized,
    onChoseStation: (Station) -> Unit = {},
    onKeywordsQuery: (String) -> Unit = {},
    onClearQuery: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val stations = remember(asyncStations, onClearQuery) {
        asyncStations().takeIf { !query.isNullOrEmpty() } ?: emptyList()
    }
    Column(
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 16.dp)
    ) {
        TitleWithCloseButton(
            onCloseClick = onDismiss,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer12()
        Search(
            text = query.orEmpty(),
            hint = stringResource(R.string.search_hint),
            onCloseIconClick = onClearQuery,
            onValueChange = onKeywordsQuery,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer12()
        StationsList(
            scrollState = scrollState,
            stations = stations,
            onChoseStation = onChoseStation,
        )
    }
}

@Composable
fun TitleWithCloseButton(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = stringResource(R.string.choose_station),
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_close_thin),
            contentDescription = stringResource(R.string.close_icon),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    role = Role.Button,
                    onClick = onCloseClick
                )
                .padding(4.dp)
                .size(24.dp)
        )
    }
}

@Composable
private fun ColumnScope.StationsList(
    scrollState: ScrollState,
    stations: List<Station>,
    onChoseStation: (Station) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shadowElevation by animateDpAsState(if (stations.isEmpty()) 0.dp else 2.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .weight(1f)
            .clipToBounds()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            stations.forEach { station ->
                StationCard(
                    name = station.name,
                    hasIcon = false,
                    onClick = { onChoseStation(station) }
                )
            }
        }
        BottomShadow(shadowElevation)
    }
}

@Composable
private fun BoxScope.BottomShadow(elevation: Dp) {
    Box(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .shadow(elevation)
            .fillMaxWidth()
            .height(elevation * 2)
    )
}

@Preview(showBackground = true)
@Composable
private fun SelectorDialogPreview(isDarkTheme: Boolean = false) {
    AppTheme(useDarkTheme = isDarkTheme) {
        DialogContent()
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectorDialogDarkPreview() {
    AppTheme {
        SelectorDialogPreview(true)
    }
}