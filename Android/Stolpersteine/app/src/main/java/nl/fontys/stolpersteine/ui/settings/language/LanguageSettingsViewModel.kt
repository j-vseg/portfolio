package nl.fontys.stolpersteine.ui.settings.language

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.fontys.stolpersteine.MainActivity
import nl.fontys.stolpersteine.R
import java.util.*

class LanguageSettingsViewModel : ViewModel() {

    private val _languageCode = MutableLiveData<String>()
    val languageCode: LiveData<String> = _languageCode

    @Suppress("DEPRECATION")
    fun changeLanguage(languageCode: String, resources: Resources, context: Context, notificationOn: Boolean) {
        val mainActivity = MainActivity()
        val locale = Locale(languageCode)
        val currentLanguageCode = resources.configuration.locale.language
        if(currentLanguageCode == locale.language){
            if(notificationOn){
                mainActivity.showPopup(context, context.getString(R.string.popup_title_lang), context.getString(
                    R.string.popup_text_lang))
            }
            return
        }
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Get an instance of SharedPreferences
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Get an instance of SharedPreferences.Editor to modify the preferences
        val editor = sharedPreferences.edit()

        // Save a value
        editor.putString("lng", locale.language)
        editor.apply()

        // Save a value
        editor.putBoolean("lngChanged", notificationOn)
        editor.apply()

        _languageCode.value = languageCode
    }
    fun getSavedLanguageCode(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("lng", null)
    }
}