package nl.fontys.stolpersteine.ui.map

import android.graphics.drawable.Drawable
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.fontys.stolpersteine.BuildConfig
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import nl.fontys.stolpersteine.R
import nl.fontys.stolpersteine.databinding.FragmentMapBinding
import nl.fontys.stolpersteine.models.CustomInfoWindow
import nl.fontys.stolpersteine.models.Stolperstein
import nl.fontys.stolpersteine.models.StolpersteineManager
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.osmdroid.bonuspack.clustering.RadiusMarkerClusterer
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.lang.Thread.sleep


class MapFragment : Fragment(){

    private var _binding: FragmentMapBinding? = null
    private lateinit var sharedPreferences: SharedPreferences

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var isNavigating = false
    private var languageChanged = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mapViewModel =
            ViewModelProvider(this).get(MapViewModel::class.java)

        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set Location Marker
        if (isLocationPermissionGranted()) {
            setLocationMarker()
        }

        setUpMap(binding.mapView)
        // Set up map when first launches
        val manager = StolpersteineManager()
        val request: Request = Request.Builder()
            .url(manager.urlString)
            .build()

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
                        if (!isNavigating) {
                            setUpMarkers(binding.mapView, list)
                        }
                    }
                }
            }
        })
        //sleep(10000)
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        return root
    }

    override fun onPause() {
        super.onPause()
        isNavigating = true
    }
    override fun onResume() {
        super.onResume()
        isNavigating = false

        languageChanged = sharedPreferences.getBoolean("lngChanged", false)

        if(languageChanged){
            findNavController().navigate(R.id.action_map_to_settings)
            findNavController().navigate(R.id.action_settings_to_language)
        }
    }

    private fun internalGetBinding(): FragmentMapBinding? {
        return _binding
    }

    private fun setLocationMarker() {
        val handler = Handler(Looper.getMainLooper())

        val runnable = Runnable {
            val binding = internalGetBinding() ?: return@Runnable  // Check if binding is null

            val myLocationOverlay = MyLocationNewOverlay(binding.mapView)
            myLocationOverlay.enableFollowLocation()
            myLocationOverlay.enableMyLocation()

            // Set a custom icon for the location overlay
            val customIconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.location_icon)
            val customIconBitmap = customIconDrawable?.toBitmap()

            // Resize the custom icon
            val resizedBitmap = customIconBitmap?.let {
                Bitmap.createScaledBitmap(it, 140, 190, false)
            }

            // Set the custom icon with the anchor point in the middle
            myLocationOverlay.setPersonIcon(resizedBitmap)
            myLocationOverlay.setDirectionIcon(resizedBitmap)

            binding.mapView.overlays.add(myLocationOverlay)
            binding.mapView.invalidate() // Refresh the map view
        }

        handler.post(runnable)
    }


    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setUpMap(mapView: MapView) {
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setVerticalMapRepetitionEnabled(false)
        val northLimit = 84.959306 // Replace with the maximum latitude limit
        val southLimit = -85.067025 // Replace with the minimum latitude limit
        mapView.setScrollableAreaLimitLatitude(northLimit, southLimit, 0)

        // Set initial map center to the Benelux region
        val beneluxCenter = GeoPoint(51.8, 5.0) // Center coordinates of Benelux
        mapView.controller.setCenter(beneluxCenter)
        mapView.controller.setZoom(7.5)

        mapView.minZoomLevel = 3.0
        @Suppress("DEPRECATION")
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)
    }
    fun setUpMarkers(mapView: MapView, stolpersteine: List<Stolperstein>?) {
        val poiMarkers = RadiusMarkerClusterer(mapView.context)
        mapView.overlays.add(poiMarkers)

        if (stolpersteine != null) {
            for (poi in stolpersteine) {
                val poiMarker = Marker(mapView)
                poiMarker.id = poi.id.toString()
                poiMarker.title = poi.address
                poiMarker.subDescription = poi.name
                poiMarker.setInfoWindow(CustomInfoWindow(R.layout.custom_info_window, mapView, requireContext()))
                poiMarker.position = GeoPoint(poi.location?.lat!!, poi.location!!.long!!)
                if (poi.photo != null) {
                    poiMarker.image = Drawable.createFromPath(poi.photo)
                }
                poiMarkers.add(poiMarker)
                /*poiMarker.setOnMarkerClickListener { marker, mapView ->
                    val detailedFragment = DetailedFragment.newInstance(poi.id)
                    val intent = Intent(context, DetailedFragment::class.java)
                    startActivity(intent)
                    true
                }*/
            }

            mapView.invalidate()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}