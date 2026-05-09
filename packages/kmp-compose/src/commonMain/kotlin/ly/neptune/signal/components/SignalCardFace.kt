package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalCardFace(
    scheme: String,
    maskedNumber: String,
    modifier: Modifier = Modifier,
    holderName: String? = null,
    background: Color = SignalTheme.colors.bankPrimary,
    contentColor: Color = SignalTheme.colors.textInverse,
) {
    val typography = SignalTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = SignalTheme.dimensions.cardFaceMinHeight)
            .background(background, RoundedCornerShape(SignalTheme.shapes.xl))
            .padding(SignalSpacing.x5),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x6),
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(text = scheme, color = contentColor, style = typography.sectionTitle)
        }
        Text(text = maskedNumber, color = contentColor, style = typography.pageTitle)
        if (holderName != null) {
            Text(text = holderName, color = contentColor.copy(alpha = 0.72f), style = typography.rowMeta)
        }
    }
}
