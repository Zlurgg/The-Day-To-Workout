package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.data.repository

import kotlinx.coroutines.flow.Flow
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.data.data_source.WorkoutDao
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.repository.WorkoutRepository

class WorkoutRepositoryImpl(
    private val dao: WorkoutDao
): WorkoutRepository {
    override fun getWorkouts(): Flow<List<Workout>> {
        return dao.getWorkouts()
    }

    override suspend fun getWorkoutById(id: Int): Workout? {
        return dao.getWorkoutById(id)
    }

    override suspend fun insertWorkout(workout: Workout) {
        return dao.insertWorkout(workout)
    }

    override suspend fun deleteWorkout(workout: Workout) {
        return dao.deleteWorkout(workout)
    }

}