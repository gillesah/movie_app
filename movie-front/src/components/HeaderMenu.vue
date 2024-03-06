<template>
	<header class="align-middle">
		<div class="row">
			<div class="col-md-3 col-sm-3 col-3">
				<div class="logo-container">
					<p class="logosite">Mood <br />Cine</p>
				</div>
			</div>
			<div class="vertical-flex col-md-9 col-sm-9 col-9 text-end align-middle">
				<div class="'bi ' + menu_icon" @click="toggleMenu">
					<i :class="menu_icon"></i>
				</div>
				<div :class="{ desktop_menu: true, mobile_menu: menuValue }" id="desktop_menu">
					<ul>
						<li @click="closeMenu">menu</li>
						<li @click="closeMenu">menu</li>
						<li @click="closeMenu">menu</li>
					</ul>
				</div>
			</div>
		</div>
	</header>
</template>

<script>
import "bootstrap-icons/font/bootstrap-icons.css";

export default {
	data() {
		return {
			menuValue: false,
			menu_icon: "bi-list",
		};
	},

	methods: {
		toggleMenu() {
			this.menuValue = !this.menuValue;
		},
		openMenu() {
			this.menuValue = true;
		},
		closeMenu() {
			this.menuValue = false;
		},
	},
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

header {
	padding: 0px;
	margin-bottom: 2em;
}
.logosite {
	font-family: "Montserrat", sans-serif;
	color: #fff;
	text-align: left;
	margin-left: 10vw;
	font-weight: 800;
	font-size: 1.8em;
	line-height: 0.9em;
}

.desktop_menu {
	display: none; /* Cache par défaut le menu desktop */
}
.desktop_menu ul {
	padding: 0;
	margin: 0;
}
.desktop_menu ul li,
.desktop_menu ul li a {
	list-style-type: none;
	color: #fff;
	display: inline-block;
	font-size: 1.2em !important;
	margin-left: 0.5em;
	font-style: none !important;
	text-decoration: none;
	font-weight: 300;
}

.bi-list {
	display: block !important; /* Affiche toujours l'icône burger */
	color: white;
	font-size: 3em;
	padding: 10px;
	margin-right: 10vh;
}
.bi-person-fill {
	font-size: 2em;
	margin-bottom: 0 !important;
}
.login-link a {
	font-size: 1em;
}
.vertical-flex {
	display: flex;
	align-items: center;
	justify-content: flex-end;
}

/* Style pour le menu mobile appliqué par défaut */
.mobile_menu,
.desktop_menu {
	position: fixed;
	transition: 0.4 ease-in-out;
	right: -280px;
	top: 0;
	width: 280px;
	height: 100%;
	z-index: 1000; /* Assure que le menu est bien au-dessus des autres éléments */
}

.mobile_menu ul li {
	display: block;
	text-align: left;
	margin: 0;
	padding: 10px 0 10px 26px;
	border-bottom: 1px solid grey;
}

/* Quand menuValue est true, déplace le menu pour le montrer */
.mobile_menu {
	right: 0 !important;
}
</style>
