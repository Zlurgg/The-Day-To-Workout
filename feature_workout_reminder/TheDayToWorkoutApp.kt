package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder

import androidx.compose.runtime.Composable
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.home.HomeScreen
import uk.co.zlurgg.thedaytoworkout.ui.theme.TheDayToWorkoutTheme


@Composable
fun TheDayToWorkoutApp() {
    TheDayToWorkoutTheme {
        HomeScreen()
    }
}