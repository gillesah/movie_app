package com.movieapp.movieapp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PopularController {

    @GetMapping("/popular")
    fun getTest(): String {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=c4d577c0da3d3d75a96709aaa1621253"

        return "Le contrôleur de test fonctionne $url!"
    }
}
