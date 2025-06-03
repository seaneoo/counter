package com.example.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private var _amount = MutableLiveData(1)
    val amount: LiveData<Int>
        get() = _amount

    private var _counter = MutableLiveData(0)
    val counter: LiveData<Int>
        get() = _counter

    fun setAmount(str: String) {
        val num = str.toIntOrNull()
        if (num != null) _amount.value = num
    }

    fun increment() {
        val current = _counter.value ?: 0
        _counter.value = current + (_amount.value ?: 1)
    }

    fun decrement() {
        val current = _counter.value ?: 0
        _counter.value = (current - (_amount.value ?: 1)).coerceAtLeast(0)
    }

    fun reset() {
        _counter.value = 0
    }
}
