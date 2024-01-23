package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.InvalidWorkoutException
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case.WorkoutUseCases
import javax.inject.Inject

@HiltViewModel
class AddEditWorkoutViewModel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _workoutTitle = mutableStateOf(
        WorkoutTextFieldState(
            hint = "Workout title..."
        )
    )
    val workoutTitle: State<WorkoutTextFieldState> = _workoutTitle

    private val _workoutDescription = mutableStateOf(
        WorkoutTextFieldState(
            hint = "Workout description..."
        )
    )
    val workoutDescription: State<WorkoutTextFieldState> = _workoutDescription

    private val _workoutDuration = mutableLongStateOf(0L)
    val workoutDuration: State<Long> = _workoutDuration

    private val _workoutCompleted = mutableStateOf(false)
    val workoutCompleted: State<Boolean> = _workoutCompleted

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentWorkoutId: Int? = null

    init {
        savedStateHandle.get<Int>("workoutId")?.let { workoutId ->
            if (workoutId != -1) {
                viewModelScope.launch {
                    workoutUseCases.getWorkout(workoutId)?.also { workout ->
                        currentWorkoutId = workout.id
                        _workoutTitle.value = workoutTitle.value.copy(
                            text = workout.title,
                            isHintVisible = false
                        )
                        _workoutDescription.value = workoutDescription.value.copy(
                            text = workout.description,
                            isHintVisible = false
                        )
                        _workoutDuration.longValue = workout.duration
                        _workoutCompleted.value = workout.completed
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditWorkoutEvent) {
        when (event) {
            is AddEditWorkoutEvent.EnteredTitle -> {
                _workoutTitle.value = workoutTitle.value.copy(
                    text = event.value
                )
            }

            is AddEditWorkoutEvent.ChangeTitleFocus -> {
                _workoutTitle.value = workoutTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            workoutTitle.value.text.isBlank()
                )
            }

            is AddEditWorkoutEvent.EnteredDescription -> {
                _workoutDescription.value = workoutDescription.value.copy(
                    text = event.value
                )
            }

            is AddEditWorkoutEvent.ChangeDescriptionFocus -> {
                _workoutDescription.value = workoutDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            workoutDescription.value.text.isBlank()
                )
            }

            is AddEditWorkoutEvent.EnteredDuration -> {
                _workoutDuration.longValue = event.value
            }

            is AddEditWorkoutEvent.ChangeDuration -> {
                _workoutDuration.longValue = event.focusState
            }

            is AddEditWorkoutEvent.Completed -> {
                _workoutCompleted.value = event.value
            }

            is AddEditWorkoutEvent.SaveWorkout -> {
                viewModelScope.launch {
                    try {
                        workoutUseCases.addWorkout(
                            Workout(
                                title = workoutTitle.value.text,
                                description = workoutDescription.value.text,
                                duration = workoutDuration.value,
                                completed = workoutCompleted.value,
                                id = currentWorkoutId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveWorkout)
                    } catch (e: InvalidWorkoutException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save workout"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveWorkout : UiEvent()
    }
}