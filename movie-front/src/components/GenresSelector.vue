<template>
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
</template>

<script>
export default {
	props: {
		genres: {
			type: Array,
			default: () => [],
		},
		modelValue: {
			type: Array,
			default: () => [],
		},
	},
	emits: ["update:modelValue"],
	data() {
		return {
			selectedGenres: this.modelValue,
		};
	},
	watch: {
		selectedGenres(newVal) {
			this.$emit("update:modelValue", newVal);
		},
	},
};
</script>
