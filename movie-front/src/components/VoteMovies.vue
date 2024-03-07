<template>
	<div>
		<GenresSelector :genres="genres" v-model="selectedGenres" />
		<div class="d-flex justify-content-center" v-touch:swipe.left="nextMovie" v-touch:swipe.right="prevMovie">
			<div class="slider-container">
				<div v-if="movies.length">
					<div class="movie" v-for="(movie, index) in filteredMovies" :key="movie.id" :class="{ 'active-movie row': index === currentIndex }">
						<div class="col-12 col-md-4"><img :src="`https://image.tmdb.org/t/p/w500${movie.poster_path}`" alt="Poster" /></div>
						<div class="col-12 col-md-8 p-3 movie-header">
							<h2>{{ movie.title }}</h2>
							<h3>note : {{ movie.vote_average }}</h3>
							<div class="d-inline-flex">
								<span v-for="genreId in movie.genre_ids" :key="genreId" class="py-1 genre-container genre">
									<span class="">{{ genreName(genreId) }}</span>
								</span>
							</div>
						</div>
						<div class="row">
							<p class="my-5 col-12 col-md-6">{{ movie.overview }}</p>
							<div class="col-12 col-md-6"><iframe :src="trailerUrl" ref="youtubePlayer" width="100%" height="315" frameborder="0" allowfullscreen></iframe></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="d-flex px-5 align-bottom nav-bottom justify-content-center">
			<div class="d-flex bar-nav-bottom justify-content-between align-items-center">
				<button @click="prevMovie" class="btn-nav left">&#10094;</button>
				<button @click="nextMovie" class="btn-nav right">&#10095;</button>
			</div>
		</div>
	</div>
</template>

<script>
import GenresSelector from "@/components/GenresSelector.vue";
import MovieService from "@/services/MovieService";
export default {
	components: {
		GenresSelector,
	},
	data() {
		return {
			movies: [],
			selectedGenres: [],
			currentIndex: 0,
			trailerUrl: "",
			genres: [],
		};
	},
	computed: {
		filteredMovies() {
			if (this.selectedGenres.length === 0) return this.movies;
			return this.movies.filter((movie) => movie.genre_ids.some((id) => this.selectedGenres.includes(id)));
		},
	},
	methods: {
		genreName(genreId) {
			const genre = this.genres.find((genre) => genre.id === genreId);
			return genre ? genre.name : "Genre inconnu";
		},
		async getAllMovies() {
			const response = await MovieService.getAllVoteMovies();
			this.movies = response.data;
			if (this.movies.length > 0) {
				this.fetchMovieTrailers(this.movies[0].id);
			}
		},
		async getGenres() {
			const response = await MovieService.getGenres();
			this.genres = response.data; // Adjust according to your API response structure
		},
		loadCurrentMovieTrailer() {
			const currentMovie = this.filteredMovies[this.currentIndex];
			if (currentMovie) {
				this.fetchMovieTrailers(currentMovie.id);
			}
		},
		fetchMovieTrailers(movieId) {
			MovieService.getMovieTrailers(movieId)
				.then((response) => {
					this.trailerUrl = `https://www.youtube.com/embed/${response.data.key}`;
				})
				.catch((error) => {
					console.error("Error fetching trailers:", error);
					this.trailerUrl = "";
				});
		},
		nextMovie() {
			if (this.currentIndex < this.filteredMovies.length - 1) {
				this.currentIndex++;
			} else {
				this.currentIndex = 0;
			}
		},
		prevMovie() {
			if (this.currentIndex > 0) {
				this.currentIndex--;
			} else {
				this.currentIndex = this.filteredMovies.length - 1;
			}
		},
	},
	created() {
		this.getAllMovies();
		this.getGenres();
	},
};
</script>

<style>
.nav-bottom {
	position: fixed;
	bottom: 0.1em;
	width: 100%;
	height: 2em;
}
.bar-nav-bottom {
	box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
	background-color: #fff;
	border-radius: 1em;
	width: 30vw;
}

.btn-nav {
	/* transform: translateY(-50%); Ajuste le centrage vertical */
	background: none;
	border: none;
	font-size: 1em; /* Ajustez selon vos préférences */
	cursor: pointer; /* Change le curseur en pointer pour indiquer l'action */
	color: #333;
	margin: none;
	width: 100% !important;
	position: relative !important;
}

.btn-nav.left {
	left: 0.3em;
}

.btn-nav.right {
	right: 0.3em;
}
.title-page {
	margin-bottom: 1em;
}

.genre {
	background-color: #402060;
	color: white;
	padding: 4px 8px;
	text-align: center;
	border-radius: 5px;
	margin: 0.5em;
}
.genre-button {
	margin-right: 10px;
}

.movie {
	width: 100%;
	display: none;
}

.movie img {
	max-width: 90%;
	height: 30vh;
	max-height: 30vh;
}
.active-movie {
	display: flex; /* Afficher seulement le film actif */
	width: 100%;
}

.slider-container {
	position: relative;
	max-width: 45vw;
	margin: auto;
	margin-top: 2em !important;
	box-shadow: rgba(228, 228, 228, 0.24) 0px 3px 8px;
	padding: 2em;
	background-color: #fff;
	border-radius: 0.4em;
	height: 80vh;
	max-height: 80vh;
	overflow-y: auto;
}

.movies-container {
	display: flex;
	align-items: center; /* Centre les éléments verticalement */
	justify-content: center; /* Centre le contenu horizontalement */
	position: relative; /* Nécessaire pour positionner les boutons de navigation */
	max-width: 60vw;
	height: 80vh;
	max-height: 80vh;
}

@media (max-width: 968px) {
	.slider-container {
		width: 80%;
		max-width: 80vw;
		padding: 1em;
	}
	.btn-nav {
		bottom: 0px !important;
	}
	.btn-nav {
		/* transform: translateY(-50%); Ajuste le centrage vertical */
		background: none;
		border: none;
		font-size: 1.6em; /* Ajustez selon vos préférences */
		cursor: pointer; /* Change le curseur en pointer pour indiquer l'action */
		color: #333;
		margin: none;
		width: auto !important;
		position: relative !important;
	}

	.genre {
		font-size: 0.6em;
		margin: 0.3em;
		padding: 2px 4px;
	}
	.movie img {
		max-width: 90%;
		height: 55vh;
		max-height: 55vh;
	}
	.row {
		--bs-gutter-x: 0.1rem !important;
	}
}
</style>
