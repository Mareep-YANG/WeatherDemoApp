package cn.mareep.weatherdemoapp.data.local.preferences

import android.content.Context
import androidx.core.content.edit

class CityPreference(context: Context){
    private val prefs = context.getSharedPreferences("weather_prefs", Context.MODE_PRIVATE)

    var selectedCode: String
        get() = prefs.getString("selected_code","210200") ?: "210200"
        set(value) = prefs.edit { putString("selected_code", value) }
}