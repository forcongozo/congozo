import axios from 'axios'

const LOGIN = 'LOGIN';
const LOGOUT = 'LOGOUT';
const API_URL = 'http://localhost:9090/api/auth/';

// initial state
const state = {
    authenticated: !!localStorage.getItem('user.jwt'),
    token: localStorage.getItem('user.jwt'),
    roles: JSON.parse(localStorage.getItem('user.roles')) || []
};

// getters
const getters = {
    isLoggedIn: state => state.authenticated,
    userRoles: state => state.roles
};

// mutations
const mutations = {
    [LOGIN] (state, payload) {
        state.authenticated = true;
        state.roles = payload
    },
    [LOGOUT] (state) {
        state.authenticated = false;
        state.token = ''
    }
};

// actions
const actions = {
    login({commit}, user) {
        return axios
            .post(API_URL + 'signin', {
                usernameOrEmail: user.email,
                password: user.password
            })
            .then(response => {
                if (response.data) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                commit(LOGIN, response.data.roles)
            });
    },
    register({commit}, user) {
        return axios
            .post(API_URL + 'signup', {
                email: user.email,
                password: user.password
            })
            .then(response => {
                if (response.data) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                commit(LOGIN, response.data.roles)
            });
    },
    logout ({ commit }) {
        localStorage.removeItem('user');
        delete axios.defaults.headers.common['Authentification'];
        commit(LOGOUT)
    }
};

export default {
    state,
    getters,
    mutations,
    actions
}
