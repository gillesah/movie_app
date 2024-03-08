package com.movieapp.movieapp

import com.movieapp.movieapp.models.Movie
import com.movieapp.movieapp.services.TmdbService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
class MovieController(private val appConfig: AppConfig, private val tmdbService: TmdbService) {
    private val restTemplate = RestTemplate()
    @CrossOrigin(origins = ["http://localhost:85", "http://localhost:8080", "https://mycinehunt.com/", "https://moodcine.com/" ])
    @GetMapping("/movies")
    fun getMovies(): String {
        val apiKey = appConfig.getApiKey()
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey"
        val response = restTemplate.getForObject(url, String::class.java)
        return response ?: "Error fetching movies"
    }

    @GetMapping("/randompopular")
    fun getPopularMovie(): Movie? {
        return tmdbService.getRandomPopularMovie()
    }
//    @GetMapping("/popular")
//    fun getAllMovie(): List<Map<String, Any>>? {
//        return tmdbService.getAllPopularMovie()
//    }
    @GetMapping("/popular")
    fun getMovies(@RequestParam(required = false) genreId: Int?): ResponseEntity<List<Map<String, Any>>?> {
        val movies = tmdbService.getAllPopularMovies(genreId)
        return ResponseEntity.ok(movies)
    }
    //http://localhost:8080/popular?genreId=16
    @GetMapping("/vote")
    fun getMovieVote(@RequestParam(required = false) genreId: Any?): ResponseEntity<List<Map<String, Any>>?> {
        val movies = tmdbService.getVoteAverage(genreId)
        println(movies)
        return ResponseEntity.ok(movies)
    }
    @GetMapping("/genres")
    fun getGenres(): ResponseEntity<List<Map<String, Any>>?> {
        val genres = tmdbService.getGenres()
        return ResponseEntity.ok(genres)
    }


    @GetMapping("/{movieId}/trailers")
    fun getMovieTrailer(@PathVariable movieId: Int): ResponseEntity<String> {
        val trailer = tmdbService.getMovieTrailers(movieId)
        return if (trailer != null) {
            ResponseEntity.ok(trailer)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
