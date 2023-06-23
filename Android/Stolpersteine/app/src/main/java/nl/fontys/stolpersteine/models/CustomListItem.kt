import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import nl.fontys.stolpersteine.R
import nl.fontys.stolpersteine.models.Stolperstein

class CustomListItem(context: Context, dataArrayList: List<Stolperstein>?) :
    ArrayAdapter<Stolperstein?>(context, R.layout.custom_list_item, dataArrayList!!) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val listData = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_list_item, parent, false)
        }
        val name = view!!.findViewById<TextView>(R.id.txtSteinName)
        val address = view.findViewById<TextView>(R.id.txtSteinAddress)
        if (listData != null) {

            name.text = listData.name
            address.text = listData.address
        }
        return view
    }
}