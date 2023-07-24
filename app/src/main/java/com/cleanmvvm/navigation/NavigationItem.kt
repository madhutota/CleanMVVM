package com.cleanmvvm.navigation

sealed class NavigationItem(var route: String) {

    object Home : NavigationItem("Home")
    object DetailsScreen : NavigationItem("DetailsScreen")

}