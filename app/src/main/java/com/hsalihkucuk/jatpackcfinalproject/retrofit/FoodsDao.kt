package com.hsalihkucuk.jatpackcfinalproject.retrofit

import com.hsalihkucuk.jatpackcfinalproject.data.entity.FoodsAnswer
import retrofit2.http.GET

interface FoodsDao {
    //base url -> http://kasimadalan.pe.hu/
    //yemekler/

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods() : FoodsAnswer
}