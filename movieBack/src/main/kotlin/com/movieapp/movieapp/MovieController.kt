package com.yourpackage

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.http.ResponseEntity

@RestController
class MovieController {

    @GetMapping("/movies")
    fun getMovies(): ResponseEntity<String> {
        val restTemplate = RestTemplate()
        val apiKey = "VOTRE_CLÉ_API"
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey"

        val response = restTemplate.getForEntity(url, String::class.java)
        return ResponseEntity.ok(response.body)
    }
}
