package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.presentation.add_edit_workout.components

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import uk.co.zlurgg.thedaytoworkout.ui.theme.paddingMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentDurationField(
    modifier: Modifier = Modifier,
    duration: Long,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    ) {
    Row(
        modifier = modifier
            .padding(paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = modifier.weight(0.4f),
            horizontalAlignment = Alignment.End
        ) {
            TextField(
                value = duration.toString(),
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TransparentDurationFieldPreview() {
    TransparentDurationField(
        duration = 0L,
        onValueChange = {}
    )
}