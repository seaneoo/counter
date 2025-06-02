package com.example.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun increment(amount: Int) {
        val current = _counter.value ?: 0
        _counter.value = current + amount
    }

    fun decrement(amount: Int) {
        val current = _counter.value ?: 0
        _counter.value = (current - amount).coerceAtLeast(0)
    }
}
