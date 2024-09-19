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
class HomeScreenViewModel @Inject constructor(var foodsRepository: FoodsRepository) : ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()

    init {
        getAllFoods()
    }

    fun getAllFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodsRepository.getAllFoods()
        }
    }
}