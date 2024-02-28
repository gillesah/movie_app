package com.movieapp.movieapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.io.ClassPathResource
import java.util.*
import java.util.Properties



fun loadConfigurations() {
	val properties = Properties()
	val resource = ClassPathResource("config.properties")
	properties.load(resource.inputStream)

}
@SpringBootApplication
class MovieappApplication



fun main(args: Array<String>) {
	loadConfigurations()
	val apiKey = System.getProperty("TMDB_API_KEY")
	runApplication<MovieappApplication>(*args)
}
