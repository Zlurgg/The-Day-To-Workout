package uk.co.zlurgg.thedaytoworkout.feature_workout_reminder.domain.util

sealed class WorkoutOrder(
    val orderType: OrderType
) {
    class Title(orderType: OrderType): WorkoutOrder(orderType)
    class Date(orderType: OrderType): WorkoutOrder(orderType)
    class Description(orderType: OrderType): WorkoutOrder(orderType)

    fun copy(orderType: OrderType): WorkoutOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Description -> Description(orderType)
        }
    }
}
