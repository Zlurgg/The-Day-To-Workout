package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case

import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.repository.WorkoutRepository

class GetWorkout(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(id: Int): Workout? {
        return repository.getWorkoutById(id)
    }
}