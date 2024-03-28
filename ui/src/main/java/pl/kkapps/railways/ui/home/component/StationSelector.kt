package pl.kkapps.railways.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.kkapps.railways.ui.R
import pl.kkapps.railways.ui.theme.AppTheme

@Composable
fun StationSelector(
    stationName: String?,
    onClick: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clickable(role = Role.Button) { onClick() },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.station_row_title),
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer8()
            StationCard(
                name = stationName.orEmpty(),
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StationSelectorPreview(isDarkTheme: Boolean = false) {
    AppTheme(useDarkTheme = isDarkTheme) {
        StationSelector(stationName = "Pruszków")
    }
}

@Preview
@Composable
private fun StationSelectorDarkPreview() {
    AppTheme {
        StationSelectorPreview(true)
    }
}