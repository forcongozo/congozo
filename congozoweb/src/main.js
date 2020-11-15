import Vue from 'vue';
import App from './App.vue';
import { router } from'./router';
import store from './store';
import 'bootstrap';
import './style/congozo.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import axios from 'axios'
import VModal from 'vue-js-modal'

Vue.prototype.$http = axios;

import VueLayers from 'vuelayers'
import 'vuelayers/lib/style.css'

Vue.use(VueLayers);

Vue.config.productionTip = false;
Vue.use(VeeValidate);

Vue.use(VModal);

import VueSlider from 'vue-slider-component'
import 'vue-slider-component/theme/default.css'

Vue.component('VueSlider', VueSlider);

// ES Modules syntax
import fetch from 'node-fetch';
global.fetch = fetch;

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
