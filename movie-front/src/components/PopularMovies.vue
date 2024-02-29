<template>
	<div class="slider-container">
		<h1>Films Populaires</h1>
		<div v-if="movies.length" class="movies-container">
			<button @click="prevMovie" class="btn-nav left">
				<i class="bi bi-chevron-left"></i>
			</button>
			<div>
				<div class="row">
					<div class="col-6"><img :src="`https://image.tmdb.org/t/p/w500${currentMovie.poster_path}`" alt="Poster" /></div>
					<div class="col-6 py-5 px-5">
						<h2>{{ currentMovie.title }} | note : {{ currentMovie.vote_average }}</h2>

						<p>{{ currentMovie.overview }}</p>
						<div class="rate">{{ currentMovie.vote_average }}</div>
					</div>
				</div>
			</div>
			<button @click="nextMovie" class="btn-nav right">
				<i class="bi bi-chevron-right"></i>
			</button>
		</div>
	</div>
</template>

<script>
import MovieService from "@/services/MovieService";

export default {
	data() {
		return {
			movies: [],
			currentIndex: 0,
		};
	},
	computed: {
		currentMovie() {
			return this.movies[this.currentIndex];
		},
	},
	created() {
		this.getAllPopularMovies();
	},
	methods: {
		getAllPopularMovies() {
			MovieService.getAllPopularMovies()
				.then((response) => {
					this.movies = response.data;
					this.currentIndex = 0;
				})
				.catch((error) => {
					console.error("There was an error fetching the movies:", error);
				});
		},
		nextMovie() {
			if (this.currentIndex < this.movies.length - 1) {
				this.currentIndex++;
			} else {
				this.currentIndex = 0; // Revenir au début si on est au dernier film
			}
		},
		prevMovie() {
			if (this.currentIndex > 0) {
				this.currentIndex--;
			} else {
				this.currentIndex = this.movies.length - 1; // Aller au dernier film si on est au premier
			}
		},
	},
};
</script>
<style>
.slider-container {
	position: relative;
	max-width: 50vw;
	margin: auto;
}

.movies-container {
	display: flex;
	align-items: center; /* Centre les éléments verticalement */
	justify-content: center; /* Centre le contenu horizontalement */
	position: relative; /* Nécessaire pour positionner les boutons de navigation */
}

.btn-nav {
	position: absolute;
	top: 50%;
	transform: translateY(-50%); /* Ajuste le centrage vertical */
	background: none;
	border: none;
	font-size: 5em; /* Ajustez selon vos préférences */
	cursor: pointer; /* Change le curseur en pointer pour indiquer l'action */
}

.btn-nav.left {
	left: -1em; /* Positionne le bouton à gauche */
}

.btn-nav.right {
	right: -1em;
}
</style>
