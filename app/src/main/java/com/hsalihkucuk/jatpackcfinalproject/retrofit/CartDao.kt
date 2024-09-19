package com.hsalihkucuk.jatpackcfinalproject.retrofit

import com.hsalihkucuk.jatpackcfinalproject.data.entity.CartAnswer
import com.hsalihkucuk.jatpackcfinalproject.data.entity.CrudAnswer
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CartDao {
    //base url -> http://kasimadalan.pe.hu/
    //yemekler/

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun getCart(@Field("kullanici_adi") kullaniciAdi : String) : CartAnswer

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(@Field("yemek_adi") yemekAdi : String,
                        @Field("yemek_resim_adi") yemekResimAdi : String,
                        @Field("yemek_fiyat") yemekFiyat : Int,
                        @Field("yemek_siparis_adet") yemekSiparisAdet : Int,
                        @Field("kullanici_adi") kullaniciAdi : String) : CrudAnswer

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun deleteFromCart(@Field("sepet_yemek_id") sepetYemekId : Int, @Field("kullanici_adi") kullaniciAdi : String) : CrudAnswer
}