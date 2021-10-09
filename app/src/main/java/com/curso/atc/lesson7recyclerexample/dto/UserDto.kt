package com.curso.atc.lesson7recyclerexample.dto

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class UserDto(

    var id : Int,
    var name: String,
    var email: String,
    @SerializedName("gender") var genero : String,
    var status: String
    )

data class UserList(
    var data:List<UserDto>
)