package com.nx.nxr.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.nx.nxr.navigation.NavGraph
import com.nx.nxr.ui.NXRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NXRTheme {
                NavGraph(
                    navController = rememberNavController()
                )
            }
        }
    }
}