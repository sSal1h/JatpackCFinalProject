package com.hsalihkucuk.jatpackcfinalproject.uix.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hsalihkucuk.jatpackcfinalproject.R
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Cart
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.Color5
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.Color6
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.PrimaryColor
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.CartScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CartScreen(navController: NavController, cartScreenViewModel: CartScreenViewModel){
    val cartList = cartScreenViewModel.cartList.observeAsState(listOf())
    val cartState = cartScreenViewModel.cartState.observeAsState(true)
    val cartValue = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        cartScreenViewModel.getCart("hSalih")
    }

    Scaffold(bottomBar = { BottomAppBar(containerColor = Color6, contentColor = Color.White) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 0.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Toplam tutar :", style = MaterialTheme.typography.titleLarge, color = Color.Black)
                Text(text = "${cartValue.value} ₺", style = MaterialTheme.typography.titleLarge, color = Color.Black)
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {

            }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
                Text(text = "Sepeti Onayla", style = MaterialTheme.typography.titleMedium)
            }
        }

    }}) {paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
            if (cartState.value){
                LazyColumn {
                    items(count = cartList.value.count(), itemContent = {
                        val cartItem = cartList.value[it]
                        cartValue.value = cartList.value.sumOf { it.yemek_fiyat * it.yemek_siparis_adet }
                        CartItemRow(cartScreenViewModel, cartItem)
                    })
                }
            }
            else {
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    cartValue.value = 0
                    
                    Text(text = "Sepetiniz Boş!", style = MaterialTheme.typography.titleLarge)
                    Button(onClick = {
                        navController.navigate("HomeScreen")
                    }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
                        Text(text = "Alışverişe Başla")
                    }
                }
            }
        }
    }

}

@Composable
fun CartItemRow(cartScreenViewModel: CartScreenViewModel, cartItem: Cart) {
    val orderCount = remember { mutableStateOf(cartItem.yemek_siparis_adet) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(shape = RoundedCornerShape(15.dp))
        .background(color = Color5)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartItem.yemek_resim_adi}"
            GlideImage(imageModel = url, modifier = Modifier.size(80.dp, 80.dp))

            Column {
                Text(text = cartItem.yemek_adi, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "${cartItem.yemek_siparis_adet} Adet",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${cartItem.yemek_fiyat * cartItem.yemek_siparis_adet} ₺",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    if (orderCount.value < 10) {
                        orderCount.value++
                        cartScreenViewModel.addToCart(cartItem.yemek_adi, cartItem.yemek_resim_adi, cartItem.yemek_fiyat, orderCount.value, "hSalih")
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
                    Icon(painter = painterResource(id = R.drawable.plus_icon), contentDescription = "+")
                }
                Text(
                    text = orderCount.value.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(3.dp)
                        .width(IntrinsicSize.Max)
                )
                Button(onClick = {
                    if (orderCount.value > 1) {
                        orderCount.value--
                        cartScreenViewModel.addToCart(cartItem.yemek_adi, cartItem.yemek_resim_adi, cartItem.yemek_fiyat, orderCount.value, "hSalih")
                    }
                    else if(orderCount.value == 1){
                        cartScreenViewModel.deleteFromCart(cartItem.sepet_yemek_id, "hSalih")
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    if (orderCount.value > 1) {
                        Icon(painter = painterResource(id = R.drawable.neg_icon), contentDescription = "-")
                    }
                    else Icon(painter = painterResource(id = R.drawable.delete_icon), contentDescription = "Delete Icon")
            }
            }
        }
    }
}
