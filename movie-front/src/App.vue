<template>
	<PopularMovies />
</template>

<script>
import PopularMovies from "./components/PopularMovies.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

export default {
	name: "App",
	components: {
		PopularMovies,
	},
};
</script>

<style>
html,
body {
	width: 100%;
	min-width: 100%;
	margin: 0;
	padding: 0;
}
#app {
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
	margin-top: 20px;
	width: 100%;
	min-width: 100%;
}
</style>
