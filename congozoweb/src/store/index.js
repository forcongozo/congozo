import Vue from 'vue';
import Vuex from 'vuex';

import auth from './modules/auth.js';
import experience from "./modules/experience";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        auth,
        experience
    }
});

export const store = Vue.observable({
    isNavOpen: false
});

export const mutations = {
    toggleNav(){
        store.isNavOpen = !store.isNavOpen
    }
};
