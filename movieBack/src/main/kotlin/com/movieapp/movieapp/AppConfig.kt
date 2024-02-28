package com.movieapp.movieapp

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.util.Properties

@Component
class AppConfig {
    val properties: Properties = Properties()

    init {
        val resource = ClassPathResource("config.properties")
        properties.load(resource.inputStream)
    }

    fun getApiKey(): String = properties.getProperty("TMDB_API_KEY")
}
