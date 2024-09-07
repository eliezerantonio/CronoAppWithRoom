package com.example.cronoapp_e_room.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.cronoapp_e_room.components.FloatButton
import com.example.cronoapp_e_room.components.MainIconButton
import com.example.cronoapp_e_room.components.MainTitle
import com.example.cronoapp_e_room.components.formatTime
import com.example.cronoapp_e_room.viewModels.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, cronosViewModel: CronosViewModel) {

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Crono APP") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),

                )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {

        ContentHomeView(it, navController, cronosViewModel)
    }


}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, cronosViewModel: CronosViewModel) {
    Column(modifier = Modifier.padding(it)) {
        val cronosList  by cronosViewModel.cronosList.collectAsState()

        LazyColumn {
            items(cronosList){ item ->
                Text(text = formatTime(item.crono))

            }
        }

    }
}