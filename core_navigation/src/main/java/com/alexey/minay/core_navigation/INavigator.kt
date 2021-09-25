package com.alexey.minay.core_navigation

interface INavigator {
    fun navigateTo(destination: Destination)
    fun popBackstack()
}