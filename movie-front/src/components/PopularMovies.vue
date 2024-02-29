<template>
	<div>
		<h1>Film Populaires</h1>
		<div v-if="movie">
			<h2>{{ movie.title }}</h2>
			<img :src="movie.posterPath" alt="Poster" />
			<p>{{ movie.overview }}</p>
		</div>
	</div>
</template>

<script>
import MovieService from "@/services/MovieService";

export default {
	data() {
		return {
			movie: null,
		};
	},
	created() {
		this.getPopularMovie();
	},
	methods: {
		getPopularMovie() {
			MovieService.getPopularMovies()
				.then((response) => {
					this.movie = response.data;
				})
				.catch((error) => {
					console.error("There was an error fetching the movie:", error);
				});
		},
	},
};
</script>
