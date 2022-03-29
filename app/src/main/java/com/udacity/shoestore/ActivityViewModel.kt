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

    private val _randomShoe = MutableLiveData<Shoe>()
    val randomShoe : LiveData<Shoe>
        get() = _randomShoe

    private var shoeList: MutableList<Shoe> = mutableListOf<Shoe>()

    init {
        Timber.i("ViewModel Initialized")
        _listOfShoes.value = listOf<Shoe>()
        shoeList.apply {
            add(Shoe("xRunner", 9.0, "Adidas", "Some Adidas Shoe"))
            add(Shoe("uBolt", 10.0, "Puma", "SomePuma Shoes"))
            add(Shoe("uFast", 10.0, "Jd", "Running shoes"))
            add(Shoe("uLight", 10.0, "Jd", "Light Running shoes"))
            add(Shoe("uRunner", 13.0, "Google", "Running shoes"))
            add(Shoe("xClassy", 12.0, "Tomaz", "Professional shoes"))
            add(Shoe("xClassy2.0", 10.0, "Tomaz", "Professional shoes 2.0"))
            add(Shoe("xClassy3.0", 8.0, "Tomaz", "Professional shoes 3.0"))
            add(Shoe("xBoots", 13.0, "Tomaz", "chelsea boots"))
        }
    }

    fun insertNew(shoe: Shoe){
        Timber.i("Received : ${shoe.toString()}")
        _listOfShoes.value = _listOfShoes.value?.toMutableList()?.apply {
            add(shoe)
        }?.toList()
        Timber.i("_listOfShoes.value = ${_listOfShoes.value.toString()}")
    }

    fun generateRandom(){
        _randomShoe.value = shoeList.shuffled().first()
    }
}