<template>
    <div>
        <div class="overflow-auto">
            <form name="form" @submit.prevent="handleRegister">
                <div v-if="!successful">
                    <div class="form-group">
                        <label for="Vorname">Vorname</label>
                        <input
                                v-model="user.vorname"
                                v-validate="'required|min:3|max:20'"
                                type="text"
                                class="form-control"
                                name="username"
                        />
                        <div
                                v-if="submitted && errors.has('vorname')"
                                class="alert-danger"
                        >{{errors.first('vorname')}}</div>
                    </div>

                    <div class="form-group">
                        <label for="nachname">nachname</label>
                        <input
                                v-model="user.nachname"
                                v-validate="'required|min:3|max:20'"
                                type="text"
                                class="form-control"
                                name="nachname"
                        />
                        <div
                                v-if="submitted && errors.has('nachname')"
                                class="alert-danger"
                        >{{errors.first('nachname')}}</div>
                    </div>

                    <div class="form-group">
                        <label for="geschlecht">Geschlect</label>
                        <input
                                v-model="user.geschlecht"
                                v-validate="'required|min:1|max:1'"
                                type="text"
                                class="form-control"
                                name="geschlecht"
                        />
                        <div
                                v-if="submitted && errors.has('geschlecht')"
                                class="alert-danger"
                        >{{errors.first('geschlecht')}}</div>
                    </div>

                    <div class="form-group">
                        <label for="Vorname">Geburtsdatum</label>
                        <input
                                v-model="user.geburtsdatum"
                                v-validate="'required|min:3|max:20'"
                                type="text"
                                class="form-control"
                                name="geburtsdatum"
                        />
                        <div
                                v-if="submitted && errors.has('geburtsdatum')"
                                class="alert-danger"
                        >{{errors.first('geburtsdatum')}}</div>
                    </div>

                    <div class="form-group">
                        <label for="Heimatort">Heimatort</label>
                        <input
                                v-model="user.heimatort"
                                v-validate="'required|min:3|max:20'"
                                type="text"
                                class="form-control"
                                name="heimatort"
                        />
                        <div
                                v-if="submitted && errors.has('heimatort')"
                                class="alert-danger"
                        >{{errors.first('heimatort')}}</div>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input
                                v-model="user.email"
                                v-validate="'required|email|max:50'"
                                type="email"
                                class="form-control"
                                name="email"
                        />
                        <div
                                v-if="submitted && errors.has('email')"
                                class="alert-danger"
                        >{{errors.first('email')}}</div>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input
                                v-model="user.password"
                                v-validate="'required|min:6|max:40'"
                                type="password"
                                class="form-control"
                                name="password"
                        />
                        <div
                                v-if="submitted && errors.has('password')"
                                class="alert-danger"
                        >{{errors.first('password')}}</div>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary btn-block">Sign Up</button>
                    </div>
                </div>
            </form>

            <div v-if="message"
                    class="alert"
                    :class="successful ? 'alert-success' : 'alert-danger'"
            >{{message}}
            </div>
        </div>
    </div>
</template>

<script>
    import User from '../models/user';

    export default {
        name: 'Register',
        data() {
            return {
                user: new User('', '', '', '', '', '', '', ''),
                submitted: false,
                successful: false,
                message: ''
            };
        },
        computed: {
            loggedIn() {
                return this.$store.state.auth.status.loggedIn;
            }
        },
        mounted() {
            if (this.loggedIn) {
                this.$router.push('/profile');
            }
        },
        methods: {
            handleRegister() {
                this.message = '';
                this.submitted = true;
                this.$validator.validate().then(isValid => {
                    if (isValid) {
                        this.$store.dispatch('auth/register', this.user).then(
                            data => {
                                this.message = data.message;
                                this.successful = true;
                            },
                            error => {
                                this.message =
                                    (error.response && error.response.data) ||
                                    error.message ||
                                    error.toString();
                                this.successful = false;
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