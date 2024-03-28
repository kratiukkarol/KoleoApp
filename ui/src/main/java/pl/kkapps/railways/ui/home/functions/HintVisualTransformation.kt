package pl.kkapps.railways.ui.home.functions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

fun hintVisualTransformation(hint: String?, color: Color) =
    hint?.let {
        VisualTransformation { text ->
            if (text.isEmpty()) {
                TransformedText(
                    text = AnnotatedString(
                        text = hint,
                        spanStyle = SpanStyle(color = color)
                    ),
                    offsetMapping = OffsetMapping.initialOffset()
                )
            } else {
                TransformedText(
                    text = text,
                    offsetMapping = OffsetMapping.Identity
                )
            }
        }
    } ?: VisualTransformation.None

private fun OffsetMapping.Companion.initialOffset() = object : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int = 0
    override fun transformedToOriginal(offset: Int): Int = 0
}