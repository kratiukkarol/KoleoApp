package pl.kkapps.railways.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.kkapps.railways.ui.R
import pl.kkapps.railways.ui.home.functions.hintVisualTransformation
import pl.kkapps.railways.ui.theme.AppTheme

private val iconSize = 24.dp

@Composable
fun Search(
    modifier: Modifier = Modifier,
    hint: String,
    text: String = "",
    onCloseIconClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    val showCloseButton by remember(text) {
        derivedStateOf { text.isNotEmpty() }
    }
    val borderColor = MaterialTheme.colorScheme.onPrimaryContainer
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val textColor = MaterialTheme.colorScheme.onSurface

    Box(
        modifier
            .border(1.dp, borderColor, CircleShape)
            .background(backgroundColor, CircleShape)
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .wrapContentSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = stringResource(R.string.search_icon),
                modifier = modifier.size(iconSize),
                tint = borderColor
            )
            Spacer12()
            BasicTextField(
                value = text,
                onValueChange = { onValueChange(it) },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Left,
                    color = textColor
                ),
                singleLine = true,
                visualTransformation = hintVisualTransformation(hint, textColor),
                cursorBrush = SolidColor(textColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            if (showCloseButton) {
                Spacer12()
                Icon(
                    painter = painterResource(R.drawable.ic_close_thin),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(iconSize)
                        .clickable(
                            role = Role.Button,
                            onClick = onCloseIconClick
                        ),
                    tint = borderColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview(
    isDarkTheme: Boolean = false,
    hint: String = "Szukaj...",
    text: String = "Pruszków",
) {
    AppTheme(useDarkTheme = isDarkTheme) {
        Search(
            hint = hint,
            text = text
        )
    }
}

@Preview
@Composable
private fun SearchDarkPreview() {
    AppTheme {
        SearchPreview(true)
    }
}