package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalAccountHeaderMode {
    Card,
    DetailStage,
}

@Composable
fun SignalAccountHeader(
    accountName: String,
    balance: String,
    iban: String,
    modifier: Modifier = Modifier,
    alias: String? = null,
    masked: Boolean = false,
    mode: SignalAccountHeaderMode = SignalAccountHeaderMode.Card,
    actions: @Composable () -> Unit = {},
    onCopyIban: (() -> Unit)? = null,
    onShareIban: (() -> Unit)? = null,
    onCopyAlias: (() -> Unit)? = null,
    onShareAlias: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shapes = SignalTheme.shapes
    val dimensions = SignalTheme.dimensions
    val shape = when (mode) {
        SignalAccountHeaderMode.Card -> RoundedCornerShape(shapes.lg)
        SignalAccountHeaderMode.DetailStage -> RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = shapes.xl,
            bottomEnd = shapes.xl,
        )
    }
    val valueTone = when (mode) {
        SignalAccountHeaderMode.Card -> SignalValueTone.Surface
        SignalAccountHeaderMode.DetailStage -> SignalValueTone.OnPrimary
    }
    val headerPadding = when (mode) {
        SignalAccountHeaderMode.Card -> SignalSpacing.x4
        SignalAccountHeaderMode.DetailStage -> SignalSpacing.x5
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = dimensions.accountCardMinHeight)
            .background(colors.bankPrimary, shape)
            .padding(headerPadding),
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
            tone = valueTone,
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
                tone = valueTone,
            )
        }
    }
}
