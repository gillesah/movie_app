import axios from "axios";

const API_URL = "http://localhost:8080";

class MovieService {
	getAllPopularMovies(genreId) {
		let url = `${API_URL}/popular`;
		if (genreId) {
			url += `?genreId=${genreId}`;
		}
		return axios.get(url);
	}

	getGenres() {
		return axios.get(`${API_URL}/genres`);
	}
}

export default new MovieService();
