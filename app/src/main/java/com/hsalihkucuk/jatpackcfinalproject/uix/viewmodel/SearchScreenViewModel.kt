package com.hsalihkucuk.jatpackcfinalproject.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hsalihkucuk.jatpackcfinalproject.data.entity.Foods
import com.hsalihkucuk.jatpackcfinalproject.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()

    fun searchFoods(searchText : String){
        CoroutineScope(Dispatchers.Main).launch {
            val foods = foodsRepository.getAllFoods()
            if (searchText.equals("")){
                foodList.value = foods
            }
            else {
                val findFood = ArrayList<Foods>()
                for (food in foods){
                    if (food.yemek_adi.contains(searchText, ignoreCase = true)){
                        findFood.add(food)
                    }
                }
                foodList.value = findFood
            }

        }
    }
}