// src/router/index.js
import { createRouter, createWebHistory } from "vue-router";
import VoteMovies from "..//components/VoteMovies";
import PopularMovies from "..//components/PopularMovies.vue";
import FiltersMovies from "../components/MovieList.vue";

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),

	routes: [
		{
			path: "/vote",
			name: "Vote",
			component: VoteMovies,
		},
		{
			path: "/popular",
			name: "Popular",
			component: PopularMovies,
		},
		{
			path: "/test",
			name: "Test",
			component: FiltersMovies,
		},
	],
});

export default router;
