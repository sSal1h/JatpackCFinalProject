package com.hsalihkucuk.jatpackcfinalproject.data.datasource

import android.util.Log
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Cart
import com.hsalihkucuk.jatpackcfinalproject.retrofit.CartDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartDataSource(var cartDao: CartDao) {
    suspend fun getCart(kullaniciAdi : String) : List<Cart> = withContext(Dispatchers.IO){
        return@withContext cartDao.getCart(kullaniciAdi).sepet_yemekler
    }

    suspend fun addToCart(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String){
        cartDao.addToCart(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
    }

    suspend fun deleteFromCart(sepetYemekId : Int, kullaniciAdi : String){
        cartDao.deleteFromCart(sepetYemekId, kullaniciAdi)
    }
}