package com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Cart
import com.hsalihkucuk.jatpackcfinalproject.data.repo.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(var cartRepository: CartRepository) : ViewModel() {
    var cartList = MutableLiveData<List<Cart>>()
    var cartState = MutableLiveData(true)

    init {
        getCart("hSalih")
    }

    fun getCart(kullaniciAdi : String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                cartList.value = cartRepository.getCart(kullaniciAdi)
                cartState.value = true
            }
            catch (e : Exception){
                Log.e("CartScreenVM", e.localizedMessage!!)
                cartState.value = false
            }
        }
    }

    fun deleteFromCart(sepetYemekId : Int, kullaniciAdi : String){
        CoroutineScope(Dispatchers.Main).launch {
            cartRepository.deleteFromCart(sepetYemekId, kullaniciAdi)
            getCart("hSalih")
        }
    }

    fun addToCart(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cartList = cartRepository.getCart(kullaniciAdi)
                for (cart in cartList){
                    if (cart.yemek_adi == yemekAdi){
                        cartRepository.addToCart(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
                        cartRepository.deleteFromCart(cart.sepet_yemek_id, kullaniciAdi)
                        getCart("hSalih")
                    }
                }
            }
            catch (e : Exception){
                Log.e("FoodDetailVM", e.localizedMessage!!)
            }
        }
    }
}