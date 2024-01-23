package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.repository.WorkoutRepository
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.util.OrderType
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.util.WorkoutOrder

class GetWorkouts(
    private val repository: WorkoutRepository
) {
    operator fun invoke(
        workoutOrder: WorkoutOrder = WorkoutOrder.Title(OrderType.Descending)
    ): Flow<List<Workout>> {
        return repository.getWorkouts().map { workouts ->
            when (workoutOrder.orderType) {
                is OrderType.Ascending -> {
                    when (workoutOrder) {
                        is WorkoutOrder.Title -> workouts.sortedBy { it.title.lowercase() }
                        is WorkoutOrder.Duration -> workouts.sortedBy { it.duration}
                        is WorkoutOrder.Description -> workouts.sortedBy { it.description.lowercase() }
                    }
                }
                is OrderType.Descending -> {
                    when (workoutOrder) {
                        is WorkoutOrder.Title -> workouts.sortedByDescending { it.title.lowercase() }
                        is WorkoutOrder.Duration -> workouts.sortedByDescending { it.duration }
                        is WorkoutOrder.Description -> workouts.sortedByDescending { it.description.lowercase() }
                    }
                }
            }
        }
    }
}