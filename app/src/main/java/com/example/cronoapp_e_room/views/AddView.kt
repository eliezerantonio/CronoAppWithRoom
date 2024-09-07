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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.cronoapp_e_room.R
import com.example.cronoapp_e_room.components.CircleButton
import com.example.cronoapp_e_room.components.FloatButton
import com.example.cronoapp_e_room.components.MainIconButton
import com.example.cronoapp_e_room.components.MainTextField
import com.example.cronoapp_e_room.components.MainTitle
import com.example.cronoapp_e_room.components.formatTime
import com.example.cronoapp_e_room.model.Cronos
import com.example.cronoapp_e_room.viewModels.CronometroViewModel
import com.example.cronoapp_e_room.viewModels.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(
    navController: NavController,
    cronometroViewModel: CronometroViewModel,
    cronoViewModel: CronosViewModel
) {

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
        ContentAddView(it, navController, cronometroViewModel, cronoViewModel)
    }


}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    cronometroViewModel: CronometroViewModel,
    cronoViewModel: CronosViewModel
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

            CircleButton(icon = painterResource(R.drawable.play), enabled = !state.activeCrono) {
                cronometroViewModel.init()
            }
            //*pausar
            CircleButton(icon = painterResource(R.drawable.pausa), enabled = state.activeCrono) {
                cronometroViewModel.pause()
            }
            //detener
            CircleButton(icon = painterResource(R.drawable.stop), enabled = !state.activeCrono) {
                cronometroViewModel.delete()
            }
            //mostrar guardar
            CircleButton(icon = painterResource(R.drawable.save), enabled = state.showSaveButton) {
                cronometroViewModel.showTextField()
            }

        }

        if (state.showTextField) {
            MainTextField(
                state.title,
                onValueChange = { cronometroViewModel.onValue(it) },
                label = "Title"
            )

            Button(onClick = {
                cronoViewModel.addCrono(Cronos(title = state.title, crono = cronometroViewModel.time))

                cronometroViewModel.delete()
                navController.popBackStack()
            }) { Text("Guardar") }
        }


    }
}