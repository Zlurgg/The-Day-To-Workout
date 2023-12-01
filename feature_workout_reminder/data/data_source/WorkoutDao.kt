package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout

@Dao
interface WorkoutDao {
    @Query("select * from workout")
    fun getWorkouts(): Flow<List<Workout>>

    @Query("select * from workout where id = :id")
    suspend fun getWorkoutById(id: Int): Workout?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)
}