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
    onCopyIban: (() -> Unit)? = null,
    onShareIban: (() -> Unit)? = null,
    onCopyAlias: (() -> Unit)? = null,
    onShareAlias: (() -> Unit)? = null,
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
        )
        SignalValueLine(
            label = "IBAN",
            value = iban,
            format = SignalValueFormat.Iban,
            masked = masked,
            copyable = onCopyIban != null,
            shareable = onShareIban != null,
            onCopy = onCopyIban,
            onShare = onShareIban,
        )
        if (alias != null) {
            SignalValueLine(
                label = "Alias",
                value = alias,
                format = SignalValueFormat.Alias,
                masked = masked,
                copyable = onCopyAlias != null,
                shareable = onShareAlias != null,
                onCopy = onCopyAlias,
                onShare = onShareAlias,
            )
        }
    }
}
