package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout

interface WorkoutRepository {
    fun getWorkouts(): Flow<List<Workout>>

    suspend fun getWorkoutById(id: Int): Workout?

    suspend fun insertWorkout(workout: Workout)

    suspend fun deleteWorkout(workout: Workout)
}