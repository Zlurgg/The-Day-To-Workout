package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workout(
    val title: String,
    val description: String,
    val duration: Long,
    val completed: Boolean,
    @PrimaryKey val id: Int? = null
)

class InvalidWorkoutException(message: String): Exception(message)