<template>
    <div class="congozo-container congozo-bg-main">
        <div class="main-container">
            <div class="back-button">
                <button class="back-btn" @click="back"><i class="fas fa-arrow-left"/><span>Zurück</span></button>
            </div>
            <div class="form-group custom">
                <div class="location-container">
                    <h4>Wo willst du dich treffen?</h4>
                    <div class="location-container-radio">
                        <div class="location-radio">
                            <input
                                    v-model="experience.fixedMeetingPoint"
                                    value=true
                                    class="form-control"
                                    id="fixed-meeting"
                                    type="radio"
                                    name="fixed-meeting"
                            />
                            <label for="fixed-meeting">
                                <i class="fas fa-map-marked-alt"/>
                                <p>Fixed meeting point</p>
                            </label>
                        </div>
                        <div class="location-radio">
                            <input
                                    v-model="experience.fixedMeetingPoint"
                                    value=false
                                    class="form-control"
                                    id="after-booking"
                                    type="radio"
                                    name="after-booking"
                            />
                            <label for="after-booking">
                                <i class="far fa-comments"/>
                                <p>Agreed upon after booking</p>
                            </label>
                        </div>
                    </div>
                    <div v-if="this.showMap === 'true'">
                        <label for="location"/>
                        <input
                                v-model="experience.meetingLocation"
                                type="text"
                                class="form-control"
                                name="location"
                                id="location"
                                placeholder="Type the address here"
                                ref="locationInput"
                                v-validate="'required'"
                        />
                        <Autocomplete :items="this.addressSearchResults" @selected="showSelectResult" v-if="experience.meetingLocation !== ''"/>
                        <span v-if="errors.has('location')" class="error-message">Meeting location is empty</span>
                        <div class="small-map-container">
                            <small-map :search-location="this.addressSearchSelected" />
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
    import SmallMap from "./maps/SmallMap";
    import Autocomplete from "./maps/Autocomplete";

    export default {
        name: "ExperienceSecond.vue",
        components: {
            SmallMap,
            Autocomplete,
        },
        props: {
            initExperience: {
                fixedMeetingPoint: Boolean,
                meetingLocation: String
            }
        },
        data() {
            return {
                experience: {
                    fixedMeetingPoint: this.initExperience.fixedMeetingPoint || true,
                    meetingLocation: this.initExperience.meetingLocation || ''
                },
                addressSearchResults: [],
                addressSearchSelected: '',
                addressSearchSelectedFlag: false,
                showMap: "true"
            };
        },
        methods: {
            back() {
                this.$emit('nextPage', 1);
            },
            submit() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.$emit('nextPage', 3);
                        this.$emit('experienceInfo', this.experience);
                    }
                    else {
                        this.handleValidationErrorAdvanced();
                    }
                });
            },
            handleValidationErrorAdvanced () {
                const firstField = Object.keys(this.errors.collect())[0];

                this.$refs[`${firstField}Input`].scrollIntoView();
            },
            showSelectResult(result) {
                this.addressSearchSelected = result;
                this.experience.meetingLocation = this.addressSearchSelected.display_name;
                this.addressSearchSelectedFlag = true;
            },
            async search() {
                this.addressSearchSelectedFlag = false;
                const response = await this.$http.get(`https://eu1.locationiq.com/v1/search.php?key=pk.349f95f3dff13624c697cab4b5c5057f&q=${this.experience.meetingLocation}&countrycodes=ISO 3166-2:AT&format=json`);
                console.log(response.data);
                this.addressSearchResults = response.data;
            }
        },
        watch: {
            'experience.meetingLocation': function () {
                if (this.addressSearchSelectedFlag === false) {
                    this.search();
                } else {
                    this.addressSearchSelectedFlag = false;
                }
            },
            'experience.fixedMeetingPoint': function () {
                this.showMap = this.experience.fixedMeetingPoint;
            }
        }
    }
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';


    .main-container {
        width: 100%;
        max-width: 700px;
        margin: auto;
        height: auto;
        padding: 3rem 2rem;
    }

    .form-group {
        margin: 0 auto 1.5rem auto;
        border-bottom: solid 1px #cdcdcd;

        label {
            max-width: 600px;
            font-weight: 700;
            margin-left: 20px;
        }

        h4 {
            margin-bottom: 1.5rem;
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

        .small-map-container {
            width: 100%;
            height: 280px;
        }

        #location {
            margin-bottom: 0;
        }

        .error-message {
            margin: auto 20px 20px;
        }
    }

    .name {
        padding: 30px 10px;
        background-color: #F0A002;
        color: white;

        input {
            border-radius: 20px;
            margin-bottom: 0 !important;
        }
    }

    .location-container, .expenses-container, .requirements-container {
        margin-bottom: 2rem;

        h5 {
            color: white;
            background-color: rgba(0, 0, 0, 0.75);
            padding: 20px;
            margin-bottom: 2rem;
        }
    }

    .custom {
        div {
            label {
                font-size: 20px;
                line-height: 30px;
            }

            .location-container-radio {
                display: flex;
                justify-content: center;

                .location-radio {

                    label {
                        width: 100%;
                        max-width: 200px;
                        height: 180px;
                        border: solid 1px transparent;
                        border-radius: 20px;
                        margin: 0;
                        padding: 0 10px;
                    }

                    .fa-map-marked-alt, .fa-comments, .fa-angle-double-down {
                        font-size: 3rem;
                        margin: 20px auto;
                        display: block;
                    }

                    .fa-angle-double-down {
                        font-size: 50px;
                        margin:auto;
                    }

                    input:checked+label {
                        border: #f0a002 solid 3px;
                        color: #F0A002;
                    }

                    input {
                        display: none !important;
                    }

                    p {
                        text-align: center;
                    }
                }
            }
        }

        #location {
            height: 3.5rem !important;
            max-width: 600px;
            margin: auto auto 0 auto;
            border-radius: 20px;
        }

        #location::placeholder {

        }
    }

    .back-button {
        padding: 0 10px;
        height: 50px;

        .back-btn {
            float: right;
            padding: 5px;
            border: solid 1px grey;
            border-radius: 10px;

            svg, span {
                margin: auto 5px;
            }
        }
    }

    .continue-button {
        margin-top: 30px;
    }
</style>
