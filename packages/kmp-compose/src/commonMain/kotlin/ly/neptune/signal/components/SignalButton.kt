package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSize
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalButtonVariant {
    Primary,
    Secondary,
    Destructive,
}

@Composable
fun SignalButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: SignalButtonVariant = SignalButtonVariant.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    leading: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalRadius.lg)
    val minModifier = modifier.defaultMinSize(minHeight = 52.dp)

    val content: @Composable () -> Unit = {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(end = SignalSpacing.x2),
                    color = if (variant == SignalButtonVariant.Primary) colors.textInverse else colors.bankPrimary,
                    strokeWidth = 2.dp,
                )
            } else if (leading != null) {
                leading()
            }
            Text(text = label, style = typography.button)
        }
    }

    when (variant) {
        SignalButtonVariant.Primary -> Button(
            onClick = onClick,
            modifier = minModifier,
            enabled = enabled && !loading,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.bankPrimary,
                contentColor = colors.textInverse,
                disabledContainerColor = colors.surfaceSoft,
                disabledContentColor = colors.textSecondary,
            ),
            content = { content() },
        )

        SignalButtonVariant.Secondary -> OutlinedButton(
            onClick = onClick,
            modifier = minModifier,
            enabled = enabled && !loading,
            shape = shape,
            border = BorderStroke(1.dp, colors.borderDefault),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colors.bankPrimary,
                disabledContentColor = colors.textSecondary,
            ),
            content = { content() },
        )

        SignalButtonVariant.Destructive -> Button(
            onClick = onClick,
            modifier = minModifier,
            enabled = enabled && !loading,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.danger,
                contentColor = Color.White,
                disabledContainerColor = colors.surfaceSoft,
                disabledContentColor = colors.textSecondary,
            ),
            content = { content() },
        )
    }
}

@Composable
fun SignalIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.defaultMinSize(
            minWidth = SignalSize.iconButton,
            minHeight = SignalSize.iconButton,
        ),
        content = content,
    )
}

