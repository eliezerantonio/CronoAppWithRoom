package com.example.cronoapp_e_room.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cronoapp_e_room.components.CircleButton
import com.example.cronoapp_e_room.components.FloatButton
import com.example.cronoapp_e_room.components.MainIconButton
import com.example.cronoapp_e_room.components.MainTitle
import com.example.cronoapp_e_room.components.formatTime
import com.example.cronoapp_e_room.viewModels.CronometroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, cronometroViewModel: CronometroViewModel) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = "Add Crono") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()

                    }
                }
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentAddView(it, navController, cronometroViewModel)
    }


}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    cronometroViewModel: CronometroViewModel,
) {
    val state = cronometroViewModel.state


    LaunchedEffect(state.activeCrono) {
        cronometroViewModel.cronos()

    }
    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTime(cronometroViewModel.time),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )


        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {

            CircleButton(icon = Icons.Default.PlayArrow, enabled = !state.activeCrono) {
                cronometroViewModel.init()
            }
            //*pausar
            CircleButton(icon = Icons.Default.Clear, enabled = state.activeCrono) {
                cronometroViewModel.pause()
            }
            //detener
            CircleButton(icon = Icons.Default.AccountCircle, enabled = !state.activeCrono) {
                cronometroViewModel.delete()
            }
            //mostrar guardar
            CircleButton(icon = Icons.Default.PlayArrow, enabled = state.showSaveButton) {
                cronometroViewModel.showTextField()
            }

        }


    }
}