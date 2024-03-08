<template>
	<div>
		<button @click="getAllMovies('popular')">Get Popular Movies</button>
		<button @click="getAllMovies('vote')">Get Upcoming Movies</button>

		<router-link :to="{ path: '/movies', query: { movieType: 'popular' } }">
			<button>Go to MovieList</button>
		</router-link>
	</div>
</template>
<script>
// export default {
// 	methods: {
// 		getAllMovies(movieType) {
// 			this.$emit("get-movies", movieType);
// 		},
// 	},
// };
</script>
