<template>
	<div>
		<GenresSelector :genres="genres" v-model="selectedGenres" />
		<MovieList :fetchMoviesMethod="getAllMovies" :genres="genres" />
		<!-- The navigation buttons are now part of MovieList and should be managed there if needed -->
	</div>
</template>

<script>
import GenresSelector from "@/components/GenresSelector.vue";
import MovieList from "@/components/MovieList.vue";
import MovieService from "@/services/MovieService";

export default {
	components: {
		GenresSelector,
		MovieList,
	},
	data() {
		return {
			genres: [], 
			selectedGenres: [],
		};
	},
	methods: {
		async getAllMovies() {
			const response = await MovieService.getAllVoteMovies();

			return response; 
		},
		async getGenres() {
			const response = await MovieService.getGenres();
			this.genres = response.data; 
		},
	},
	async created() {
		await this.getGenres();
	},
};
</script>
