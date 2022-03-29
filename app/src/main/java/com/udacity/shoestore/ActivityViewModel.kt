package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ActivityViewModel : ViewModel() {

    private val _listOfShoes = MutableLiveData<List<Shoe>>()
    val listOfShoes : LiveData<List<Shoe>>
        get() = _listOfShoes

    init {
        Timber.i("ViewModel Initialized")
        _listOfShoes.value = listOf<Shoe>()
    }

    fun insertNew(shoe: Shoe){
        Timber.i("Received : ${shoe.toString()}")
        _listOfShoes.value = _listOfShoes.value?.toMutableList()?.apply {
            add(shoe)
        }?.toList()
        Timber.i("_listOfShoes.value = ${_listOfShoes.value.toString()}")
    }
}