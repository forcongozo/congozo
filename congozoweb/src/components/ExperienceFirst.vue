<template>
    <div class="congozo-container congozo-bg-main">
        <div class="main-container">
            <div class="form-group name">
                <h4>Geben Sie Ihrer Erlebnis einen Titel</h4>
                <label for="name"/>
                <input
                        v-model="experience.experienceName"
                        type="text"
                        class="form-control"
                        name="name"
                        id="name"
                        placeholder="Titel eingeben"
                        ref="nameInput"
                        v-validate="'required'"
                />
                <span v-if="errors.has('name')" class="error-message">Title ist leer!</span>
            </div>
            <div class="form-group categories">
                <h4>Wählen Sie die Kategorien aus</h4>
                <div class="icons">
                    <div class="first-row-icons">
                        <div>
                            <input
                                    v-model="experience.categories"
                                    value="sport"
                                    type="checkbox"
                                    class="form-control"
                                    name="sport"
                                    id="category-sport"
                                    placeholder="select category"
                            />
                            <label for="category-sport" id="label-sport"/>
                            <h5>Sport</h5>
                        </div>
                        <div>
                            <input
                                    v-model="experience.categories"
                                    value="kulinarik"
                                    type="checkbox"
                                    class="form-control"
                                    name="kulinarik"
                                    id="category-kulinarik"
                                    placeholder="select category"
                            />
                            <label for="category-kulinarik" id="label-kulinarik"/>
                            <h5>Kulinarik</h5>
                        </div>
                        <div>
                            <input
                                    v-model="experience.categories"
                                    value="kultur"
                                    type="checkbox"
                                    class="form-control"
                                    name="kultur"
                                    id="category-kultur"
                                    placeholder="select category"
                            />
                            <label for="category-kultur" id="label-kultur"/>
                            <h5>Kultur</h5>
                        </div>
                    </div>
                    <div class="second-row-icons">
                        <div>
                            <input
                                    v-model="experience.categories"
                                    value="outdoor"
                                    type="checkbox"
                                    class="form-control"
                                    name="outdoor"
                                    id="category-outdoor"
                                    placeholder="select category"
                            />
                            <label for="category-outdoor" id="label-outdoor"/>
                            <h5>Outdoor</h5>
                        </div>
                        <div>
                            <input
                                    v-model="experience.categories"
                                    value="kreativ"
                                    type="checkbox"
                                    class="form-control"
                                    name="kreativ"
                                    id="category-kreativ"
                                    placeholder="select category"
                            />
                            <label for="category-kreativ" id="label-kreativ"/>
                            <h5>Kreativ</h5>
                        </div>
                        <div>
                            <input
                                    v-model="experience.categories"
                                    value="sonstiges"
                                    type="checkbox"
                                    class="form-control"
                                    name="sonstiges"
                                    id="category-sonstiges"
                                    placeholder="select category"
                            />
                            <label for="category-sonstiges" id="label-sonstiges"/>
                            <h5>Anderes</h5>
                        </div>
                    </div>
                </div>
            </div>
            <div class="continue-button">
                <button class="congozo-btn-main btn-block" @click="submit">Speichern und Weiter</button>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        name: '',
        components: {},
        props: {
            initExperience: {
                experienceName: String,
                categories: Array
            }
        },
        data() {
            return {
                experience: {
                    experienceName: this.initExperience.experienceName || '',
                    categories: this.initExperience.categories || []
                }
            };
        },
        methods: {
            submit() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.$emit('nextPage', 2);
                        this.$emit('experienceInfo', this.experience);
                    }
                    else {
                        this.handleValidationErrorAdvanced();
                    }
                });
            }
        }
    };
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';

    .main-container {
        height: 100%;
        width: 100%;
        max-width: 700px;
        margin: auto;
        padding: 2rem 2rem 1rem;
    }

    .form-group {
        margin: 0 auto 1.5rem auto;
        border-bottom: solid 1px #cdcdcd;

        h4 {
            margin-bottom: 20px;
        }

        .form-control {
            height: 3rem;
            max-width: 600px;
            margin: auto auto auto auto !important;
            border-radius: 20px;
        }

        .congozo-btn-main {
            margin-top: 3rem;
        }

        .error-message {
            margin: auto 20px 20px;
        }
    }

    .name {
        padding: 30px 10px;

        label {
            display: none;
        }

        input {
            border-radius: 20px;
            margin-bottom: 0 !important;
        }
    }

    .categories {
        padding: 0 10px 30px;

        .icons {
            .first-row-icons, .second-row-icons {
                display: flex;
                justify-content: space-around;
                padding: 20px 0;

                div {
                    // border: #F0A002 solid 1px;
                    border-radius: 20px;
                    //height: 80px;
                    //width: 80px;
                }

                svg {
                    display: block;
                    margin: auto;
                    font-size: 50px;
                    cursor: pointer;
                }
            }
        }
    }

    //////
    .categories {
        margin-top: 40px;
        .icons {
            display: flex;
            flex-direction: column;
            justify-content: space-around;
        }
        input[type=checkbox] {
            display: none;
        }
        label {
            cursor: pointer;
            display: inline-block;
            padding: 3px;
            width: 6rem;
            height: 6rem;
        }
        h5 {
            text-align: center;
        }
        #label-outdoor {
            background: url("../assets/categories/cloud-sun-solid.png") transparent no-repeat;
            background-size: cover;
            margin: auto;
            background-origin: content-box;
            padding: 5px;
        }
        #label-kultur {
            background: url("../assets/categories/guitar-solid.png") transparent no-repeat;
            background-size: cover;
            margin: auto;
            background-origin: content-box;
            padding: 5px;
        }
        #label-sonstiges {
            background: url("../assets/categories/umbrella-beach-solid.png") transparent no-repeat;
            background-size: cover;
            margin: auto;
            background-origin: content-box;
            padding: 5px;
        }
        #label-kulinarik {
            background: url("../assets/categories/utensils-solid.png") transparent no-repeat;
            background-size: cover;
            margin: auto;
            background-origin: content-box;
            padding: 5px;
        }
        #label-kreativ {
            background: url("../assets/categories/campground-solid.png") transparent no-repeat;
            background-size: cover;
            margin: auto;
            background-origin: content-box;
            padding: 5px;
        }
        #label-sport {
            background: url("../assets/categories/biking-solid.png") transparent no-repeat;
            background-size: cover;
            margin: auto;
            background-origin: content-box;
            padding: 5px;
        }
        input[type=checkbox]:checked + label {
            color: #fff;
            border: solid 3px  #F0A002;
            border-radius: 20px;
        }
    }
    //////

    .continue-button {
        margin: 30px auto;
        // position: absolute;
        // bottom: 2rem;
    }

</style>
