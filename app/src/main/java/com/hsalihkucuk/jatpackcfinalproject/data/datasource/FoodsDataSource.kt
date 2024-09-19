package com.hsalihkucuk.jatpackcfinalproject.data.datasource

import com.hsalihkucuk.jatpackcfinalproject.data.entity.Foods
import com.hsalihkucuk.jatpackcfinalproject.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodsDataSource(var foodsDao: FoodsDao) {
    suspend fun getAllFoods() : List<Foods> = withContext(Dispatchers.IO){
        return@withContext foodsDao.getAllFoods().yemekler
    }
}