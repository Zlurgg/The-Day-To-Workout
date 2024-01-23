package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.workouts.component

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.work.Data
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.core.notifications.NotificationWorker
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.core.notifications.Notifications
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingMedium
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun WorkoutReminderTimePicker(
    modifier: Modifier = Modifier,
) {
    lateinit var checkNotificationPermission: ActivityResultLauncher<String>
    var isPermission: Boolean
    val context = LocalContext.current

    val timeDialogState = rememberMaterialDialogState()
    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Reminder time:",
            style = MaterialTheme.typography.titleLarge
        )
        Button(
            shape = MaterialTheme.shapes.extraSmall,
            onClick = {
            timeDialogState.show()
        }) {
            Icon(
                imageVector = Icons.Default.Timelapse,
                contentDescription = "workout notification time"
            )
        }
        Spacer(modifier = modifier.height(paddingMedium))
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.headlineSmall
        )
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok")
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            title = "Pick a time",
        ) {
            pickedTime = it
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    isPermission = true
                } else {
                    isPermission = false

                    checkNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            } else {
                isPermission = true
            }
            if (isPermission) {
                val data = Data.Builder().putInt(NotificationWorker.NOTIFICATION_ID, 0).build()
                Notifications.scheduleNotification(data, context, pickedTime.atDate(LocalDate.now()))
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    checkNotificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun TimePickerPreview() {
    WorkoutReminderTimePicker()
}
