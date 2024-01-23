package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.workouts.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingSmall

@Composable
fun DailyWorkoutChecklist(
    modifier: Modifier = Modifier
) {
    val days = listOf(1, 2, 3, 4, 5, 6, 7)
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(days) { day ->
            DailyWorkoutItem(modifier, day)
        }
    }
}

@Composable
fun DailyWorkoutItem(
    modifier: Modifier = Modifier,
    day: Int,
    cornerRadius: Dp = 10.dp,
    ) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        var checked by remember {
            mutableStateOf(false)
        }
        var color by remember {
            mutableStateOf(Color.Black)
        }
        color = if (checked) {
            MaterialTheme.colorScheme.inversePrimary
        } else {
            MaterialTheme.colorScheme.primaryContainer
        }

        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath) {
                drawRoundRect(
                    color = color,
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Spacer(modifier = Modifier.padding(horizontal = paddingSmall))
        IconButton(
            onClick = {
                checked = !checked
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text(
                text = "$day",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyWorkoutChecklistPreview() {
    DailyWorkoutChecklist()
}
