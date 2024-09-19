package com.hsalihkucuk.jatpackcfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.JatpackCFinalProjectTheme
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.CartScreenViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.FoodDetailViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.HomeScreenViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.views.BottomNavBar
import com.hsalihkucuk.jatpackcfinalproject.uix.views.Navigater
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val homeScreenViewModel : HomeScreenViewModel by viewModels()
    val foodDetailViewModel : FoodDetailViewModel by viewModels()
    val cartScreenViewModel : CartScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JatpackCFinalProjectTheme {
                BottomNavBar(homeScreenViewModel, foodDetailViewModel, cartScreenViewModel)
            }
        }
    }
}