package ly.neptune.signal.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSize
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
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = SignalSize.fieldHeight),
            enabled = enabled,
            singleLine = singleLine,
            isError = errorText != null,
            label = { Text(label) },
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            shape = RoundedCornerShape(SignalRadius.md),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colors.bankPrimary,
                unfocusedBorderColor = colors.borderDefault,
                errorBorderColor = colors.danger,
                focusedLabelColor = colors.bankPrimary,
                unfocusedLabelColor = colors.textSecondary,
                focusedTextColor = colors.textPrimary,
                unfocusedTextColor = colors.textPrimary,
                cursorColor = colors.bankPrimary,
            ),
            supportingText = {
                val text = errorText ?: supportingText
                if (text != null) {
                    Text(
                        text = text,
                        color = if (errorText != null) colors.danger else colors.textSecondary,
                        style = SignalTheme.typography.rowMeta,
                    )
                }
            },
        )
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
                        .size(44.dp)
                        .semantics { contentDescription = "Copy $label" },
                ) {
                    SignalCopyGlyph()
                }
            }
        } else {
            null
        },
    )
}
