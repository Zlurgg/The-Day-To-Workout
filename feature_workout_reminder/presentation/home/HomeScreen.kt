package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.home.component.TimePickerItem
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingMedium
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingSmall

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(modifier: Modifier = Modifier) {
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
                            text = "Daily Workout:",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                )
            },
            bottomBar = {
                BottomAppBar() {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.AddCircle, contentDescription = "Localized description")
                    }

                    // Daily workout checkbox
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
                    TimePickerItem()
                }
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}