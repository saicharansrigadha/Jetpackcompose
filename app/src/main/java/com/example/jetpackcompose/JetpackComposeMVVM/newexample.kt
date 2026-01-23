package com.example.customapiwithmvvm.simpleMVVM.JetpackComposeMVVM

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        println("Running coroutine!")
    }
    job.join() // wait for coroutine to finish
}
