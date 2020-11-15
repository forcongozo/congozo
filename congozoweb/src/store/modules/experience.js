import Vue from'vue'
import axios from 'axios'

const API_URL = 'http://localhost:9090/api/public/';

const CREATE_EXPERIENCE = 'CREATE_EXPERIENCE';
const SET_EXPERIENCE_DATA = 'SET_EXPERIENCE_DATA';

// initial state
const state = {
    experience: {}
};

// getters
const getters = {
    experienceData: state => state.experience
};

// mutations
const mutations = {
    [CREATE_EXPERIENCE] (state, payload) {
        state.experience = payload
    },
    [SET_EXPERIENCE_DATA] (state, payload) {
        Object.entries(payload).forEach(entry => {
            Vue.set(state.experience, entry[0], entry[1]);
        });
    }
};

// actions
const actions = {
    create({commit}, experience) {
        return axios
            .post(API_URL + 'newAdventure', experience)
            .then(response => {
                console.log(response.data);
                commit(CREATE_EXPERIENCE, experience);
            })
            .catch(error => (
                console.log(error)
            ))
    },
    saveExperienceData({commit}, experienceData) {
        console.log(experienceData);
        commit(SET_EXPERIENCE_DATA, experienceData)
    }
};

export default {
    state,
    getters,
    mutations,
    actions
}
