package com.curso.atc.lesson7recyclerexample.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.curso.atc.lesson7recyclerexample.R
import com.curso.atc.lesson7recyclerexample.dto.UserDto

class DogsAdapter(private val usuario: ArrayList<UserDto>, val context: Context) : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    class DogsViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        fun bind(image: UserDto){
            // vincular la imagen al imageView
            //val ivDog = view.findViewById<ImageView>(R.id.ivDog)

            var id:TextView = view.findViewById<TextView>(R.id.id)
            var name:TextView= view.findViewById<TextView>(R.id.name)
            var email:TextView= view.findViewById<TextView>(R.id.email)
            var gener:TextView= view.findViewById<TextView>(R.id.genero)
            var status:TextView= view.findViewById<TextView>(R.id.status)

             //ivDog.fromUrl(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cardView = layoutInflater.inflate(R.layout.dog_cardview, parent, false)
        return DogsViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val currentDog = usuario[position]
        holder.bind(currentDog)
    }

    override fun getItemCount(): Int {
        return usuario.size
    }

}