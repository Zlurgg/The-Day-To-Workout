package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.workouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.Screen
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.workouts.component.DailyWorkoutChecklist
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.workouts.component.WorkoutReminderTimePicker
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingSmall

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WorkoutHomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var isSectionVisible by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = modifier.padding(paddingSmall),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            text = "Workouts",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = modifier.fillMaxWidth()
                ) {
                    DailyWorkoutChecklist()
                }
            },
            floatingActionButton = {
                // create new workout
                   IconButton(onClick = {
                       navController.navigate(Screen.AddEditWorkoutScreen.route)
                   }) {
                       Icon(
                           modifier = modifier
                               .size(width = 40.dp, height = 40.dp)
                               .background(MaterialTheme.colorScheme.primaryContainer),
                           imageVector = Icons.Default.Add,
                           contentDescription = "new workout"
                       )
                   }
            },
            content = { paddingValues ->
                Box(
                    modifier = modifier
                        .padding(
                            bottom = paddingValues.calculateBottomPadding(),
                            top = paddingValues.calculateTopPadding()
                        )
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Daily Workout times:",
                            style = MaterialTheme.typography.titleLarge
                        )
                        IconButton(onClick = {
                            isSectionVisible = !isSectionVisible
                        }) {
                            Icon(
                                imageVector = Icons.Default.Sort,
                                contentDescription = "top section dropdown"
                            )
                        }
                    }
                    AnimatedVisibility(
                        visible = isSectionVisible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        WorkoutReminderTimePicker()
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true, heightDp = 320)
@Composable
private fun WorkoutHomeScreenPreview() {
    WorkoutHomeScreen(rememberNavController())
}