package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.Screen
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout.AddEditWorkoutScreen
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.workouts.WorkoutHomeScreen
import uk.co.zlurgg.thedaytoworkout.ui.theme.TheDayToWorkoutTheme


@Composable
fun TheDayToWorkoutApp() {
    TheDayToWorkoutTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.WorkoutHomeScreen.route
        ) {
            composable(route = Screen.WorkoutHomeScreen.route) {
                WorkoutHomeScreen(navController = navController)
            }
            composable(
                route = Screen.AddEditWorkoutScreen.route +
                        "?workoutId={workoutId}",
                arguments = listOf(
                    navArgument(
                        name = "workoutId"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                AddEditWorkoutScreen(
                    navController = navController
                )
            }
        }
    }
}