<template>
	<header class="header">
		<div class="logo-container">
			<div class="logosite">
				<router-link to="/">
					Mood <br />
					Cine
				</router-link>
			</div>
		</div>
		<div @click="toggleMenu" class="menu-icon-container">
			<i :class="menu_icon"></i>
		</div>
		<div :class="{ menu: true, open: menuValue }" class="align-middle">
			<ul>
				<li @click="closeMenu"><router-link to="/vote">best rated</router-link></li>
				<li @click="closeMenu"><router-link to="/popular">best popular</router-link></li>
			</ul>
		</div>
	</header>
</template>

<script>
export default {
	data() {
		return {
			menuValue: false, // Initialise le menu comme fermé
			menu_icon: "bi-list",
		};
	},
	methods: {
		toggleMenu() {
			this.menuValue = !this.menuValue;
			this.menu_icon = this.menuValue ? "bi-x-lg" : "bi-list";
		},
		closeMenu() {
			this.menuValue = false;
			this.menu_icon = "bi-list";
		},
	},
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0px;
	margin: 0 3em 0 3em;
	line-height: 1;
	text-align: left;
}

.logosite a {
	font-family: "Montserrat", sans-serif;
	color: #fff;
	font-weight: 800;
	font-size: 1.8em;
	text-decoration: none;
}

.menu-icon-container {
	color: white;
	font-size: 2em;
	cursor: pointer;
}
.bi-x-lg {
	color: #333;
	position: relative;
	z-index: 1002;
}

.menu {
	display: none;
	position: fixed;
	top: 0;
	right: -100%;
	height: 100%;
	width: 280px;
	background-color: #fff;
	transition: right 0.3s ease;
	z-index: 1000;
}

.menu.open {
	display: block;
	right: 1em;
}

.menu ul {
	list-style-type: none;
	padding: 1em;
	margin-top: 20vh;
}

.menu ul li {
	padding: 10px 0;
	border-bottom: 1px solid grey;
}

.menu ul li a {
	color: #333;
	text-decoration: none;
}
@media (max-width: 968px) {
	.logosite {
		font-size: 0.8em;
		line-height: 1em;
	}
	.header {
		margin-top: 2em !important;
	}
}
</style>
