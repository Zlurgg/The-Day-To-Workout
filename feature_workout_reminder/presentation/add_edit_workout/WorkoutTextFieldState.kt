package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout

data class WorkoutTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)