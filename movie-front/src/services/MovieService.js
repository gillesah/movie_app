import axios from 'axios';

const API_URL = 'http://localhost:8080';

class MovieService {
    getAllPopularMovies() {
        return axios.get(`${API_URL}/popular`);
    }
}

export default new MovieService();
