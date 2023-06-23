package nl.fontys.stolpersteine.models

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import nl.fontys.stolpersteine.R
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class CustomInfoWindow(
    layoutResId: Int,
    mapView: MapView,
    context: Context
    ) : InfoWindow(layoutResId, mapView) {

        private val txtAddress: TextView
        private val txtPerson: TextView
        private val button: Button

        init {
            val view = LayoutInflater.from(context).inflate(layoutResId, null)
            txtAddress = view.findViewById(R.id.info_address)
            txtPerson = view.findViewById(R.id.info_person)
            button = view.findViewById(R.id.info_button)
            mView = view
        }

        override fun onOpen(item: Any) {
            // Customize the info window content based on the marker item
            if (item is Marker) {
                txtPerson.text = item.title
                txtAddress.text = item.subDescription
                button.setOnClickListener {
                    val bundle = bundleOf("steinId" to item.id)
                    Navigation.findNavController(mView).navigate(R.id.action_navigation_map_to_navigation_detailed, bundle)
                    //val intent = Intent(view.context, DetailedFragment::class.java)
                    //startActivity(view.context, intent, Bundle().apply{ putString("steinId", item.id) })
                }
            }
        }

    override fun onClose() {}
}