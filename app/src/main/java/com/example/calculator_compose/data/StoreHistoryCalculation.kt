package com.example.calculator_compose.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.calculator_compose.core.Store
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreHistoryCalculation @Inject constructor(
    @ApplicationContext private val context: Context
) : Store {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("historyCalculation")
        val HISTORY_CALCULATION = stringPreferencesKey("history_calculation")
    }

    override fun get(): Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[HISTORY_CALCULATION] ?: ""
    }

    override suspend fun save(name: String) {
        context.dataStore.edit { preferences ->
            preferences[HISTORY_CALCULATION] = name
        }
    }

}