package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = title, color = SignalTheme.colors.bankPrimary, style = SignalTheme.typography.sectionTitle)
        action?.invoke()
    }
}

@Composable
fun SignalEmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceCard, RoundedCornerShape(SignalRadius.xl))
            .border(BorderStroke(1.dp, colors.borderDefault), RoundedCornerShape(SignalRadius.xl))
            .padding(SignalSpacing.x6),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        Text(text = title, color = colors.bankPrimary, style = typography.pageTitle, textAlign = TextAlign.Center)
        Text(text = message, color = colors.textSecondary, style = typography.rowMeta, textAlign = TextAlign.Center)
        action?.invoke()
    }
}

@Composable
fun SignalNotificationRow(
    title: String,
    message: String,
    time: String,
    modifier: Modifier = Modifier,
    unread: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    SignalBankingRow(
        title = title,
        metadata = "$message · $time",
        modifier = modifier,
        tone = if (unread) SignalRowTone.Accent else SignalRowTone.Neutral,
        onClick = onClick,
    )
}

