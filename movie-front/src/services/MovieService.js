import axios from 'axios';

const API_URL = 'http://localhost:8080';

class MovieService {
    getPopularMovies() {
        return axios.get(`${API_URL}/popular`);
    }
}

export default new MovieService();
