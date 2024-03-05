/* eslint-disable */
<template>
	<h1 class="title-page">Mon film ce soir</h1>

	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Filtrer par genre</button>
		<ul class="dropdown-menu">
			<li v-for="genre in genres" :key="genre.id">
				<label class="dropdown-item">
					<input type="checkbox" :value="genre.id" v-model="selectedGenres" />
					{{ genre.name }}
				</label>
			</li>
		</ul>
	</div>

	<div class="row">
		<button @click="prevMovie" class="btn-nav left col-1 col-md-3">&#10094;</button>

		<div class="slider-container col-10 col-md-6">
			<div v-if="movies.length">
				<div v-touch:swipe.left="nextMovie" v-touch:swipe.right="prevMovie" class="">
					<div class="movie" v-for="(movie, index) in filteredMovies" :key="movie.id" :class="{ 'active-movie row': index === currentIndex }">
						<div class="col-12 col-md-4"><img :src="`https://image.tmdb.org/t/p/w500${movie.poster_path}`" alt="Poster" /></div>
						<div class="col-10 col-md-8 p-3 movie-header">
							<h2>{{ movie.title }}</h2>
							<h3>note : {{ movie.vote_average }}</h3>
							<div class="row">
								<span v-for="genreId in movie.genre_ids" :key="genreId" class="py-1 genre-container col-5 col-md-3 genre">
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
		<button @click="nextMovie" class="btn-nav right col-1 col-md-3">&#10095;</button>
	</div>
</template>

<script>
import MovieService from "@/services/MovieService";

export default {
	data() {
		return {
			movies: [],
			genres: [],
			selectedGenres: [],
			currentIndex: 0,
			trailerUrl: "",
		};
	},

	computed: {
		filteredMovies() {
			if (this.selectedGenres.length === 0) return this.movies;
			return this.movies.filter((movie) => movie.genre_ids.some((id) => this.selectedGenres.includes(id)));
		},
	},

	watch: {
		currentIndex() {
			this.loadCurrentMovieTrailer();
		},
	},
	methods: {
		toggleGenreSelection(genreId) {
			const index = this.selectedGenres.indexOf(genreId);
			if (index !== -1) {
				this.selectedGenres.splice(index, 1);
			} else {
				this.selectedGenres.push(genreId);
			}
		},
		genreName(genreId) {
			const genre = this.genres.find((genre) => genre.id === genreId);
			return genre ? genre.name : "Genre inconnu";
		},
		getAllMovies() {
			MovieService.getAllPopularMovies().then((response) => {
				this.movies = response.data;
				if (this.movies.length > 0) {
					this.fetchMovieTrailers(this.movies[0].id);
				}
			});
		},
		getGenres() {
			MovieService.getGenres().then((response) => {
				this.genres = response.data;
			});
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
					// Assuming the response data has a "key" or "id" property containing the video ID:
					this.key = response.data;
					this.trailerUrl = `https://www.youtube.com/embed/${this.key}`;
				})
				.catch((error) => {
					console.error("Error fetching trailers:", error);
					this.trailerUrl = ""; // Set an empty URL in case of error
				});
		},

		//940551

		nextMovie() {
			if (this.currentIndex < this.movies.length - 1) {
				this.currentIndex++;
			} else {
				this.currentIndex = 0; // Loop back to the start
			}
		},
		prevMovie() {
			if (this.currentIndex > 0) {
				this.currentIndex--;
			} else {
				this.currentIndex = this.movies.length - 1; // Loop to the end
			}
		},
		loadYouTubeVideo() {
			let tag = document.createElement("script");
			tag.src = "https://www.youtube.com/iframe_api";
			let firstScriptTag = document.getElementsByTagName("script")[0];
			firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

			window.onYouTubeIframeAPIReady = () => {
				new window.YT.Player(this.$refs.youtubePlayer, {
					events: {
						onReady: this.onPlayerReady,
					},
				});
			};
		},

		onPlayerReady(event) {
			event.target.playVideo();
		},
	},
	created() {
		this.getAllMovies();
		this.getGenres();

		// if (this.movies.length > 0) {
		// 	this.fetchMovieTrailers(this.movie.id);
		// }
	},
};
</script>

<style>


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
	box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
	padding: 2em;
}

.movies-container {
	display: flex;
	align-items: center; /* Centre les éléments verticalement */
	justify-content: center; /* Centre le contenu horizontalement */
	position: relative; /* Nécessaire pour positionner les boutons de navigation */
	max-width: 60vw;
	height: 100vh;
	max-height: 100vh;
}

.btn-nav {
	position: absolute;
	top: 50%;
	height: 100vh !important;

	transform: translateY(-50%); /* Ajuste le centrage vertical */
	background: none;
	border: none;
	font-size: 5em; /* Ajustez selon vos préférences */
	cursor: pointer; /* Change le curseur en pointer pour indiquer l'action */
}

.btn-nav.left {
	left: -1em;
}

.btn-nav.right {
	right: -1em;
}

@media (max-width: 768px) {

	.slider-container {
		width: 90vw;
	}
	.btn-nav {
	}
	.btn-nav.left {
		left: 0em;
	}

	.btn-nav.right {
		right: 0em;
	}
}
</style>
