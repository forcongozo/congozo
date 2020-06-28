<template>
    <div>
        <form name="form" @submit.prevent="handleLogin">
            <div v-if="!successful">
                <div class="form-group">
                    <input
                            v-model="user.usernameOrEmail"
                            v-validate="'required|min:3|max:20'"
                            type="text"
                            class="form-control"
                            name="usernameOrEmail"
                            placeholder="Email or Username"
                    />
                    <div
                            v-if="submitted && errors.has('usernameOrEmail')"
                            class="alert-danger"
                    >{{errors.first('usernameOrEmail')}}</div>
                </div>
            </div>
            <div v-if="!successful">
                <div class="form-group">
                    <input
                            v-model="user.password"
                            v-validate="'required|min:3|max:20'"
                            type="text"
                            class="form-control"
                            name="pasword"
                            placeholder="Password"
                    />
                    <div
                            v-if="submitted && errors.has('password')"
                            class="alert-danger"
                    >{{errors.first('password')}}</div>
                </div>
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-block">Login</button>
            </div>

        </form>
        <div v-if="message"
             class="alert"
             :class="successful ? 'alert-success' : 'alert-danger'"
        >{{message}}
        </div>
        <router-link to="/FbLogin">
            Mit Facebook anmelden
        </router-link>
        <br/>
        <router-link to="/GmLogin">
            Mit Gmail anmelden
        </router-link>
        <br/>
        <router-link to="/EmlLogin" class="nav-link">
            Mit Email anmelden
        </router-link>
        <br/>
        <router-link to="/TelLogin" class="nav-link">
            Mit Telefonenummer anmelden
        </router-link>
        <br/>
        <h6>Noch kein Mitglied?</h6>
        <h7>Hier kannst du dich registrieren</h7>

        <router-link to="/Register" class="nav-link">
            JETZT REGISTRIEREN >>
        </router-link>
    </div>
</template>

<script>
    import User from '../models/user';

    export default {
        name: 'Login',
        data() {
            return {
                user: new User('', '', '', '', '', '', '', ''),
                loading: false,
                message: ''
            };
        },
        computed: {
            loggedIn() {
                return this.$store.state.auth.status.loggedIn;
            }
        },
        created() {
            if (this.loggedIn) {
                this.$router.push('/profile');
            }
        },
        methods: {
            handleLogin() {
                this.loading = true;
                this.$validator.validateAll().then(isValid => {
                    if (!isValid) {
                        this.loading = false;
                        return;
                    }

                    if (this.user.usernameOrEmail && this.user.password) {
                        this.$store.dispatch('auth/login', this.user).then(
                            () => {
                                this.$router.push('/profile');
                            },
                            error => {
                                this.loading = false;
                                this.message =
                                    (error.response && error.response.data) ||
                                    error.message ||
                                    error.toString();
                            }
                        );
                    }
                });
            }
        }
    };
</script>

<style scoped>

</style>