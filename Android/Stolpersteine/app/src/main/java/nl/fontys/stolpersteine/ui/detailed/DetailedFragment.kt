package nl.fontys.stolpersteine.ui.detailed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import nl.fontys.stolpersteine.R
import nl.fontys.stolpersteine.databinding.FragmentDetailedBinding
import nl.fontys.stolpersteine.models.Stolperstein
import nl.fontys.stolpersteine.models.StolpersteineManager
import nl.fontys.stolpersteine.ui.info.InfoViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.Thread.sleep

class DetailedFragment : Fragment() {
    private var _binding: FragmentDetailedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var steinId: String = "-1" // Hardcoded for now
    private var stolperstein: Stolperstein? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            steinId = it.getString("steinId").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val infoViewModel =
            ViewModelProvider(this).get(InfoViewModel::class.java)

        _binding = FragmentDetailedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navigationBack = binding.txtNavigationBack
        navigationBack.setOnClickListener {
            Navigation.findNavController(root).navigate(R.id.action_navigation_detailed_to_navigation_map)
        }

        val manager = StolpersteineManager()
        val valUrl = manager.urlString.replace("half", "id="+steinId)
        val request: Request = Request.Builder()
            .url(valUrl)
            .build()

        manager.client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("API_ERROR", e.toString())
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body
                if (responseString != null) {
                    Log.d("API_RESPONSE", responseString.toString())
                    val result = Gson().fromJson(responseString.string(), Stolperstein::class.java)
                    if (result != null) {
                        stolperstein = result
                    }
                }
            }
        })

        binding.layoutDetails.visibility = View.VISIBLE
        binding.txtLoading.visibility = View.INVISIBLE

        sleep(1000)

        Picasso.get().load(stolperstein?.photo).into(binding.ivPerson)
        binding.txtNamePerson.text = stolperstein?.name
        binding.rGender.text = stolperstein?.gender
        binding.rAddress.text = stolperstein?.address
        binding.rCity.text = stolperstein?.city
        binding.rDOB.text = stolperstein?.dateOfBirth
        binding.rDOD.text = stolperstein?.dateOfPassing
        binding.rPOD.text = stolperstein?.placeOfPassing
        binding.rROD.text = stolperstein?.reasonOfPassing

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}