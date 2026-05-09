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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalVoucherCategory(
    val key: String,
    val label: String,
    val icon: @Composable () -> Unit = {},
)

data class SignalVoucherProduct(
    val key: String,
    val provider: String,
    val description: String,
    val categoryKey: String,
    val values: List<String>,
    val icon: @Composable () -> Unit = {},
)

@Composable
fun SignalVoucherStore(
    categories: List<SignalVoucherCategory>,
    products: List<SignalVoucherProduct>,
    selectedCategoryKey: String,
    onCategorySelected: (String) -> Unit,
    onProductSelected: (SignalVoucherProduct) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visibleProducts = products.filter { it.categoryKey == selectedCategoryKey }.ifEmpty { products }
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        SignalCategoryStrip(
            categories = categories,
            selectedKey = selectedCategoryKey,
            onSelected = onCategorySelected,
        )
        SignalProductGrid(
            products = visibleProducts,
            onProductSelected = onProductSelected,
        )
    }
}

@Composable
fun SignalCategoryStrip(
    categories: List<SignalVoucherCategory>,
    selectedKey: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        categories.chunked(3).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                row.forEach { category ->
                    SignalCategoryChip(
                        category = category,
                        selected = category.key == selectedKey,
                        onClick = { onSelected(category.key) },
                        modifier = Modifier.weight(1f),
                    )
                }
                repeat(3 - row.size) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun SignalCategoryChip(
    category: SignalVoucherCategory,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val shapes = SignalTheme.shapes
    val background = if (selected) colors.secondaryContainer else colors.surfaceContainerLowest
    val content = if (selected) colors.onSecondaryContainer else colors.onSurface
    val border = if (selected) colors.bankSecondary else colors.outlineVariant

    Column(
        modifier = modifier
            .heightIn(min = 54.dp)
            .background(background, RoundedCornerShape(shapes.md))
            .border(BorderStroke(1.dp, border), RoundedCornerShape(shapes.md))
            .clickable(onClick = onClick)
            .padding(SignalSpacing.x2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(modifier = Modifier.size(20.dp), contentAlignment = Alignment.Center) {
            category.icon()
        }
        Text(
            text = category.label,
            color = content,
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SignalProductGrid(
    products: List<SignalVoucherProduct>,
    onProductSelected: (SignalVoucherProduct) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        products.chunked(2).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                row.forEach { product ->
                    SignalVoucherProductCard(
                        product = product,
                        onClick = { onProductSelected(product) },
                        modifier = Modifier.weight(1f),
                    )
                }
                if (row.size == 1) Box(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun SignalVoucherProductCard(
    product: SignalVoucherProduct,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val shapes = SignalTheme.shapes

    Column(
        modifier = modifier
            .heightIn(min = 124.dp)
            .background(colors.surfaceContainerLowest, RoundedCornerShape(shapes.lg))
            .border(BorderStroke(1.dp, colors.outlineVariant), RoundedCornerShape(shapes.lg))
            .clickable(onClick = onClick)
            .padding(SignalSpacing.x3),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(colors.primaryContainer, RoundedCornerShape(shapes.sm)),
                contentAlignment = Alignment.Center,
            ) {
                product.icon()
            }
            Text(text = product.provider, color = colors.onSurface, style = SignalTheme.typography.rowTitle, maxLines = 1)
        }
        Text(
            text = product.description,
            color = colors.onSurfaceVariant,
            style = SignalTheme.typography.rowMeta,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = product.values.firstOrNull().orEmpty(),
            color = colors.bankSecondary,
            style = SignalTheme.typography.rowTitle,
            maxLines = 1,
        )
    }
}

@Composable
fun SignalVoucherValueSelector(
    values: List<String>,
    selectedValue: String,
    onValueSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        values.chunked(4).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                row.forEach { value ->
                    SignalVoucherValueChip(
                        value = value,
                        selected = value == selectedValue,
                        onClick = { onValueSelected(value) },
                        modifier = Modifier.weight(1f),
                    )
                }
                repeat(4 - row.size) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun SignalVoucherValueChip(
    value: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val background = if (selected) colors.bankPrimary else colors.surfaceContainerLowest
    val content = if (selected) colors.textInverse else colors.onSurface

    Box(
        modifier = modifier
            .heightIn(min = SignalTheme.dimensions.touchTarget)
            .background(background, RoundedCornerShape(SignalTheme.shapes.md))
            .border(BorderStroke(1.dp, if (selected) colors.bankPrimary else colors.outlineVariant), RoundedCornerShape(SignalTheme.shapes.md))
            .clickable(onClick = onClick)
            .padding(SignalSpacing.x2),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = value, color = content, style = SignalTheme.typography.rowTitle, maxLines = 1)
    }
}
