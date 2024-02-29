package com.movieapp.movieapp

import com.movieapp.movieapp.models.Movie
import com.movieapp.movieapp.services.TmdbService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class MovieController(private val appConfig: AppConfig, private val tmdbService: TmdbService) {
    private val restTemplate = RestTemplate()
    @CrossOrigin(origins = ["http://localhost:8081"])
    @GetMapping("/movies")
    fun getMovies(): String {
        val apiKey = appConfig.getApiKey()
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey"
        val response = restTemplate.getForObject(url, String::class.java)
        return response ?: "Error fetching movies"
    }

    @GetMapping("/popular")
    fun getPopularMovie(): Movie? {
        return tmdbService.getRandomPopularMovie()
    }
}
