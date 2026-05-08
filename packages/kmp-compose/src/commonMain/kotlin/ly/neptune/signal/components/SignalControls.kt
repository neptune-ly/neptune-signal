package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalSegment(
    val key: String,
    val label: String,
)

@Composable
fun SignalSegmentedControl(
    segments: List<SignalSegment>,
    selectedKey: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(colors.surfaceContainer)
            .padding(SignalSpacing.x1),
    ) {
        segments.forEach { segment ->
            val selected = segment.key == selectedKey
            Text(
                text = segment.label,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (selected) colors.surfaceContainerLowest else colors.surfaceContainer)
                    .clickable { onSelected(segment.key) }
                    .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
                color = if (selected) colors.primary else colors.onSurfaceVariant,
                style = SignalTheme.typography.statusPill,
            )
        }
    }
}
