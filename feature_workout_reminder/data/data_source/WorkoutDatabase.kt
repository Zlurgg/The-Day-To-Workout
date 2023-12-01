package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model.Workout

@Database(
    entities = [Workout::class],
    version = 1
)
abstract class WorkoutDatabase: RoomDatabase() {
    abstract val workoutDao: WorkoutDao

    companion object {
        const val DATABASE_NAME = "thedayto_workout_db"
    }
}