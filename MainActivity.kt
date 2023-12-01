package uk.co.zlurgg.thedaytoworkout

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.TheDayToWorkoutApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheDayToWorkoutApp()
        }
    }
    companion object {
        lateinit  var appContext: Context
    }
}

