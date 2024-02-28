package com.movieapp.movieapp
import com.movieapp.movieapp.AppConfig

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate



@RestController
class MovieController (private val appConfig: AppConfig){
    private val restTemplate = RestTemplate()

    @GetMapping("/movies")
    fun getMovies(): String {
        val apiKey = appConfig.getApiKey()

        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey"
        val response = restTemplate.getForObject(url, String::class.java)
        return response ?: "Error fetching movies"
    }

}