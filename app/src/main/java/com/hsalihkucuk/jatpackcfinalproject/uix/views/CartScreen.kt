package com.hsalihkucuk.jatpackcfinalproject.uix.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Cart
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.CartScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CartScreen(navController: NavController, cartScreenViewModel: CartScreenViewModel){
    val cartList = cartScreenViewModel.cartList.observeAsState(listOf())
    val cartState = cartScreenViewModel.cartState.observeAsState(true)

    LaunchedEffect(key1 = true) {
        cartScreenViewModel.getCart("hSalih")
    }

    Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (cartState.value){
            LazyColumn {
                items(count = cartList.value.count(), itemContent = {
                    val cartItem = cartList.value[it]
                    CartItemRow(cartScreenViewModel, cartItem)
                })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Total: ${cartList.value.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }}₺", style = MaterialTheme.typography.titleSmall, color = Color.White)
        }
        else {
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Sepetiniz Boş!", style = MaterialTheme.typography.titleLarge)
                Button(onClick = {
                    navController.navigate("HomeScreen")
                }) {
                    Text(text = "Alışverişe Başla")
                }
            }
        }
    }
}

@Composable
fun CartItemRow(cartScreenViewModel: CartScreenViewModel, cartItem: Cart) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartItem.yemek_resim_adi}"
        GlideImage(imageModel = url, modifier = Modifier.size(80.dp, 80.dp))

        Column {
            Text(text = cartItem.yemek_adi, color = Color.White)
            Text(text = "x${cartItem.yemek_siparis_adet}", color = Color.White)
            Text(text = "${cartItem.yemek_fiyat * cartItem.yemek_siparis_adet}₺", color = Color.White)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            cartScreenViewModel.deleteFromCart(cartItem.sepet_yemek_id, "hSalih")
            cartScreenViewModel.getCart("hSalih")
        }) {
            Text(text = "Sil")
        }
    }
}
