package com.example.calculator_compose.core

import kotlinx.coroutines.flow.Flow

interface Store {

    fun get(): Flow<String?>

    suspend fun save(name: String)
}