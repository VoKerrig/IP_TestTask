package com.example.iptesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iptesttask.main_screen.MainScreen
import com.example.iptesttask.navigation.MainScreenObject
import com.example.iptesttask.ui.theme.Blue
import com.example.iptesttask.ui.theme.IPTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPTestTaskTheme {
                Scaffold(topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                                Text(text = stringResource(R.string.mainActivity_title))
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Blue
                        )
                    )
                }) { paddingValues ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = MainScreenObject,
                        modifier = Modifier.padding(paddingValues))
                    {
                        composable<MainScreenObject> {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}
