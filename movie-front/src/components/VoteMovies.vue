<template>
	<div>
		<GenresSelector :genres="genres" v-model="selectedGenres" />
		<MovieList :movies="movies" :selectedGenres="selectedGenres" />
	</div>
</template>

<script>
import GenresSelector from "./GenresSelector.vue"; // Ajustez le chemin selon votre structure
import MovieList from "./MovieList.vue"; // Ajustez le chemin selon votre structure
import MovieService from "@/services/MovieService.js"; // Ajustez selon votre implémentation

export default {
	components: {
		GenresSelector,
		MovieList,
	},
	data() {
		return {
			genres: [],
			selectedGenres: [],
			movies: [],
		};
	},
	async created() {
		await this.fetchGenres();
		await this.fetchMovies();
	},
	methods: {
		async fetchGenres() {
			MovieService.getGenres().then((response) => {
				this.genres = response.data;
			});
		},
		async fetchMovies() {
			MovieService.getAllPopularMovies().then((response) => {
				this.movies = response.data;
				if (this.movies.length > 0) {
					this.fetchMovieTrailers(this.movies[0].id);
				}
			});
		},
		fetchMovieTrailers(movieId) {
			MovieService.getMovieTrailers(movieId)
				.then((response) => {
					// Assuming the response data has a "key" or "id" property containing the video ID:
					this.key = response.data;
					this.trailerUrl = `https://www.youtube.com/embed/${this.key}`;
				})
				.catch((error) => {
					console.error("Error fetching trailers:", error);
					this.trailerUrl = ""; // Set an empty URL in case of error
				});
		},
		genreName(genreId) {
			const genre = this.genres.find((genre) => genre.id === genreId);
			return genre ? genre.name : "Genre inconnu";
		},
	},
};
</script>
