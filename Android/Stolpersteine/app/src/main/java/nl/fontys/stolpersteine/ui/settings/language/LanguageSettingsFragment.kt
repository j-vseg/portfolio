package nl.fontys.stolpersteine.ui.settings.language

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import nl.fontys.stolpersteine.databinding.FragmentLanguageSettingsBinding

class LanguageSettingsFragment : Fragment() {

    private lateinit var viewModel: LanguageSettingsViewModel
    private var _binding: FragmentLanguageSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLanguageSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this)[LanguageSettingsViewModel::class.java]

        // Get an instance of SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Get an instance of SharedPreferences.Editor to modify the preferences
        val editor = sharedPreferences.edit()

        // Save a value
        editor.putBoolean("lngChanged", false)
        editor.apply()

        // Observe the language code from the ViewModel
        viewModel.languageCode.observe(viewLifecycleOwner) {
            // Update the UI based on the language code

            // Restart the activity to reflect the new language
            activity?.let { currentActivity ->
                val intent = currentActivity.intent
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                currentActivity.finish()
                currentActivity.startActivity(intent)
                currentActivity.overridePendingTransition(0, 0)
            }
        }

        // Set click listeners for the buttons
        binding.btnEn.setOnClickListener {
            viewModel.changeLanguage("en", resources, this.requireContext(), true)
        }

        binding.btnNl.setOnClickListener {
            viewModel.changeLanguage("nl", resources, this.requireContext(), true)
        }

        binding.btnFr.setOnClickListener {
            viewModel.changeLanguage("fr", resources, this.requireContext(), true)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
