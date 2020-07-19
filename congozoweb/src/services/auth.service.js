import axios from 'axios';

const API_URL = 'http://localhost:9090/api/auth/';
//const API_URL = 'http://Congozo-env-1.eba-4psdgx3b.us-east-2.elasticbeanstalk.com/api/auth/';

class AuthService {
    login(user) {
        return axios
            .post(API_URL + 'signin', {
                usernameOrEmail: user.usernameOrEmail,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }
    register(user) {
        return axios.post(API_URL + 'signup', {
            vorname: user.username,
            nachname: user.nachname,
            geschlecht: user.geschlecht,
            geburtsdatum: user.geburtsdatum,
            heimatort: user.heimatort,
            email: user.email,
            password: user.password
        });
    }
}

export default new AuthService();