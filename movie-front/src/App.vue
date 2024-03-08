<template>
	<div class="site-container">
		<HeaderMenu />
		<div class="content-wrap">
			<div><router-view /></div>
		</div>
		<FooterMood />
	</div>
</template>

<script>
import HeaderMenu from "./components/HeaderMenu.vue";
import FooterMood from "./components/FooterMood.vue";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

export default {
	name: "App",
	components: {
		HeaderMenu,
		FooterMood,
	},
};
</script>

<style>
html,
body {
	width: 100%;
	min-width: 100%;
	max-width: 100%;
	margin: 0;
	padding: 0;
	background-color: #16191a;
	height: 100%;
}

.site-container {
	min-height: 100vh;
	position: relative;
}
.content-wrap {
	padding-bottom: 60px;
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
	max-width: 100%;
}
@media (max-width: 968px) {
	#app {
		margin-top: 0px !important;
		max-height: 100vh !important;
	}
}
</style>
