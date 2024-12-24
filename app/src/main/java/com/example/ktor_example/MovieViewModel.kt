package com.example.ktor_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor_example.data.MovieRepositoryImpl
import com.example.ktor_example.data.network.CustomResponse
import com.example.ktor_example.data.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl

) : ViewModel(){

    private val _movies = MutableStateFlow<com.example.ktor_example.data.network.CustomResponse<List<Movie>>?>(null)
    val movies:StateFlow<CustomResponse<List<Movie>>?>  = _movies

    init {
        viewModelScope.launch {
            _movies.value = CustomResponse.Loading
            _movies.value = repository.getPopularMovies()

        }
    }
}