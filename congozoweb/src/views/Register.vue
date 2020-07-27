<template>
    <div class="full-container">
        <div class="form-container">
            <div class="change-form">
                <div class="radio-buttons">
                    <input
                            type="radio"
                            id="login-form"
                            name="login"
                            value="true"
                            v-model="isLogin"
                    />
                    <label for="login-form">LOGIN</label>
                    <input
                            type="radio"
                            id="sign-up-form"
                            name="sign-up"
                            value="false"
                            v-model="isLogin"
                    />
                    <label for="sign-up-form">SIGN UP</label>
                </div>
            </div>
            <div class="login-form-container" v-if="isLogin === 'true'">
                <form name="form" @submit.prevent="handleLogin">
                    <div class="form-input-container" v-if="!successful">
                        <div class="form-group">
                            <label for="email">E-Mail:</label>
                            <input
                                    v-model="user.email"
                                    v-validate="'required|email|max:50'"
                                    type="email"
                                    class="form-control"
                                    name="email"
                                    id="email-login"
                                    placeholder="Enter e-mail"
                            />
                            <div
                                    v-if="submitted && errors.has('email')"
                                    class="alert-danger"
                            >{{errors.first('email')}}</div>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input
                                    v-model="user.password"
                                    v-validate="'required|min:6|max:40'"
                                    type="password"
                                    class="form-control"
                                    name="password"
                                    id="password-login"
                                    placeholder="Enter password"
                            />
                            <div
                                    v-if="submitted && errors.has('password')"
                                    class="alert-danger"
                            >{{errors.first('password')}}</div>
                        </div>
                        <div class="form-group">
                            <button class="btn-main btn-block">Login</button>
                        </div>
                    </div>
                </form>
                <fieldset>
                    <legend>or</legend>
                </fieldset>
                <div class="social">
                    <div class="social-media-buttons">
                        <button class="facebook-button form-control"><i class="fab fa-facebook-square"/>Login with Facebook</button>
                        <button class="google-button form-control">Login with Google</button>
                    </div>
                </div>
                <div class="privacy-policy">
                    <p>By continuing, you accept our <b>Terms & Conditions</b> and <b>Privacy Policy.</b></p>
                </div>
            </div>
            <div class="register-form-container" v-else>
                <div class="social">
                    <div class="social-media-buttons">
                        <button class="facebook-button form-control">Sign up with Facebook</button>
                        <button class="google-button form-control">Sign up with Google</button>
                    </div>
                </div>
                <fieldset>
                    <legend>or</legend>
                </fieldset>
                <div>
                    <h5>Sign up with your email address</h5>
                </div>
                <form name="form" @submit.prevent="handleRegister">
                    <div class="form-input-container" v-if="!successful">
                        <div class="form-group">
                            <label for="email">E-Mail:</label>
                            <input
                                    v-model="user.email"
                                    v-validate="'required|email|max:50'"
                                    type="email"
                                    class="form-control"
                                    name="email"
                                    id="email"
                                    placeholder="Enter e-mail"
                            />
                            <!--<div
                                    v-if="submitted && errors.has('email')"
                                    class="alert-danger"
                            >{{errors.first('email')}}</div>-->
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input
                                    v-model="user.password"
                                    v-validate="'required|min:6|max:40'"
                                    type="password"
                                    class="form-control"
                                    name="password"
                                    id="password"
                                    placeholder="Enter password"
                            />
                            <!--<div
                                    v-if="submitted && errors.has('password')"
                                    class="alert-danger"
                            >{{errors.first('password')}}</div>-->
                        </div>
                        <div class="form-group">
                            <button class="btn-main btn-block">Sign Up</button>
                        </div>
                    </div>
                </form>
                <div class="privacy-policy">
                    <p>By continuing, you accept our <b>Terms & Conditions</b> and <b>Privacy Policy.</b></p>
                </div>
                <div v-if="message"
                     class="alert"
                     :class="successful ? 'alert-success' : 'alert-danger'"
                >{{message}}</div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Register',
        data() {
            return {
                user: {
                    email: '',
                    password: ''
                },
                isLogin: 'true',
                submitted: false,
                successful: false,
                message: ''
            };
        },
        computed: {
            loggedIn() {
                return this.$store.state.authenticated;
            }
        },
        mounted() {
            if (this.loggedIn) {
                this.$router.push('/profile');
            }
        },
        methods: {
            handleLogin() {
                this.message = '';
                this.submitted = true;
                this.$validator.validate().then(() => {
                    // eslint-disable-next-line no-constant-condition
                    if (true) {
                        this.$store.dispatch('login', this.user).then(
                            data => {
                                this.message = data.message;
                                this.successful = true;
                                this.user.email = '';
                                this.user.password = '';
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
            },
            handleRegister() {
                this.message = '';
                this.submitted = true;
                this.$validator.validate().then(() => {
                    // eslint-disable-next-line no-constant-condition
                    if (true) {
                        this.$store.dispatch('register', this.user).then(
                            data => {
                                this.message = data.message;
                                this.successful = true;
                                this.user.email = '';
                                this.user.password = '';
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
    @import '../style/congozo.css';

    .full-container {
        width: 100%;
        padding: 50px 20px;
        background-image: url("../assets/images/eddy-billard-42nzwGteFgI-unsplash.jpg");
        background-size: cover;
    }

    button {
        max-width: 350px;
        margin: auto;
        border-radius: 2rem;
    }

    fieldset {
        border-top: 1px solid #cdcdcd;
        margin: 1.6rem auto 1rem auto;
        max-width: 480px;
    }

    legend {
        padding: 0 0.5rem;
        margin: auto;
        width: 40px;
    }

    h5 {
        text-align: center;
        margin: 1.5rem auto;
    }

    input {
        margin: auto;
    }

    label {
        font-weight: bold;
    }

    .register-form-container, .login-form-container {
        padding: 20px;
    }

    .form-container {
        margin: auto;
        max-width: 600px;
        background-color: #F4F2F0;
        border-bottom-left-radius: 20px;
        border-bottom-right-radius: 20px;
    }

    .form-group {
        max-width: 480px;
        margin: 20px auto;
    }

    .form-control {
        height: 3rem;
        border-radius: 0;
        max-width: 480px;
    }

    .social-media-buttons {
        display: flex;
        flex-wrap: wrap;
    }

    .social-media-buttons button {
        width: 100%;
        max-width: 480px;
        border-radius: 0;
        margin: 10px auto;
        text-transform: uppercase;
        font-weight: bold;
    }

    .facebook-button {
        background-color: #3b5998;
        color: white;
    }

    .privacy-policy {
        margin: auto;
        max-width: 480px;
        text-align: center;
    }

    .btn-main {
        margin: auto;
        text-transform: uppercase;
    }

    /* Radio Buttons */

    .radio-buttons {
        text-align: center;
    }

    input[type="radio"] {
        opacity: 0;
        position: fixed;
        width: 0;
    }
    .radio-buttons label {
        width: 50%;
        display: inline-block;
        background-color: #F0A002;
        color: white;
        padding: 10px 10px;
        font-family: sans-serif, Arial;
        font-size: 16px;
        border: 1px solid #F0A002;
        cursor: pointer;
        text-align: center;
    }
    input[type="radio"]:checked + label {
        background-color: #35353A;
        border-color: #35353A;
    }
    .radio-buttons label:hover {
        border-color: #004bad;
    }

</style>
