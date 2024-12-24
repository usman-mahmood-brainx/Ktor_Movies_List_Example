package com.example.ktor_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import com.example.ktor_example.data.network.CustomResponse
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.tmdbmovies.ui.movies.MovieList
import net.simplifiedcoding.tmdbmovies.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val movies = viewModel.movies.collectAsState()
            AppTheme {
                movies.value?.let {
                    when(it) {
                        is com.example.ktor_example.data.network.CustomResponse.Loading -> {
                            Text(text = "Loading")
                        }

                        is CustomResponse.Success -> {
                            MovieList(movies = it.data)

                        }

                        is CustomResponse.Failure -> {
                            Text(text = "Error")
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}