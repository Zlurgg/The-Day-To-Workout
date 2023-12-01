package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.util

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}
