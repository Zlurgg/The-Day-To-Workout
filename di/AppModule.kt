package uk.co.zlurgg.thedaytoworkout.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.data.data_source.WorkoutDatabase
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.data.repository.WorkoutRepositoryImpl
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.repository.WorkoutRepository
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case.AddWorkout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case.DeleteWorkout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case.GetWorkout
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case.GetWorkouts
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.use_case.WorkoutUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWorkoutsDatabase(app: Application): WorkoutDatabase {
        return Room.databaseBuilder(
            app,
            WorkoutDatabase::class.java,
            WorkoutDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWorkoutRepository(db: WorkoutDatabase): WorkoutRepository {
        return WorkoutRepositoryImpl(db.workoutDao)
    }

    @Provides
    @Singleton
    fun provideWorkoutUseCases(repository: WorkoutRepository): WorkoutUseCases {
        return WorkoutUseCases(
            getWorkout = GetWorkout(repository),
            deleteWorkout = DeleteWorkout(repository),
            addWorkout = AddWorkout(repository),
            getWorkouts = GetWorkouts(repository)
        )
    }
}