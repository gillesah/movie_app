<template>
	<div class="slider-container">
		<h1>Films Populaires</h1>
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
		<div v-if="movies.length">
			<button @click="prevMovie" class="btn-nav left">&#10094;</button>
			<div class="movie row" v-for="(movie, index) in filteredMovies" :key="movie.id" :class="{ 'active-movie': index === currentIndex }">
				<div class="col-5"><img :src="`https://image.tmdb.org/t/p/w500${movie.poster_path}`" alt="Poster" /></div>
				<div class="col-7 my-5 p-5">
					<h2>{{ movie.title }}</h2>
					<h3>note : {{ movie.vote_average }}</h3>
					<div v-for="genreId in movie.genre_ids" :key="genreId" class="my-3">
						<span class="genre">{{ genreName(genreId) }}</span>
					</div>
					<p class="my-5">{{ movie.overview }}</p>
				</div>
			</div>
			<button @click="nextMovie" class="btn-nav right">&#10095;</button>
		</div>
	</div>
</template>

<script>
import MovieService from "@/services/MovieService";

export default {
	data() {
		return {
			movies: [],
			genres: [],
			selectedGenres: [], // Notez le changement ici pour supporter la sélection multiple
			currentIndex: 0,
		};
	},
	computed: {
		filteredMovies() {
			if (this.selectedGenres.length === 0) return this.movies;
			return this.movies.filter((movie) => movie.genre_ids.some((id) => this.selectedGenres.includes(id)));
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
				// Assurez-vous d'ajuster selon la structure de données réelle
			});
		},
		getGenres() {
			MovieService.getGenres().then((response) => {
				this.genres = response.data;
				// Assurez-vous d'ajuster selon la structure de données réelle
			});
		},
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
	},
	created() {
		this.getAllMovies();
		this.getGenres();
	},
};
</script>

<style>
.genre {
	background-color: #402060;
	color: white;
	padding: 4px 8px;
	text-align: center;
	border-radius: 5px;
	margin: 1em;
}
.genre-button {
	margin-right: 10px;
}

.movie {
	width: 100%;
	display: none;
}
.active-movie {
	display: block; /* Afficher seulement le film actif */
}
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
	max-width: 50vw;
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
