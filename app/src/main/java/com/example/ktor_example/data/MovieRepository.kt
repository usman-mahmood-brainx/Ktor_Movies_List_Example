package com.example.ktor_example.data

import com.example.ktor_example.data.network.CustomResponse
import com.example.ktor_example.data.models.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): CustomResponse<List<Movie>>?
}