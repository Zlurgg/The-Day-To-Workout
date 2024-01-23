package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case

data class WorkoutUseCases(
    val getWorkout: GetWorkout,
    val getWorkouts: GetWorkouts,
    val deleteWorkout: DeleteWorkout,
    val addWorkout: AddWorkout,
)