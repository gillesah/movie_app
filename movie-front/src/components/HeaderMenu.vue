<template>
	<header class="align-middle">
		<div class="row">
			<div class="col-md-3 col-sm-3 col-3">
				<div class="logo-container">
					<a href="/"><img src="../assets/logo.png" alt="" class="logo" /></a>
				</div>
			</div>
			<div class="vertical-flex col-md-6 col-sm-6 col-6 text-end align-middle">
				<div class="'bi ' + menu_icon" @click="toggleMenu">
					<i :class="menu_icon"></i>
				</div>
				<div :class="{ desktop_menu: true, mobile_menu: menuValue }" id="desktop_menu">
					<ul>
						<li @click="closeMenu">Toutes les ruches</li>
						<li @click="closeMenu">Mes ruches</li>
						<li @click="closeMenu">Créer une ruche</li>
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
			menuValue: true,
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
header {
	padding: 0px;
	margin-bottom: 2em;
}
.logo {
	width: 10vw;
}
.logo:hover {
	cursor: pointer;
}

.desktop_menu {
	margin-top: 2vh;
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

#desktop_menu ul li a {
	position: relative;
	line-height: 1.4em;
	margin-top: 2em;
	margin-bottom: 3em;
	text-decoration: none;
}

#desktop_menu ul li a::after {
	content: "";
	position: absolute;
	width: 100%;
	height: 1px;
	border-radius: 4px;
	background-color: #fff;
	bottom: 0;
	left: 0;
	transform-origin: right;
	transform: scaleX(0);
	transition: transform 0.3s ease-in-out;
}
#desktop_menu ul li a:hover::after {
	transform-origin: left;
	transform: scaleX(1);
}

.bi-list {
	display: none;
}
.bi-person-fill {
	font-size: 2em;
	margin-bottom: 0 !important;
}
.login-link a {
	font-size: 1em;
}
.vertical-flex-center {
	display: flex;
	align-items: center;
	justify-content: center;
}
.vertical-flex {
	display: flex;
	align-items: center;
	justify-content: flex-end;
}
.modal-body form input {
	margin: 1em;
}

@media screen and (min-width: 128px) and (max-width: 900px) {
	.desktop_menu {
		position: fixed !important;
		transition: 0.4 ease-in-out;

		right: -280px;
		top: 34px;
		width: 280px;
		height: 100%;
	}
	.desktop_menu ul li {
		display: block;
		text-align: left;
		margin: 0;
		padding: 10px 0 10px 26px;
		border-bottom: 1px solid grey;
	}
	.bi-list {
		display: block !important;
		color: white;
		font-size: 3em;
		padding: 10px;
	}
	.mobile_menu {
		right: 0;
		transition: 0.4 ease-in-out;
	}
	.logo {
		width: 18vw;
	}
}
</style>
