<template>
  <div id="app">
    <nav id="main-nav" class="navbar navbar-expand-lg navbar-light bg-light">
      <ul>
        <li>
          <burger></burger>
        </li>
        <li>
          <div class="logo">
            ConGozo
          </div>
        </li>
        <li class="login">
          <router-link to="/login">Anmelden/ Registrieren</router-link>
        </li>
      </ul>
    </nav>

    <Sidebar>
      <div @click="closeSidebarPanel">
        <ul class="sidebar-panel-nav">
          <li><a href="#hilfe_faqs">Hilfe/ FAQs</a>
          </li>
          <li>
            <router-link to="/login">Anmelden/ Registrieren</router-link>
          </li>
          <li><a href="#erlebnis_suchen">Erlebnis suchen</a>
          </li>
          <li><a href="#erlebnis_anbieten">Erlebnis anbieten</a>
          </li>
          <li><a href="#profile">Dein Profile</a>
          </li>
          <li><a href="#nachrichten">Nachrichten</a>
          </li>
          <li><a href="#forum">Forum</a>
          </li>
          <li><a href="#auslogen">Auslogen</a>
          </li>
        </ul>
      </div>
    </Sidebar>
    <div class="congozo-container-full-width">
      <router-view />
    </div>
  </div>
</template>

<script>
  import Burger from './components/menu/Burger.vue';
  import Sidebar from './components/menu/Sidebar.vue';
  import { store, mutations } from "./store";

  export default {
    name: 'app',
    components: {
      Burger,
      Sidebar
    },
    methods: {
      closeSidebarPanel: mutations.toggleNav
    },
    computed: {
      isPanelOpen() {
        return store.isNavOpen
      }
    }
  }
</script>
<style>
  html {
    height: 100%;
    overflow:hidden;
  }

  body {
    border: 0; margin: 0; padding: 0;
    font-family: 'Lato';
    height: 100%;
    /*background: rgb(101,31,87); */
    /*background: linear-gradient(45deg, rgba(101,31,87,1) 0%, rgba(225,113,87,1) 48%, rgba(249,248,113,1) 100%); */
    /*background: #ecd569 !important; */

  }

  .logo {
    align-self: center;
    font-weight: bold;
    font-family: 'Lato'
  }

  .main-nav {
    display: flex;
    justify-content: space-between;
    padding: 0.5rem 0.8rem;
    border: #130f40;
    border: 1px;
  }

  ul.sidebar-panel-nav {
    list-style-type: none;
  }

  ul.sidebar-panel-nav > li > a {
    color:black;
    text-decoration: none;
    font-size: 1.5rem;
    display: block;
    padding-bottom: 0.5em;
  }
</style>