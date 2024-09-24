package com.hsalihkucuk.jatpackcfinalproject.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Foods
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.CartScreenViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.FoodDetailViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.HomeScreenViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.SearchScreenViewModel

@Composable
fun Navigater(selectedPage: String, homeScreenViewModel: HomeScreenViewModel, foodDetailViewModel: FoodDetailViewModel, cartScreenViewModel: CartScreenViewModel, searchScreenViewModel: SearchScreenViewModel){
    val navControler = rememberNavController()

    NavHost(navController = navControler, startDestination = selectedPage){
        composable("HomeScreen"){ HomeScreen(navControler, homeScreenViewModel) }
        composable("FoodDetail/{food}", arguments = listOf(navArgument("food"){type = NavType.StringType})){
            val json = it.arguments?.getString("food")
            val foodNesne = Gson().fromJson(json, Foods::class.java)

            FoodDetailPage(navControler, foodNesne, foodDetailViewModel)
        }
        composable("CartScreen"){ CartScreen(navControler, cartScreenViewModel) }
        composable("SearchScreen"){ SearchScreen(navController = navControler, searchScreenViewModel = searchScreenViewModel) }
        composable("AccountPage"){

        }
    }
}