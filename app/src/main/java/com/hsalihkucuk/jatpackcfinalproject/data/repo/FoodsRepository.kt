package com.hsalihkucuk.jatpackcfinalproject.data.repo

import com.hsalihkucuk.jatpackcfinalproject.data.datasource.FoodsDataSource
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Foods

class FoodsRepository(var foodsDataSource: FoodsDataSource) {
    suspend fun getAllFoods() : List<Foods> = foodsDataSource.getAllFoods()
}