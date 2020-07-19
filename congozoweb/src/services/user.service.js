import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:9090/api/public/';
//const API_URL = 'http://Congozo-env-1.eba-4psdgx3b.us-east-2.elasticbeanstalk.com/api/public/';


class UserService {
    getPublicContent() {
        return axios.get(API_URL + 'all');
    }

    getUserBoard() {
        return axios.get(API_URL + 'user', { headers: authHeader() });
    }

    getModeratorBoard() {
        return axios.get(API_URL + 'mod', { headers: authHeader() });
    }

    getAdminBoard() {
        return axios.get(API_URL + 'admin', { headers: authHeader() });
    }
}

export default new UserService();