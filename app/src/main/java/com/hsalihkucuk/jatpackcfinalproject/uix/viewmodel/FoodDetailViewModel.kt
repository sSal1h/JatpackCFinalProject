package com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Cart
import com.hsalihkucuk.jatpackcfinalproject.data.repo.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(var cartRepository: CartRepository) : ViewModel() {
    fun addToCart(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cartList = cartRepository.getCart(kullaniciAdi)
                for (cart in cartList){
                    if (cart.yemek_adi == yemekAdi){
                        cartRepository.addToCart(yemekAdi, yemekResimAdi, yemekFiyat, (yemekSiparisAdet + cart.yemek_siparis_adet), kullaniciAdi)
                        cartRepository.deleteFromCart(cart.sepet_yemek_id, kullaniciAdi)
                    }
                    else cartRepository.addToCart(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
                }
            }
            catch (e : Exception){
                Log.e("FoodDetailVM", e.localizedMessage!!)

                cartRepository.addToCart(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
            }
        }
    }
}