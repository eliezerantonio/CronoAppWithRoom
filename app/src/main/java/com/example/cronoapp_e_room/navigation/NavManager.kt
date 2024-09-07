package com.example.cronoapp_e_room.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cronoapp_e_room.viewModels.CronometroViewModel
import com.example.cronoapp_e_room.viewModels.CronosViewModel
import com.example.cronoapp_e_room.views.AddView
import com.example.cronoapp_e_room.views.EditView
import com.example.cronoapp_e_room.views.HomeView

@Composable
fun NavManager(cronometroViewModel: CronometroViewModel, cronosViewModel: CronosViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeView(navController, cronosViewModel)
        }
        composable("AddView") {
            AddView(navController, cronometroViewModel, cronosViewModel)
        }
        composable("EditView") {
            EditView(navController)
        }

    }

}