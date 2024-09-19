package com.hsalihkucuk.jatpackcfinalproject.data.repo

import com.hsalihkucuk.jatpackcfinalproject.data.datasource.CartDataSource
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Cart

class CartRepository(var cartDataSource: CartDataSource) {
    suspend fun getCart(kullaniciAdi : String) : List<Cart> = cartDataSource.getCart(kullaniciAdi)

    suspend fun addToCart(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String) = cartDataSource.addToCart(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)

    suspend fun deleteFromCart(sepetYemekId : Int, kullaniciAdi : String) = cartDataSource.deleteFromCart(sepetYemekId, kullaniciAdi)
}