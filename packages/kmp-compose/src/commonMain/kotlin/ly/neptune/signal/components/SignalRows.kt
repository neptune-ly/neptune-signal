package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalRowTone {
    Neutral,
    Primary,
    Accent,
    Success,
    Warning,
    Danger,
}

@Composable
fun SignalBankingRow(
    title: String,
    metadata: String,
    modifier: Modifier = Modifier,
    amount: String? = null,
    tone: SignalRowTone = SignalRowTone.Neutral,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalRadius.md)
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(enabled = enabled, onClick = onClick)
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .then(clickableModifier)
            .fillMaxWidth()
            .heightIn(min = 72.dp)
            .background(colors.surfaceCard, shape)
            .border(BorderStroke(1.dp, colors.borderDefault), shape)
            .padding(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        SignalIconSurface(tone = tone, content = leading)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = colors.textPrimary,
                style = typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = metadata,
                color = colors.textSecondary,
                style = typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (amount != null) {
            Text(
                text = amount,
                color = toneColor(tone),
                style = typography.rowTitle,
                maxLines = 1,
            )
        }
        trailing?.invoke()
    }
}

@Composable
fun SignalAccountRow(
    name: String,
    accountType: String,
    status: String,
    iban: String,
    balance: String,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
    onClick: () -> Unit,
    trailing: (@Composable () -> Unit)? = null,
) {
    SignalBankingRow(
        title = name,
        metadata = "$accountType · $status · IBAN ending ${iban.filter { it.isLetterOrDigit() }.takeLast(4)}",
        amount = if (masked) "••••••" else balance,
        modifier = modifier,
        tone = SignalRowTone.Primary,
        onClick = onClick,
        trailing = trailing,
    )
}

@Composable
fun SignalConsentScopeRow(
    index: String,
    title: String,
    description: String,
    access: String,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalRadius.md)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceCard, shape)
            .border(BorderStroke(1.dp, colors.borderDefault), shape)
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(colors.bankPrimary, RoundedCornerShape(999.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = index, color = colors.textInverse, style = typography.statusPill)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = colors.textPrimary, style = typography.rowTitle)
            Text(text = description, color = colors.textSecondary, style = typography.rowMeta)
        }
        Surface(
            color = colors.surfaceSoft,
            shape = RoundedCornerShape(999.dp),
        ) {
            Text(
                text = access,
                modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
                color = colors.bankPrimary,
                style = typography.statusPill,
            )
        }
    }
}

@Composable
private fun SignalIconSurface(
    tone: SignalRowTone,
    content: (@Composable () -> Unit)?,
) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(toneColor(tone).copy(alpha = if (tone == SignalRowTone.Neutral) 0.09f else 1f), RoundedCornerShape(SignalRadius.sm)),
        contentAlignment = Alignment.Center,
    ) {
        content?.invoke()
    }
}

@Composable
private fun toneColor(tone: SignalRowTone): Color {
    val colors = SignalTheme.colors
    return when (tone) {
        SignalRowTone.Neutral -> colors.textSecondary
        SignalRowTone.Primary -> colors.bankPrimary
        SignalRowTone.Accent -> colors.bankAccent
        SignalRowTone.Success -> colors.success
        SignalRowTone.Warning -> colors.warning
        SignalRowTone.Danger -> colors.danger
    }
}
