package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout

import androidx.compose.ui.focus.FocusState

sealed class AddEditWorkoutEvent {
    data class EnteredTitle(val value: String): AddEditWorkoutEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditWorkoutEvent()
    data class EnteredDescription(val value: String): AddEditWorkoutEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): AddEditWorkoutEvent()
    data class EnteredDuration(val value: Long): AddEditWorkoutEvent()
    data class ChangeDuration(val focusState: Long): AddEditWorkoutEvent()
    data class Completed(val value: Boolean): AddEditWorkoutEvent()
    data object SaveWorkout: AddEditWorkoutEvent()
}