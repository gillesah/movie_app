import axios from "axios";

const API_URL = process.env.VUE_APP_API_URL;

class MovieService {
	getAllPopularMovies(genreId) {
		let url = `${API_URL}/popular`;
		if (genreId) {
			url += `?genreId=${genreId}`;
		}
		return axios.get(url);
	}
	getAllVoteMovies(genreId) {
		let url = `${API_URL}/vote`;
		if (genreId) {
			url += `?genreId=${genreId}`;
		}
		return axios.get(url);
	}

	getGenres() {
		return axios.get(`${API_URL}/genres`);
	}

	getMovieTrailers(movieId) {
		return axios.get(`${API_URL}/${movieId}/trailers`);
	}
	getAllMoviesByType(movieType, genreId) {
		let url = `${API_URL}/${movieType}`;
		if (genreId) {
			url += `?genreId=${genreId}`;
		}
		return axios.get(url);
	}
}

export default new MovieService();
