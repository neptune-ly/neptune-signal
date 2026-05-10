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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalBrand
import ly.neptune.signal.theme.SignalColorMode
import ly.neptune.signal.theme.SignalThemeOverrides
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalThemeConfig
import ly.neptune.signal.theme.SignalThemeSettings
import ly.neptune.signal.theme.SignalTheme

data class SignalThemePreset(
    val key: String,
    val label: String,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val description: String? = null,
)

fun SignalThemePreset.toBrand(
    ink: Color = Color(0xFF071C2E),
    surface: Color = Color(0xFFFAFCFC),
): SignalBrand = SignalBrand(
    key = key,
    label = label,
    primary = primary,
    secondary = secondary,
    accent = accent,
    ink = ink,
    surface = surface,
)

fun SignalThemePreset.toThemeConfig(
    mode: SignalColorMode,
    ink: Color = Color(0xFF071C2E),
    surface: Color = Color(0xFFFAFCFC),
): SignalThemeConfig = SignalThemeConfig(
    brand = toBrand(ink = ink, surface = surface),
    mode = mode,
)

fun SignalThemePreset.toThemeSettings(
    appearance: SignalAppearanceMode = SignalAppearanceMode.System,
    overrides: SignalThemeOverrides = SignalThemeOverrides(),
    ink: Color = Color(0xFF071C2E),
    surface: Color = Color(0xFFFAFCFC),
): SignalThemeSettings = SignalThemeSettings(
    brand = toBrand(ink = ink, surface = surface),
    appearance = appearance,
    overrides = overrides,
)

object SignalThemePresetDefaults {
    val Neptune = SignalThemePreset(
        key = "neptune",
        label = "Neptune Core",
        primary = Color(0xFF07315F),
        secondary = Color(0xFF00A8AE),
        accent = Color(0xFFEB4E4D),
        description = "Navy, teal, coral",
    )

    val BankSamples = listOf(
        Neptune,
        SignalThemePreset("atib", "ATIB Red", Color(0xFF981B1E), Color(0xFFFFFFFF), Color(0xFFD7282F), "Red and white"),
        SignalThemePreset("yaqeen", "Yaqeen Gold", Color(0xFF4C3324), Color(0xFFC7A15D), Color(0xFF8E6A2F), "Brown and gold"),
        SignalThemePreset("ncb", "NCB Green", Color(0xFF0B5A3A), Color(0xFF35B56F), Color(0xFFD6B15B), "Green public bank"),
        SignalThemePreset("nub", "Nuran NUB", Color(0xFF1E2430), Color(0xFFD5A332), Color(0xFFF0C75E), "Slate and gold"),
        SignalThemePreset("aman", "Aman Blue", Color(0xFF004B7A), Color(0xFF00A17A), Color(0xFF42B7E8), "Blue and green"),
        SignalThemePreset("public", "Public Green", Color(0xFF103F35), Color(0xFF148F67), Color(0xFFB7A66A), "Institutional green"),
    )
}

@Composable
fun SignalAppearanceSelector(
    selectedMode: SignalAppearanceMode,
    onModeSelected: (SignalAppearanceMode) -> Unit,
    modifier: Modifier = Modifier,
    labels: Map<SignalAppearanceMode, String> = mapOf(
        SignalAppearanceMode.System to "System",
        SignalAppearanceMode.Light to "Light",
        SignalAppearanceMode.Dark to "Dark",
        SignalAppearanceMode.Black to "Black",
    ),
) {
    SignalSegmentedControl(
        modifier = modifier,
        segments = SignalAppearanceMode.entries.map { mode ->
            SignalSegment(mode.name, labels[mode] ?: mode.name)
        },
        selectedKey = selectedMode.name,
        onSelected = { key ->
            SignalAppearanceMode.entries.firstOrNull { it.name == key }?.let(onModeSelected)
        },
    )
}

@Composable
fun SignalThemePresetList(
    presets: List<SignalThemePreset>,
    selectedKey: String,
    onPresetSelected: (SignalThemePreset) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        presets.forEach { preset ->
            SignalThemePresetRow(
                preset = preset,
                selected = preset.key == selectedKey,
                onClick = { onPresetSelected(preset) },
            )
        }
    }
}

@Composable
fun SignalThemePresetRow(
    preset: SignalThemePreset,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalTheme.shapes.md)
    val selectedBorder = if (colors.dark) colors.bankAccent else colors.bankPrimary
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = SignalTheme.dimensions.rowMinHeight)
            .background(if (selected) colors.primaryContainer else colors.surfaceContainerLowest, shape)
            .border(
                BorderStroke(1.dp, if (selected) selectedBorder else colors.outlineVariant),
                shape,
            )
            .clickable(onClick = onClick)
            .padding(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        SignalPresetSwatches(
            primary = preset.primary,
            secondary = preset.secondary,
            accent = preset.accent,
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = preset.label,
                color = colors.onSurface,
                style = typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (preset.description != null) {
                Text(
                    text = preset.description,
                    color = colors.onSurfaceVariant,
                    style = typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (selected) {
            Text(text = "Selected", color = selectedBorder, style = typography.statusPill)
        }
    }
}

@Composable
fun SignalPresetSwatches(
    primary: Color,
    secondary: Color,
    accent: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((-6).dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SignalSwatch(primary)
        SignalSwatch(secondary)
        SignalSwatch(accent)
    }
}

@Composable
fun SignalSwatch(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .background(color, RoundedCornerShape(SignalTheme.shapes.full))
            .border(BorderStroke(1.dp, SignalTheme.colors.outlineVariant), RoundedCornerShape(SignalTheme.shapes.full)),
    )
}
