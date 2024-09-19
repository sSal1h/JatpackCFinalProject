package com.hsalihkucuk.jatpackcfinalproject.uix.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsalihkucuk.jatpackcfinalproject.R
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.CartScreenViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.FoodDetailViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(homeScreenViewModel: HomeScreenViewModel, foodDetailViewModel: FoodDetailViewModel, cartScreenViewModel: CartScreenViewModel){
    val selectedPage = remember { mutableStateOf(0) }
    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.app_name), color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold) },
        actions = { IconButton(onClick = {
            selectedPage.value = 4
        },content = {Icon(painter = painterResource(id = R.drawable.cart_icon),contentDescription = "Cart Icon")})  }) },
        bottomBar = { BottomAppBar(modifier = Modifier.height(65.dp), containerColor = Color.DarkGray, contentColor = Color.White, content = {
        NavigationBarItem(
            selected = selectedPage.value == 0,
            onClick = { selectedPage.value = 0 },
            icon = { Icon(modifier = Modifier.size(33.dp), painter = painterResource(id = R.drawable.home_icon), contentDescription = "Home Icon") })
        NavigationBarItem(
            selected = selectedPage.value == 1,
            onClick = { selectedPage.value = 1 },
            icon = { Icon(modifier = Modifier.size(33.dp), painter = painterResource(id = R.drawable.search_icon), contentDescription = "Search Icon") })
        NavigationBarItem(
            selected = selectedPage.value == 2,
            onClick = { selectedPage.value = 2 },
            icon = { Icon(modifier = Modifier.size(33.dp), painter = painterResource(id = R.drawable.account_icon), contentDescription = "Account Icon") })
        NavigationBarItem(
            selected = selectedPage.value == 3,
            onClick = { selectedPage.value = 3 },
            icon = { Icon(modifier = Modifier.size(33.dp), painter = painterResource(id = R.drawable.offer_icon), contentDescription = "Offer Icon") })
    })
    }) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {

            when(selectedPage.value){
                0 -> Navigater("HomeScreen", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel)
                1 -> Navigater("SearchScreen", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel)
                2 -> Navigater("AccountPage", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel)
                3 -> Navigater("OffersPage", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel)
                4 -> Navigater("CartScreen", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel)
            }
        }
    }
}