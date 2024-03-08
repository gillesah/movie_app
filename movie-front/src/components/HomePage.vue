<template>
	<div>
		<div class="feeling">
			<p>Today I'm in the mood</p>
			<br />
			<router-link :to="{ path: '/movies', query: { movieType: 'vote', genreId: '35%7C18' } }">depressed</router-link><br />
			<router-link :to="{ path: '/movies', query: { movieType: 'vote', genreId: '35' } }">cheerful</router-link><br />
			<router-link :to="{ path: '/movies', query: { movieType: 'vote', genreId: '37%7C99%7C12' } }">ambitious</router-link><br />
			<router-link :to="{ path: '/movies', query: { movieType: 'vote', genreId: '14%7C10402%7C878' } }">curious</router-link><br />

			<br />
		</div>
		<div class="withoutfeeling">
			<router-link :to="{ path: '/movies', query: { movieType: 'popular' } }">
				<p>Go to Popular</p>
			</router-link>
			<router-link :to="{ path: '/movies', query: { movieType: 'vote' } }">
				<p>Go to best movies list</p>
			</router-link>
		</div>
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
<style>
.feeling p,
.feeling a {
	color: #fff;
	font-size: 3em !important;
}
.withoutfeeling p {
	color: #fff;
}
@media (max-width: 968px) {
	.feeling {
		margin-top: 2em;
	}
}
</style>
