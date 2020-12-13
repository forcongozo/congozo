import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import CreateExperience from "./views/CreateExperience";
// Delete Later
import ExperienceSecond from "./components/ExperienceSecond";
import ExperienceThird from "./components/ExperienceThird";
import ExperienceFourth from "./components/ExperienceFourth";
import ExperienceFifth from "./components/ExperienceFifth";
import ExperienceSixth from "./components/ExperienceSixth";

Vue.use(Router);

export const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/create',
            component: CreateExperience
        },
        {
            path: '/create/2',
            component: ExperienceSecond
        },
        {
            path: '/create/3',
            component: ExperienceThird
        },
        {
            path: '/create/4',
            component: ExperienceFourth
        },
        {
            path: '/create/5',
            component: ExperienceFifth
        },
        {
            path: '/create/6',
            component: ExperienceSixth
        },
        {
            path: '/profile',
            name: 'Profile',
            // lazy-loaded
            component: () => import('./views/Profile.vue')
        },

    ]
});

// router.beforeEach((to, from, next) => {
//     const publicPages = ['/login', '/register', '/home'];
//     const authRequired = !publicPages.includes(to.path);
//     const loggedIn = localStorage.getItem('user');
//
//     // trying to access a restricted page + not logged in
//     // redirect to login page
//     if (authRequired && !loggedIn) {
//         next('/login');
//     } else {
//         next();
//     }
// });
