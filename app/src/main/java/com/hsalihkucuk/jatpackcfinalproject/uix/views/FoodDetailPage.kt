package com.hsalihkucuk.jatpackcfinalproject.uix.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hsalihkucuk.jatpackcfinalproject.R
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Foods
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.Color5
import com.hsalihkucuk.jatpackcfinalproject.ui.theme.PrimaryColor
import com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel.FoodDetailViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FoodDetailPage(navController: NavController, food: Foods, foodDetailViewModel: FoodDetailViewModel){
    val orderCount = remember { mutableStateOf(1) }

    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Column(modifier = Modifier
            .clip(shape = RoundedCornerShape(25.dp))
            .background(color = Color5)
            .padding(50.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemek_resim_adi}"
            GlideImage(imageModel = url, modifier = Modifier.size(200.dp, 200.dp))

            Text(text = food.yemek_adi, style = MaterialTheme.typography.titleLarge)
            Text(text = "${ food.yemek_fiyat }₺", style = MaterialTheme.typography.titleMedium)

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    if (orderCount.value > 1) {
                        orderCount.value--
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
                    Icon(painter = painterResource(id = R.drawable.neg_icon), contentDescription = "-")
                }
                Text(
                    text = orderCount.value.toString(),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .width(IntrinsicSize.Max)
                )
                Button(onClick = {
                    if (orderCount.value < 10) {
                        orderCount.value++
                    }
                }, colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
                    Icon(painter = painterResource(id = R.drawable.plus_icon), contentDescription = "+")
                }
            }

            Button(onClick = {
                foodDetailViewModel.addToCart(food.yemek_adi, food.yemek_resim_adi, food.yemek_fiyat, orderCount.value, "hSalih")

                navController.navigate("CartScreen")
            }, Modifier.size(150.dp, 40.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
                Text(text = "Sepete Ekle", fontSize = 18.sp)
            }
        }
    }
}