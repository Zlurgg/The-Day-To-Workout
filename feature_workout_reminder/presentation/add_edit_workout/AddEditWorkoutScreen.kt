package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import uk.co.zlurgg.thedaytoworkout.R
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout.components.TransparentDurationField
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout.components.TransparentHintTextField
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingMedium
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditWorkoutScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AddEditWorkoutViewModel = hiltViewModel()
) {

    val titleState = viewModel.workoutTitle.value
    val descriptionState = viewModel.workoutDescription.value
    val durationState = viewModel.workoutDuration.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditWorkoutViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditWorkoutViewModel.UiEvent.SaveWorkout -> {
                    navController.navigateUp()
                }
            }
        }
    }


    Scaffold(
        modifier = modifier.padding(paddingSmall),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = stringResource(R.string.add_edit_workout_create_a_new_workout),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditWorkoutEvent.SaveWorkout)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = stringResource(R.string.add_edit_workout_save))
            }
        },
        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .padding(paddingSmall)
        ) {
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditWorkoutEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditWorkoutEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(paddingMedium))
            TransparentHintTextField(
                text = descriptionState.text,
                hint = descriptionState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditWorkoutEvent.EnteredDescription(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditWorkoutEvent.ChangeDescriptionFocus(it))
                },
                isHintVisible = descriptionState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(paddingMedium))
            TransparentDurationField(
                duration = durationState,
                onValueChange = {
                    viewModel.onEvent(AddEditWorkoutEvent.EnteredDuration(it.toLong()))
                },
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}