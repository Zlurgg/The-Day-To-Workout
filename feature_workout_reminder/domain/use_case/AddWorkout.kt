package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case

import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.InvalidWorkoutException
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.repository.WorkoutRepository

class AddWorkout(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(workout: Workout) {
        if (workout.title.isBlank()) {
            throw InvalidWorkoutException("The title of the workout can't be empty")
        }
        repository.insertWorkout(workout)
    }
}