package com.example.cronoapp_e_room

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cronoapp_e_room.navigation.NavManager
import com.example.cronoapp_e_room.ui.theme.CronoAppeRoomTheme
import com.example.cronoapp_e_room.viewModels.CronometroViewModel
import com.example.cronoapp_e_room.viewModels.CronosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cronometroVM: CronometroViewModel by viewModels()
        val cronosVm: CronosViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            CronoAppeRoomTheme {
                NavManager(cronometroVM, cronosVm)

            }
        }
    }
}

