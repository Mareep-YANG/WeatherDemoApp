package cn.mareep.weatherdemoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.mareep.weatherdemoapp.data.local.preferences.CityPreference
import cn.mareep.weatherdemoapp.data.remote.client.WeatherApiClient

class MainViewModelFactory(
    private val apiClient: WeatherApiClient,
    private val cityPreference: CityPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(apiClient, cityPreference) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
