package com.curso.atc.lesson7recyclerexample.network

import com.curso.atc.lesson7recyclerexample.dto.DogResponse
import com.curso.atc.lesson7recyclerexample.dto.UserDto
import com.curso.atc.lesson7recyclerexample.dto.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    // base url:  https://dog.ceo

    // http://www.omdbapi.com/?apikey=[yourkey]&
    @GET("/public/v1/users")
    suspend fun getListOfUser() : Response<UserList>
}