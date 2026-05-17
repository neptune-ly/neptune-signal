package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalTheme

enum class SignalDetailBackTone {
    OnSurface,
    OnBrand,
}

@Composable
fun SignalDetailBackAction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tone: SignalDetailBackTone = SignalDetailBackTone.OnSurface,
    contentDescription: String = "Back",
) {
    val colors = SignalTheme.colors
    val shape = RoundedCornerShape(16.dp)
    val container = when (tone) {
        SignalDetailBackTone.OnSurface -> colors.surfaceContainerHigh.copy(alpha = if (colors.dark) 0.72f else 0.86f)
        SignalDetailBackTone.OnBrand -> Color.White.copy(alpha = 0.14f)
    }
    val border = when (tone) {
        SignalDetailBackTone.OnSurface -> colors.outlineVariant.copy(alpha = 0.18f)
        SignalDetailBackTone.OnBrand -> Color.White.copy(alpha = 0.18f)
    }
    val content = when (tone) {
        SignalDetailBackTone.OnSurface -> colors.onSurface
        SignalDetailBackTone.OnBrand -> Color.White
    }
    Box(
        modifier = modifier
            .size(SignalComponentMetrics.touchTarget)
            .clip(shape)
            .background(container)
            .border(1.dp, border, shape)
            .semantics {
                role = Role.Button
                this.contentDescription = contentDescription
            }
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        SignalIcon(SignalIconName.ArrowStart, tint = content, size = 22.dp)
    }
}
