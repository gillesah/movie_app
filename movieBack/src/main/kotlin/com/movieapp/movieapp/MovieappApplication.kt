package com.movieapp.movieapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieappApplication

fun main(args: Array<String>) {
	runApplication<MovieappApplication>(*args)
}
