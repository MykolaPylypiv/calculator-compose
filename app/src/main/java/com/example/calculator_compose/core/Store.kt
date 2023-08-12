package com.example.calculator_compose.core

import kotlinx.coroutines.flow.Flow

interface Store<T> {

    fun get(): Flow<T>

    suspend fun save(name: T)
}

