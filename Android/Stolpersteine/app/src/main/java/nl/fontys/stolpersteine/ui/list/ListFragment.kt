package nl.fontys.stolpersteine.ui.list

import CustomListItem
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.fontys.stolpersteine.R
import nl.fontys.stolpersteine.databinding.FragmentListBinding
import nl.fontys.stolpersteine.models.Stolperstein
import nl.fontys.stolpersteine.models.StolpersteineManager
import nl.fontys.stolpersteine.ui.settings.SettingsViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set up map when first launches
        val manager = StolpersteineManager()
        val request: Request = Request.Builder()
            .url(manager.urlString)
            .build()
        var stolpersteine: List<Stolperstein>? = null
        manager.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {
                Log.d("API_ERROR", e.toString())
            }

            @Throws(java.io.IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body
                if (responseString != null) {
                    Log.d("API_RESPONSE", responseString.toString())
                    val listType: TypeToken<List<Stolperstein>?> = object : TypeToken<List<Stolperstein>?>() {}
                    val list = Gson().fromJson(responseString.string(), listType)
                    if (list != null) {
                        stolpersteine = list
                    }
                }
            }
        })
        //Thread.sleep(10000)

        val searchBar = binding.txtSearchList
        searchBar.addTextChangedListener( object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var filteredCity: MutableList<Stolperstein>? = mutableListOf()
                var filteredStreet: MutableList<Stolperstein>? = mutableListOf()
                var filteredPeople: MutableList<Stolperstein>? = mutableListOf()

                if (s != null && count >= 3) {
                    for (item in stolpersteine!!) {
                        if (item.name!!.contains(s)) { filteredPeople?.add(item) }
                        if ( item.city!!.contains(s)) { filteredCity?.add(item) }
                        if (item.address!!.contains(s)) { filteredStreet?.add(item) }
                    }
                }
                val lvPeople = binding.lvPeople
                val lvStreet = binding.lvStreet
                val lvCity = binding.lvCity
                if (filteredCity!!.isNotEmpty()) {
                    binding.layoutCities.visibility = View.VISIBLE
                    lvCity.adapter = CustomListItem(requireContext(), filteredCity)
                }
                else { binding.layoutCities.visibility = View.INVISIBLE }
                if (filteredStreet!!.isNotEmpty()) {
                    binding.layoutStreets.visibility = View.VISIBLE
                    lvStreet.adapter = CustomListItem(requireContext(), filteredStreet)
                }
                else { binding.layoutStreets.visibility = View.INVISIBLE }
                if (filteredPeople!!.isNotEmpty()) {
                    binding.layoutPeople.visibility = View.VISIBLE
                    lvPeople.adapter = CustomListItem(requireContext(), filteredPeople)
                }
                else { binding.layoutPeople.visibility = View.INVISIBLE }
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}