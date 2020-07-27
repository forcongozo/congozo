<template>
    <div class="congozo-container congozo-bg-main">
        <div class="main-container">
            <div class="form-container">
                <div>
                    <h2 id="welcome-text">Welcome to the Community!</h2>
                </div>
                <div class="change-form">
                    <div class="radio-buttons">
                        <input
                                type="radio"
                                id="searching-form"
                                name="searching"
                                value="true"
                                v-model="isSearching"
                        />
                        <label for="searching-form">IM LOOKING FOR</label>
                        <input
                                type="radio"
                                id="offering-form"
                                name="offering"
                                value="false"
                                v-model="isSearching"
                        />
                        <label for="offering-form">IM OFFERING</label>
                    </div>
                </div>
                <div class="searching-form-container" v-if="isSearching === 'true'">
                    <form name="form" @submit.prevent="handleSearching">
                        <div class="form-input-container" v-if="!successful">
                            <div class="form-group">
                                <label for="offer"/>
                                <input
                                        v-model="offer"
                                        type="text"
                                        class="form-control"
                                        name="offer"
                                        id="offer"
                                        placeholder="Hiking"
                                />
                                <div
                                        v-if="submitted && errors.has('offer')"
                                        class="alert-danger"
                                >{{errors.first('offer')}}</div>
                            </div>
                            <div class="form-group">
                                <label for="city"/>
                                <input
                                        v-model="city"
                                        type="text"
                                        class="form-control"
                                        name="city"
                                        id="city"
                                        placeholder="Wien"
                                />
                                <div
                                        v-if="submitted && errors.has('city')"
                                        class="alert-danger"
                                >{{errors.first('city')}}</div>
                            </div>
                            <div class="form-group">
                                <button class="congozo-btn-main btn-block">Let's Explore!</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div>
                    <h4 id="motto-text">We Unite People Around the World to Share Local Experience.</h4>
                </div>
            </div>
        </div>
        <div class="text-container">
            <div class="become-guide">
                <div class="become-image">
                    <fieldset>
                        <legend>
                            <img src="../assets/images/guilherme-stecanella-unsplash.jpg" alt="portrait image">
                        </legend>
                    </fieldset>
                </div>
                <div class="become-text">
                    <h3>Become a guide</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur eos facere fuga magnam nesciunt nobis optio quia? Aspernatur aut, dolore eos facilis, in maxime modi mollitia neque nisi reiciendis soluta.</p>
                </div>
                <div class="become-button">
                    <button class="congozo-btn-main btn-block">Start Sharing</button>
                </div>
            </div>
            <div class="become-guest">
                <div class="become-image">
                    <fieldset>
                        <legend>
                            <img src="../assets/images/guilherme-stecanella-unsplash.jpg" alt="portrait image">
                        </legend>
                    </fieldset>
                </div>
                <div class="become-text">
                    <h3>Be a guest</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur eos facere fuga magnam nesciunt nobis optio quia? Aspernatur aut, dolore eos facilis, in maxime modi mollitia neque nisi reiciendis soluta.</p>
                </div>
                <div class="become-button">
                    <button class="congozo-btn-main btn-block">Start Exploring</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import UserService from '../services/user.service';

    export default {
        name: 'Home',
        data() {
            return {
                offer: '',
                city: '',
                isSearching: 'true',
                submitted: false,
                successful: false,
                message: '',
                content: ''
            };
        },
        mounted() {
            UserService.getPublicContent().then(
                response => {
                    this.content = response.data;
                },
                error => {
                    this.content =
                        (error.response && error.response.data) ||
                        error.message ||
                        error.toString();
                }
            );
        }
    };
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';

    .main-container {
        width: 100%;
        height: auto;
        padding: 5rem 2rem;
        background: linear-gradient(rgba(255,255,255,.3), rgba(255,255,255,.3)), url("../assets/images/eddy-billard-42nzwGteFgI-unsplash.jpg");
        background-size: cover;
        background-position: center;

        #welcome-text {
            font-weight: 400;
            text-align: center;
            color: black;
            margin-bottom: 4rem;
        }

        #motto-text {
            margin-top: 5rem;
            text-align: center;
            color: black;
        }

        .form-container {
            margin: auto;
            max-width: 600px;
            background: transparent;

            .form-group {
                margin: 0 auto;

                label {
                    display: none;
                }

                .form-control {
                    height: 3rem;
                    margin-bottom: 1rem;
                    border-radius: 0;
                }

                .congozo-btn-main {
                    margin-top: 3rem;
                }
            }
        }
    }

    .text-container {
        width: 100%;
        height: auto;
        padding: 5rem 2rem;

        .become-guide, .become-guest {
            margin: auto auto 4rem auto;
            max-width: 600px;

            .become-image {
                img {
                    width: 130px;
                    height: 130px;
                    display: block;
                    margin-left: -65px;
                    object-fit:cover;
                    border-radius:50%;
                }

                fieldset {
                    border-top: 5px solid #F0A002;

                    legend {
                        padding: 3rem 3rem 0 3rem;
                        margin: auto;
                        width: 30px;
                    }
                }
            }

            .become-text {
                margin: 2rem;
                text-align: center;
            }
        }
    }

    /* Radio Buttons */
    .radio-buttons {
        text-align: center;

        input[type="radio"] {
            opacity: 0;
            position: fixed;
            width: 0;
        }

        input[type="radio"]:checked + label {
            background-color: #F0A002;
            border: 1px solid #F0A002;
        }

        label {
            width: 50%;
            margin-bottom: 0;
            display: inline-block;
            background-color: #35353A;
            color: white;
            padding: 10px 10px;
            font-family: sans-serif, Arial;
            font-size: 16px;
            border: 1px solid #35353A;
            cursor: pointer;
            text-align: center;
        }

        label:hover {
            border-color: #004bad;
        }
    }
</style>
