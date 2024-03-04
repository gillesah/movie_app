package com.movieapp.movieapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class MovieappApplication {
	@Bean
	fun corsConfigurer() = object : WebMvcConfigurer {
		override fun addCorsMappings(registry: CorsRegistry) {
			registry.addMapping("/**")
				.allowedOrigins("https://mycinehunt.com") // Autorise le frontend Vue.js
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<MovieappApplication>(*args)
}
