package com.hsalihkucuk.jatpackcfinalproject.di

import com.hsalihkucuk.jatpackcfinalproject.data.datasource.CartDataSource
import com.hsalihkucuk.jatpackcfinalproject.data.datasource.FoodsDataSource
import com.hsalihkucuk.jatpackcfinalproject.data.repo.CartRepository
import com.hsalihkucuk.jatpackcfinalproject.data.repo.FoodsRepository
import com.hsalihkucuk.jatpackcfinalproject.retrofit.ApiUtils
import com.hsalihkucuk.jatpackcfinalproject.retrofit.CartDao
import com.hsalihkucuk.jatpackcfinalproject.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodsRepository(foodsDataSource: FoodsDataSource) : FoodsRepository{
        return FoodsRepository(foodsDataSource)
    }
    @Provides
    @Singleton
    fun provideFoodsDataSource(foodsDao: FoodsDao) : FoodsDataSource{
        return FoodsDataSource(foodsDao)
    }
    @Provides
    @Singleton
    fun provideFoodsDao() : FoodsDao{
        return ApiUtils.getFoodsDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDataSource: CartDataSource) : CartRepository {
        return CartRepository(cartDataSource)
    }
    @Provides
    @Singleton
    fun provideCartDataSource(cartDao: CartDao) : CartDataSource {
        return CartDataSource(cartDao)
    }
    @Provides
    @Singleton
    fun provideCartDao() : CartDao {
        return ApiUtils.getCartDao()
    }
}