package ly.neptune.signal.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

enum class SignalTopBarNavigation {
    None,
    Back,
    Close,
}

enum class SignalTopBarType {
    Small,
    CenterAligned,
    Medium,
    Large,
}

data class SignalTopBarContract(
    val title: String,
    val subtitle: String? = null,
    val navigation: SignalTopBarNavigation = SignalTopBarNavigation.Back,
    val type: SignalTopBarType = SignalTopBarType.CenterAligned,
    val elevated: Boolean = false,
    val navigationContentDescription: String = "Back",
)

@Composable
fun SignalContractTopBar(
    contract: SignalTopBarContract,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    navigationOverride: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    SignalTopBar(
        title = contract.title,
        subtitle = contract.subtitle,
        modifier = modifier,
        navigation = navigationOverride ?: when (contract.navigation) {
            SignalTopBarNavigation.None -> null
            SignalTopBarNavigation.Back -> {
                {
                    SignalIconButton(
                        onClick = onNavigate,
                        modifier = Modifier.semantics {
                            role = Role.Button
                            contentDescription = contract.navigationContentDescription
                        },
                    ) {
                        SignalIcon(SignalIconName.ArrowStart, size = 24.dp)
                    }
                }
            }
            SignalTopBarNavigation.Close -> {
                {
                    SignalIconButton(
                        onClick = onNavigate,
                        modifier = Modifier.semantics {
                            role = Role.Button
                            contentDescription = contract.navigationContentDescription
                        },
                    ) {
                        SignalCloseMark()
                    }
                }
            }
        },
        actions = actions,
    )
}

@Composable
private fun SignalCloseMark() {
    val color = androidx.compose.material3.LocalContentColor.current
    androidx.compose.foundation.Canvas(modifier = Modifier.size(24.dp)) {
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(size.width * 0.28f, size.height * 0.28f),
            end = androidx.compose.ui.geometry.Offset(size.width * 0.72f, size.height * 0.72f),
            strokeWidth = 2.35.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(size.width * 0.72f, size.height * 0.28f),
            end = androidx.compose.ui.geometry.Offset(size.width * 0.28f, size.height * 0.72f),
            strokeWidth = 2.35.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round,
        )
    }
}
