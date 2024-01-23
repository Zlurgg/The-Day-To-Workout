package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.util

sealed class WorkoutOrder(
    val orderType: OrderType
) {
    class Title(orderType: OrderType): WorkoutOrder(orderType)
    class Duration(orderType: OrderType): WorkoutOrder(orderType)
    class Description(orderType: OrderType): WorkoutOrder(orderType)

    fun copy(orderType: OrderType): WorkoutOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Duration -> Duration(orderType)
            is Description -> Description(orderType)
        }
    }
}
