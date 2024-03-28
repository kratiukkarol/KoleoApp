package pl.kkapps.railways.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.kkapps.railways.ui.R
import pl.kkapps.railways.ui.theme.AppTheme

@Composable
fun StationCard(
    name: String,
    modifier: Modifier = Modifier,
    hasIcon: Boolean = true,
    onClick: (() -> Unit) = {},
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clip(CircleShape)
            .border(
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                width = 1.dp,
                shape = CircleShape
            )
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .clickable(role = Role.Button) { onClick() }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer12()
        Text(
            text = name,
            modifier = modifier.weight(1f),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer8()
        if (hasIcon) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = stringResource(R.string.search_icon),
                modifier = modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer4()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StationCardPreview(isDarkTheme: Boolean = false) {
    AppTheme(useDarkTheme = isDarkTheme) {
        StationCard(name = "Pruszków")
    }
}

@Preview
@Composable
private fun StationCardDarkPreview() {
    AppTheme {
        StationCardPreview(true)
    }
}