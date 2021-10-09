package com.curso.atc.lesson7recyclerexample.dto

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("status") var status : String,
    @SerializedName("message") var images : List<String>
)
