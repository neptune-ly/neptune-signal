package ly.neptune.signal.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalComponentMetrics
import kotlin.math.min

enum class SignalIconName {
    Home,
    Accounts,
    Cards,
    More,
    Bell,
    Shield,
    QrCode,
    Voucher,
    Services,
    Transfer,
    Chart,
    ArrowUpRight,
    ChevronRight,
    ChevronStart,
    ChevronEnd,
    ArrowStart,
    ArrowEnd,
    Eye,
    EyeOff,
    Copy,
    Key,
    Smartphone,
    Lock,
    User,
    Languages,
    Support,
    Chat,
    Settings,
    SignOut,
    Search,
    CheckCircle,
    ErrorCircle,
    Clock,
}

@Composable
fun SignalIcon(
    name: SignalIconName,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    size: Dp = SignalComponentMetrics.standardIcon,
    strokeWidth: Dp = 2.25.dp,
) {
    val layoutDirection = LocalLayoutDirection.current
    val resolvedName = when (name) {
        SignalIconName.ChevronStart -> if (layoutDirection == LayoutDirection.Rtl) SignalIconName.ChevronEnd else SignalIconName.ChevronStart
        SignalIconName.ChevronEnd -> if (layoutDirection == LayoutDirection.Rtl) SignalIconName.ChevronStart else SignalIconName.ChevronEnd
        SignalIconName.ArrowStart -> if (layoutDirection == LayoutDirection.Rtl) SignalIconName.ArrowEnd else SignalIconName.ArrowStart
        SignalIconName.ArrowEnd -> if (layoutDirection == LayoutDirection.Rtl) SignalIconName.ArrowStart else SignalIconName.ArrowEnd
        else -> name
    }
    Canvas(modifier = modifier.size(size)) {
        val canvasSize = this.size
        val finalStroke = strokeWidth.toPx()
        val inset = finalStroke * 0.72f
        val iconScale = min(
            (canvasSize.width - inset * 2f).coerceAtLeast(1f) / 24f,
            (canvasSize.height - inset * 2f).coerceAtLeast(1f) / 24f,
        )
        val dx = (canvasSize.width - 24f * iconScale) / 2f
        val dy = (canvasSize.height - 24f * iconScale) / 2f
        withTransform({
            translate(left = dx, top = dy)
            scale(scaleX = iconScale, scaleY = iconScale, pivot = Offset.Zero)
        }) {
            val stroke = Stroke(
                width = finalStroke / iconScale,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round,
            )
            drawSignalIcon(name = resolvedName, color = tint, stroke = stroke)
        }
    }
}

private fun DrawScope.drawSignalIcon(name: SignalIconName, color: Color, stroke: Stroke) {
    fun path(data: String) {
        drawPath(
            path = PathParser().parsePathString(data).toPath(),
            color = color,
            style = stroke,
        )
    }
    fun line(x1: Float, y1: Float, x2: Float, y2: Float) {
        drawLine(
            color = color,
            start = Offset(x1, y1),
            end = Offset(x2, y2),
            strokeWidth = stroke.width,
            cap = StrokeCap.Round,
        )
    }
    fun rect(x: Float, y: Float, width: Float, height: Float, radius: Float = 2f) {
        drawRoundRect(
            color = color,
            topLeft = Offset(x, y),
            size = Size(width, height),
            cornerRadius = CornerRadius(radius, radius),
            style = stroke,
        )
    }
    fun circle(x: Float, y: Float, radius: Float, filled: Boolean = false) {
        drawCircle(
            color = color,
            radius = radius,
            center = Offset(x, y),
            style = if (filled) androidx.compose.ui.graphics.drawscope.Fill else stroke,
        )
    }

    when (name) {
        SignalIconName.Home -> {
            path("M15 21v-8a1 1 0 0 0-1-1h-4a1 1 0 0 0-1 1v8")
            path("M3 10a2 2 0 0 1 .709-1.528l7-6a2 2 0 0 1 2.582 0l7 6A2 2 0 0 1 21 10v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z")
        }
        SignalIconName.Accounts -> {
            path("M19 7V4a1 1 0 0 0-1-1H5a2 2 0 0 0 0 4h15a1 1 0 0 1 1 1v4h-3a2 2 0 0 0 0 4h3a1 1 0 0 0 1-1v-2a1 1 0 0 0-1-1")
            path("M3 5v14a2 2 0 0 0 2 2h15a1 1 0 0 0 1-1v-4")
        }
        SignalIconName.Cards -> {
            rect(2f, 5f, 20f, 14f, 2f)
            line(2f, 10f, 22f, 10f)
        }
        SignalIconName.More -> {
            line(4f, 5f, 20f, 5f)
            line(4f, 12f, 20f, 12f)
            line(4f, 19f, 20f, 19f)
        }
        SignalIconName.Bell -> {
            path("M10.268 21a2 2 0 0 0 3.464 0")
            path("M3.262 15.326A1 1 0 0 0 4 17h16a1 1 0 0 0 .74-1.673C19.41 13.956 18 12.499 18 8A6 6 0 0 0 6 8c0 4.499-1.411 5.956-2.738 7.326")
        }
        SignalIconName.Shield -> path("M20 13c0 5-3.5 7.5-7.66 8.95a1 1 0 0 1-.67-.01C7.5 20.5 4 18 4 13V6a1 1 0 0 1 1-1c2 0 4.5-1.2 6.24-2.72a1.17 1.17 0 0 1 1.52 0C14.51 3.81 17 5 19 5a1 1 0 0 1 1 1z")
        SignalIconName.QrCode -> {
            rect(3f, 3f, 5f, 5f, 1f)
            rect(16f, 3f, 5f, 5f, 1f)
            rect(3f, 16f, 5f, 5f, 1f)
            path("M21 16h-3a2 2 0 0 0-2 2v3")
            path("M21 21v.01")
            path("M12 7v3a2 2 0 0 1-2 2H7")
            path("M3 12h.01")
            path("M12 3h.01")
            path("M12 16v.01")
            path("M16 12h1")
            path("M21 12v.01")
            path("M12 21v-1")
        }
        SignalIconName.Voucher -> {
            path("M2 9a3 3 0 0 1 0 6v2a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-2a3 3 0 0 1 0-6V7a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2Z")
            path("M13 5v2")
            path("M13 17v2")
            path("M13 11v2")
        }
        SignalIconName.Services -> {
            path("M12 3v17a1 1 0 0 1-1 1H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v6a1 1 0 0 1-1 1H3")
            path("M16 19h6")
            path("M19 22v-6")
        }
        SignalIconName.Transfer -> {
            path("M3.714 3.048a.498.498 0 0 0-.683.627l2.843 7.627a2 2 0 0 1 0 1.396l-2.842 7.627a.498.498 0 0 0 .682.627l18-8.5a.5.5 0 0 0 0-.904z")
            path("M6 12h16")
        }
        SignalIconName.Chart -> {
            path("M3 3v16a2 2 0 0 0 2 2h16")
            path("M18 17V9")
            path("M13 17V5")
            path("M8 17v-3")
        }
        SignalIconName.ArrowUpRight -> {
            path("M7 7h10v10")
            path("M7 17 17 7")
        }
        SignalIconName.ChevronRight -> path("m9 18 6-6-6-6")
        SignalIconName.ChevronStart -> path("m15 18-6-6 6-6")
        SignalIconName.ChevronEnd -> path("m9 18 6-6-6-6")
        SignalIconName.ArrowStart -> {
            path("m12 19-7-7 7-7")
            path("M19 12H5")
        }
        SignalIconName.ArrowEnd -> {
            path("M5 12h14")
            path("m12 5 7 7-7 7")
        }
        SignalIconName.Eye -> {
            path("M2.062 12.348a1 1 0 0 1 0-.696 10.75 10.75 0 0 1 19.876 0 1 1 0 0 1 0 .696 10.75 10.75 0 0 1-19.876 0")
            circle(12f, 12f, 3f)
        }
        SignalIconName.EyeOff -> {
            path("M10.733 5.076a10.744 10.744 0 0 1 11.205 6.575 1 1 0 0 1 0 .696 10.747 10.747 0 0 1-1.444 2.49")
            path("M14.084 14.158a3 3 0 0 1-4.242-4.242")
            path("M17.479 17.499a10.75 10.75 0 0 1-15.417-5.151 1 1 0 0 1 0-.696 10.75 10.75 0 0 1 4.446-5.143")
            path("m2 2 20 20")
        }
        SignalIconName.Copy -> {
            rect(8f, 8f, 14f, 14f, 2f)
            path("M4 16c-1.1 0-2-.9-2-2V4c0-1.1.9-2 2-2h10c1.1 0 2 .9 2 2")
        }
        SignalIconName.Key -> {
            path("M2.586 17.414A2 2 0 0 0 2 18.828V21a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h1a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h.172a2 2 0 0 0 1.414-.586l.814-.814a6.5 6.5 0 1 0-4-4z")
            circle(16.5f, 7.5f, 0.6f, filled = true)
        }
        SignalIconName.Smartphone -> {
            rect(5f, 2f, 14f, 20f, 2f)
            path("M12 18h.01")
        }
        SignalIconName.Lock -> {
            circle(12f, 16f, 1f)
            rect(3f, 10f, 18f, 12f, 2f)
            path("M7 10V7a5 5 0 0 1 10 0v3")
        }
        SignalIconName.User -> {
            circle(12f, 8f, 5f)
            path("M20 21a8 8 0 0 0-16 0")
        }
        SignalIconName.Languages -> {
            path("m5 8 6 6")
            path("m4 14 6-6 2-3")
            path("M2 5h12")
            path("M7 2h1")
            path("m22 22-5-10-5 10")
            path("M14 18h6")
        }
        SignalIconName.Support -> path("M3 14h3a2 2 0 0 1 2 2v3a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-7a9 9 0 0 1 18 0v7a2 2 0 0 1-2 2h-1a2 2 0 0 1-2-2v-3a2 2 0 0 1 2-2h3")
        SignalIconName.Chat -> {
            path("M16 10a2 2 0 0 1-2 2H6.828a2 2 0 0 0-1.414.586l-2.202 2.202A.71.71 0 0 1 2 14.286V4a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2z")
            path("M20 9a2 2 0 0 1 2 2v10.286a.71.71 0 0 1-1.212.502l-2.202-2.202A2 2 0 0 0 17.172 19H10a2 2 0 0 1-2-2v-1")
        }
        SignalIconName.Settings -> {
            path("M9.671 4.136a2.34 2.34 0 0 1 4.659 0 2.34 2.34 0 0 0 3.319 1.915 2.34 2.34 0 0 1 2.33 4.033 2.34 2.34 0 0 0 0 3.831 2.34 2.34 0 0 1-2.33 4.033 2.34 2.34 0 0 0-3.319 1.915 2.34 2.34 0 0 1-4.659 0 2.34 2.34 0 0 0-3.32-1.915 2.34 2.34 0 0 1-2.33-4.033 2.34 2.34 0 0 0 0-3.831A2.34 2.34 0 0 1 6.35 6.051a2.34 2.34 0 0 0 3.319-1.915")
            circle(12f, 12f, 3f)
        }
        SignalIconName.SignOut -> {
            path("m16 17 5-5-5-5")
            path("M21 12H9")
            path("M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4")
        }
        SignalIconName.Search -> {
            path("m21 21-4.34-4.34")
            circle(11f, 11f, 8f)
        }
        SignalIconName.CheckCircle -> {
            circle(12f, 12f, 10f)
            path("m9 12 2 2 4-4")
        }
        SignalIconName.ErrorCircle -> {
            circle(12f, 12f, 10f)
            path("m15 9-6 6")
            path("m9 9 6 6")
        }
        SignalIconName.Clock -> {
            circle(12f, 12f, 10f)
            path("M12 6v6l4 2")
        }
    }
}
