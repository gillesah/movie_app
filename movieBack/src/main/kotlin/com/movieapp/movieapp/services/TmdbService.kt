package com.movieapp.movieapp.services

import com.movieapp.movieapp.AppConfig
import com.movieapp.movieapp.models.Movie
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class TmdbService(private val appConfig: AppConfig) {
    private val restTemplate = RestTemplate()
    private val baseUrl = "https://api.themoviedb.org/3/movie/popular"
    private val imageUrl = "https://image.tmdb.org/t/p/w500"

    fun getRandomPopularMovie(): Movie? {
        val apiKey = appConfig.getApiKey()
        val url = "$baseUrl?api_key=$apiKey"
        val response = restTemplate.getForObject(url, Map::class.java)
        val results = response?.get("results") as List<Map<String, Any>>?
        if (!results.isNullOrEmpty()) {
            val randomMovie = results[Random().nextInt(results.size)]
            return Movie(
                title = randomMovie["title"] as String,
                overview = randomMovie["overview"] as String,
                posterPath = "$imageUrl${randomMovie["poster_path"] as String}"
            )
        }
        return null
    }
}
