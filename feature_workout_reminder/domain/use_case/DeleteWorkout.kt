package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case

import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.repository.WorkoutRepository

class DeleteWorkout(
    private val repository: WorkoutRepository
){
    suspend operator fun invoke(workout: Workout) {
        repository.deleteWorkout(workout)
    }
}