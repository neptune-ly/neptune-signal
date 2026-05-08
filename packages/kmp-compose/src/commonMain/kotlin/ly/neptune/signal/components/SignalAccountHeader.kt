package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalAccountHeader(
    accountName: String,
    balance: String,
    iban: String,
    modifier: Modifier = Modifier,
    alias: String? = null,
    masked: Boolean = false,
    actions: @Composable () -> Unit = {},
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.bankPrimary, RoundedCornerShape(SignalRadius.lg))
            .padding(SignalSpacing.x4),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = accountName, color = colors.textInverse, style = typography.sectionTitle)
            actions()
        }
        Text(
            text = if (masked) "••••••" else balance,
            color = colors.textInverse,
            style = typography.displayBalance,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = if (masked) "IBAN: LY••••••••••••••••" else "IBAN: $iban",
            color = colors.textInverse.copy(alpha = 0.72f),
            style = typography.rowMeta,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (alias != null) {
            Text(
                text = if (masked) "Alias: ••••••" else "Alias: $alias",
                color = colors.textInverse.copy(alpha = 0.72f),
                style = typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

