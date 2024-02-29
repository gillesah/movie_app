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

    fun getAllPopularMovies(genreId: Int? = null): List<Map<String, Any>>? {
        val apiKey = appConfig.getApiKey()
        val results = mutableListOf<Map<String, Any>>()

        for (page in 1..100) {
            val url = "$baseUrl?api_key=$apiKey&page=$page"
            val response = restTemplate.getForObject(url, Map::class.java)
            val pageResults = response?.get("results") as List<Map<String, Any>>?
            pageResults?.let { results.addAll(it) }
        }

        return results?.filter { movie ->
            genreId == null || (movie["genre_ids"] as List<Int>).contains(genreId)
        }?.sortedByDescending { movie ->
            (movie["vote_average"] as Number?)?.toDouble() ?: 0.0
        }
    }

    //    fun getAllPopularMovies(genreId: Int? = null): List<Map<String, Any>>? {
//        val apiKey = appConfig.getApiKey()
//        val url = "$baseUrl?api_key=$apiKey"
//        val response = restTemplate.getForObject(url, Map::class.java)
//        val results = response?.get("results") as List<Map<String, Any>>?
//
//        // Filtrer par genre si un genreId est spécifié, puis trier tous les films par vote_average en ordre décroissant
//        return results?.filter { movie ->
//            genreId == null || (movie["genre_ids"] as List<Int>).contains(genreId)
//        }?.sortedByDescending { movie ->
//            (movie["vote_average"] as Number?)?.toDouble() ?: 0.0
//        }
//    }
    fun getGenres(): List<Map<String, Any>>? {
        val apiKey = appConfig.getApiKey()
        val url = "https://api.themoviedb.org/3/genre/movie/list?api_key=$apiKey"
        val response = restTemplate.getForObject(url, Map::class.java)
        return response?.get("genres") as List<Map<String, Any>>?
    }
}
