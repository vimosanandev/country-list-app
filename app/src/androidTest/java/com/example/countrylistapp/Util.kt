package com.example.countrylistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.getOrAwaitValue(): T {
    var value: T? = null
    val latch = CountDownLatch(1)

    val observer = Observer<T> {
        value = it
        latch.countDown()
    }

    try {
        runBlocking {
            withContext(Dispatchers.Main) {
                observeForever(observer)
            }
            latch.await(2, TimeUnit.SECONDS)
        }
    } finally {
        runBlocking {
            withContext(Dispatchers.Main) {
                removeObserver(observer)
            }
        }
    }

    return value ?: throw IllegalStateException("LiveData value was never set!")
}