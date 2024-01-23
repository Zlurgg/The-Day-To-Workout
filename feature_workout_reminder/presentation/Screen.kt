package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation

sealed class Screen(val route: String) {
    data object WorkoutHomeScreen: Screen("workout_home_screen")
    data object AddEditWorkoutScreen: Screen("add_edit_workout_screen")
}