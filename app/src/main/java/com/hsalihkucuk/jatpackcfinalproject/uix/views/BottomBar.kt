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
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
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
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.DarkPrimaryColor
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.PrimaryColor
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.SecondaryColor
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.CartScreenViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.FoodDetailViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.HomeScreenViewModel
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.SearchScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    homeScreenViewModel: HomeScreenViewModel,
    foodDetailViewModel: FoodDetailViewModel,
    cartScreenViewModel: CartScreenViewModel,
    searchScreenViewModel: SearchScreenViewModel
){
    val selectedPage = remember { mutableStateOf(0) }

    Scaffold(topBar = { CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.app_name), color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold) },
        navigationIcon =  {
            if (selectedPage.value != 0) {
                IconButton(onClick = {
                    selectedPage.value = 0
                }) { Icon(painter = painterResource(id = R.drawable.back_icon), contentDescription = "Close", tint = Color.White)}
            }
        }, colors = TopAppBarColors(containerColor = PrimaryColor, titleContentColor = SecondaryColor, actionIconContentColor = Color.White, scrolledContainerColor = Color.Transparent, navigationIconContentColor = Color.Transparent),
            actions = { IconButton(onClick = {
                selectedPage.value = 3
            }, content = {Icon(painter = painterResource(id = R.drawable.cart_icon), contentDescription = "Cart Icon")})  }) },
        bottomBar = { BottomAppBar(modifier = Modifier.height(65.dp), containerColor = PrimaryColor, contentColor = Color.White, content = {
            NavigationBarItem(
                selected = selectedPage.value == 0,
                onClick = { selectedPage.value = 0 },
                icon = { Icon(modifier = Modifier.size(33.dp), painter = painterResource(id = R.drawable.home_icon), contentDescription = "Home Icon") },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = DarkPrimaryColor, unselectedIconColor = Color.White))
            NavigationBarItem(
                selected = selectedPage.value == 1,
                onClick = { selectedPage.value = 1 },
                icon = { Icon(modifier = Modifier.size(33.dp), painter = painterResource(id = R.drawable.search_icon), contentDescription = "Search Icon") },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = DarkPrimaryColor, unselectedIconColor = Color.White))
            NavigationBarItem(
                selected = selectedPage.value == 2,
                onClick = { selectedPage.value = 2 },
                icon = { Icon(modifier = Modifier.size(33.dp), painter = painterResource(id = R.drawable.account_icon), contentDescription = "Account Icon") },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = DarkPrimaryColor, unselectedIconColor = Color.White))
        })}
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {

            when(selectedPage.value){
                0 -> Navigater("HomeScreen", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel, searchScreenViewModel)
                1 -> Navigater("SearchScreen", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel, searchScreenViewModel)
                2 -> Navigater("AccountPage", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel, searchScreenViewModel)
                3 -> Navigater("CartScreen", homeScreenViewModel, foodDetailViewModel, cartScreenViewModel, searchScreenViewModel)
            }
        }
    }
}