package com.example.ktor_example.data

import com.example.ktor_example.data.network.CustomResponse
import com.example.ktor_example.data.network.BaseUrl.POPULAR_MOVIES
import com.example.ktor_example.data.models.Movie
import com.example.ktor_example.data.models.PopularMovies
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : MovieRepository {

    override suspend fun getPopularMovies(): CustomResponse<List<Movie>> {
        return try {
            val response: List<Movie> = httpClient.get<PopularMovies>{
                url(POPULAR_MOVIES)
            }.results
            CustomResponse.Success(response)
        } catch (e: Exception) {
            CustomResponse.Failure(e)
        }
    }
}