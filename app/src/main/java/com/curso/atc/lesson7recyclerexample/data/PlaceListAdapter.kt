package com.curso.atc.lesson7recyclerexample.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.curso.atc.lesson7recyclerexample.R
import com.curso.atc.lesson7recyclerexample.model.Place

class PlaceListAdapter(
        private val listCountries: ArrayList<Place>,
        private val context: Context
    ) : RecyclerView.Adapter<PlaceListAdapter.MyViewHolder>() {
    private var listener : RecyclerItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myCardView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(myCardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem( listCountries[position] )
    }

    override fun getItemCount(): Int {
        return listCountries.size
    }

    fun addListener(pListener : RecyclerItemListener){
        listener = pListener
    }

    // un viewHolder
    inner class MyViewHolder(private val cardView: View) : RecyclerView.ViewHolder(cardView) {

        fun bindItem(place: Place) {
            val tvCountryName : TextView = cardView.findViewById(R.id.countryName)
            val tvCountryCity : TextView = cardView.findViewById(R.id.countryCity)

            tvCountryName.text = place.countryName
            tvCountryCity.text = place.cityName

            cardView.setOnClickListener{
                //Toast.makeText(context, "Selecciono: ${place.countryName}", Toast.LENGTH_LONG).show()
                // startAc
                listener?.onItemClick(place)

            }
        }
    }

    interface RecyclerItemListener{
        fun onItemClick(place: Place)
    }
}