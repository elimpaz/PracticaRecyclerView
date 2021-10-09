package com.curso.atc.lesson7recyclerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curso.atc.lesson7recyclerexample.data.DogsAdapter
import com.curso.atc.lesson7recyclerexample.data.PlaceListAdapter
import com.curso.atc.lesson7recyclerexample.dto.UserDto
import com.curso.atc.lesson7recyclerexample.dto.UserList
import com.curso.atc.lesson7recyclerexample.model.Place
import com.curso.atc.lesson7recyclerexample.network.ApiAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), PlaceListAdapter.RecyclerItemListener, CoroutineScope by MainScope() {

    //private var countryList : ArrayList<Place>? = null
    private lateinit var userList : ArrayList<UserDto>
    private var mylayoutManager : RecyclerView.LayoutManager? = null
    //private var myAdapter : PlaceListAdapter? = null
    private var userAdapter : DogsAdapter? = null

    private lateinit var myRecyclerView : RecyclerView
    private lateinit var etUser : EditText
    private lateinit var btSearch : Button
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //countryList = ArrayList()
        userList = ArrayList()

        myRecyclerView = findViewById(R.id.myRecyclerView)
        etUser = findViewById(R.id.etUser)
        btSearch = findViewById(R.id.btnSearch)
        progressBar = findViewById(R.id.progressBar)

        btSearch.setOnClickListener {
            searchDogs()
        }



        //myAdapter = PlaceListAdapter(countryList!!, this)

        //myRecyclerView.adapter = myAdapter
        //loadData()
        //myAdapter!!.notifyDataSetChanged()
        //myAdapter?.addListener(this)

    }

    fun searchDogs(){
        if (etUser.text.toString() == ""){
            Toast.makeText(this, "Debe digitar una raza", Toast.LENGTH_LONG).show()
            return
        }
        progressBar.visibility = View.VISIBLE

        launch {
            try {
                delay(2000)
                val apiResponse = ApiAdapter.apiClient.getListOfUser()

                if (apiResponse.isSuccessful && apiResponse.body() != null) {
                    val users = apiResponse.body() as UserList

                   initRecycler(users)

                    Log.v("APIDATA", "Data: $users")
                    progressBar.visibility = View.GONE
                } else {
                    Log.v("APIDATA", "No se encontro esa raza")
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception){
                Log.v("APIDATA", "Exception: ${e.localizedMessage}")
                progressBar.visibility = View.GONE
            }
        }

        Toast.makeText(this, "Antes de coroutina", Toast.LENGTH_LONG).show()
    }

    fun initRecycler(uList : UserList){

        for (user in uList.data)
        {
            if (user.status == "active")
            {
                userList.add(user)
            }

        }




        mylayoutManager = LinearLayoutManager(this)
        myRecyclerView.layoutManager = mylayoutManager

        userAdapter = DogsAdapter(userList, this)
        myRecyclerView.adapter = userAdapter

        //dogAdapter?.notifyDataSetChanged()

    }


    // invocación a un ws/ datos traidos desde una bd local
    /*fun loadData(){
        var countryNameList: Array<String> = arrayOf(
            "Canada",
            "USA",
            "Mexico",
            "Colombia",
            "Malaysia",
            "Singapur",
            "Turkia",
            "Guatemala",
            "Honduras",
            "El Salvador",
            "Nicaragua",
            "Costa Rica",
            "Wakanda",
        )

        var cityNameList: Array<String> = arrayOf(
            "Ottawa",
            "Washintong",
            "Cuidad de Mexico",
            "Bogota",
            "Kuala Lampur",
            "Singapur",
            "Ankara",
            "Ciudad de Guatemala",
            "Tegucigalpa",
            "San Salvador",
            "Managua",
            "San José",
            "Birnin Zana",
        )

        for (i in 0 until countryNameList.size){
            val place = Place(countryNameList[i], cityNameList[i])

            countryList?.add(place)

        }
    }*/

    override fun onItemClick(place: Place) {
        Toast.makeText(this, "Lanza aqui una activity\n$place", Toast.LENGTH_LONG).show()

    }
}