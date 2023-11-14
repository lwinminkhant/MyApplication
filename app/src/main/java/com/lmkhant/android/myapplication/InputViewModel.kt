package com.lmkhant.android.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {
    private val _dataset = MutableLiveData<List<Input>>()

    val dataset = _dataset

    init {
        _dataset.value = mutableListOf()
    }

    fun addInput(input: Input) {
        val list = _dataset.value?.toMutableList() ?: mutableListOf()
        list.add(input)
        _dataset.value = list
    }

    fun removeCheckedInputs() {
        val list = dataset.value?.toMutableList() ?: mutableListOf()
        list.removeIf { it.checked }
        _dataset.value = list
    }

}