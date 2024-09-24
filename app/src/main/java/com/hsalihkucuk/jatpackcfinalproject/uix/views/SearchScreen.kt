package com.hsalihkucuk.jatpackcfinalproject.uix.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hsalihkucuk.jatpackcfinalproject.R
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.PrimaryColor
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.SearchScreenViewModel

@Composable
fun SearchScreen(navController: NavController, searchScreenViewModel: SearchScreenViewModel){
    val tfSearch = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val foodList = searchScreenViewModel.foodList.observeAsState(listOf())
    
    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
        searchScreenViewModel.searchFoods(tfSearch.value)
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(modifier = Modifier
            .clip(shape = RectangleShape)
            .fillMaxWidth()
            .focusRequester(focusRequester)
            , value = tfSearch.value, onValueChange = {
            tfSearch.value = it

            searchScreenViewModel.searchFoods(tfSearch.value)
        }, singleLine = true, placeholder = { Text(text = "Ara", color = Color.White)},
            trailingIcon = {
                if (!tfSearch.value.equals("")){
                    Icon(modifier = Modifier.size(25.dp).clickable {
                        tfSearch.value = ""
                        searchScreenViewModel.searchFoods(tfSearch.value)
                    }, painter = painterResource(id = R.drawable.clear_icon), contentDescription = "Clear Icon", tint = Color.White)
                }
                else Icon(modifier = Modifier.size(25.dp), painter = painterResource(id = R.drawable.search_icon), contentDescription = "Search Icon", tint = Color.White)
                           },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = PrimaryColor,
                unfocusedContainerColor = PrimaryColor
        ))

        LazyVerticalGrid(columns = GridCells.Fixed(count = 2), modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)) {
            items(count = foodList.value.count(), itemContent = {
                val food = foodList.value[it]
                FoodItemCard(navController, food)
            })
        }
    }


}