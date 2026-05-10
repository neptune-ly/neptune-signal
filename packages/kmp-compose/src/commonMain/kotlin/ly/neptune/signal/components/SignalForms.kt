package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val dimensions = SignalTheme.dimensions
    val containerColor = when {
        colors.black -> colors.surfaceContainerLow
        colors.dark -> colors.surfaceContainerLowest
        else -> colors.surfaceContainerLowest
    }
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = dimensions.fieldHeight),
            color = containerColor,
            shape = RoundedCornerShape(SignalTheme.shapes.md),
            border = BorderStroke(1.dp, if (errorText != null) colors.error else colors.outlineVariant),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = SignalSpacing.x3, vertical = 11.dp),
            ) {
                Text(
                    text = label,
                    color = if (errorText != null) colors.error else colors.onSurfaceVariant,
                    style = SignalTheme.typography.fieldLabel,
                )
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = singleLine,
                    keyboardOptions = keyboardOptions,
                    visualTransformation = visualTransformation,
                    cursorBrush = SolidColor(colors.bankPrimary),
                    textStyle = SignalTheme.typography.fieldValue.copy(
                        color = colors.onSurface,
                    ),
                    decorationBox = { innerTextField ->
                        androidx.compose.foundation.layout.Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                        ) {
                            androidx.compose.foundation.layout.Box(modifier = Modifier.weight(1f)) {
                                innerTextField()
                            }
                            trailingIcon?.invoke()
                        }
                    },
                )
            }
        }
        val text = errorText ?: supportingText
        if (text != null) {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x1),
                color = if (errorText != null) colors.error else colors.onSurfaceVariant,
                style = SignalTheme.typography.rowMeta,
            )
        }
    }
}

@Composable
fun SignalAmountField(
    value: String,
    onValueChange: (String) -> Unit,
    currency: String,
    modifier: Modifier = Modifier,
    label: String = "Amount",
    errorText: String? = null,
) {
    SignalTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        errorText = errorText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        trailingIcon = {
            Text(
                text = currency,
                color = SignalTheme.colors.bankPrimary,
                style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"),
            )
        },
    )
}

@Composable
fun SignalIbanField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "IBAN",
    errorText: String? = null,
    onCopy: (() -> Unit)? = null,
) {
    SignalTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        errorText = errorText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
        trailingIcon = if (onCopy != null) {
            {
                IconButton(
                    onClick = onCopy,
                    modifier = Modifier
                        .size(SignalTheme.dimensions.iconButton)
                        .semantics { contentDescription = "Copy $label" },
                ) {
                    SignalIcon(
                        name = SignalIconName.Copy,
                        tint = SignalTheme.colors.bankPrimary,
                        size = 18.dp,
                    )
                }
            }
        } else {
            null
        },
    )
}
