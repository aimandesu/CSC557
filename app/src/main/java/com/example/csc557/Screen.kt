package com.example.csc557

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object CarDetailScreen : Screen("car_details_screen")
    object PaymentDetailScreen : Screen("payment_detail_screen")
    object SearchScreen: Screen("search_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }

        }
    }

}

