package com.hsalihkucuk.jatpackcfinalproject.uix.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Foods
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.HomeScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(navController: NavController, homeScreenViewModel: HomeScreenViewModel){
    val foodList = homeScreenViewModel.foodList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        homeScreenViewModel.getAllFoods()
    }

    LazyVerticalGrid(columns = GridCells.Fixed(count = 2), modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)) {
        items(count = foodList.value.count(), itemContent = {
            val food = foodList.value[it]
            FoodItemCard(navController, food)
        })
    }
}

@Composable
fun FoodItemCard(navController: NavController, food: Foods) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val foodJson = Gson().toJson(food)
                navController.navigate("FoodDetail/$foodJson")
            }
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            var url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
            GlideImage(imageModel = url, modifier = Modifier.size(100.dp, 100.dp))
            Text(text = food.yemek_adi, style = MaterialTheme.typography.titleMedium)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "${food.yemek_fiyat}₺", style = MaterialTheme.typography.titleSmall)

            }
        }
    }
}