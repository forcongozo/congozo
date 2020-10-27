<template>
    <div class="congozo-container congozo-bg-main">
        <div class="image-container">
            <h3>Basic Experience Info</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio dolorem magni quam.</p>
        </div>
        <div class="main-container">
            <div class="form-steps">
                <div class="step"><h6>Basics</h6></div>
                <div class="step"><h6>Description</h6></div>
                <div class="step"><h6>Photos</h6></div>
            </div>
            <div class="form-group name">
                <label for="name">Give your experience a title*</label>
                <input
                        v-model="experience.experienceName"
                        type="text"
                        class="form-control"
                        name="name"
                        id="name"
                        placeholder="Enter title"
                />
                <div
                        v-if="submitted && errors.has('name')"
                        class="alert-danger"
                >{{errors.first('name')}}</div>
            </div>
            <div class="form-group">
                <div class="participants-container">
                    <h5>Please set a number of participants</h5>
                    <div>
                        <div>
                            <h3>Min.</h3>
                            <div class="icons-participants">
                                <button @click="subMin()"><i class="fas fa-minus-square"/></button>
                                <p>{{this.experience.minParticipants}}</p>
                                <button @click="addMin()"><i class="fas fa-plus-square"/></button>
                            </div>
                        </div>
                        <div>
                            <i class="fas fa-users"/>
                        </div>
                        <div>
                            <h3>Max.</h3>
                            <div class="icons-participants">
                                <button @click="subMax()"><i class="fas fa-minus-square"/></button>
                                <p>{{this.experience.maxParticipants}}</p>
                                <button @click="addMax()"><i class="fas fa-plus-square"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="duration-container">
                    <h5>How long will it take approximately*</h5>
                    <div>
                        <div class="icons-duration">
                            <button @click="subDur()"><i class="fas fa-minus-square"/></button>
                            <div>
                                <p>{{this.experience.duration}}</p>
                                <p>Hours</p>
                            </div>
                            <button @click="addDur()"><i class="fas fa-plus-square"/></button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="date-container">
                    <h5>Please select the date below*</h5>
                    <date-pick-component @meetingDate="setDate" />
                    <div
                            v-if="submitted && errors.has('date')"
                            class="alert-danger"
                    >{{errors.first('date')}}</div>
                </div>
            </div>
            <div class="form-group">
                <div class="time-container">
                    <h5>What time does the adventure start?*</h5>
                    <label for="time"/>
                    <input
                            v-model="experience.time"
                            type="time"
                            class="form-control"
                            name="time"
                            id="time"
                            placeholder="Enter time"
                    />
                    <div
                            v-if="submitted && errors.has('time')"
                            class="alert-danger"
                    >{{errors.first('time')}}</div>
                </div>
            </div>
            <div class="form-group custom">
                <div class="location-container">
                    <h5>Where do you want to meet?*</h5>
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
                                <i class="fas fa-angle-double-down"/>
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
                        />
                        <Autocomplete :items="this.addressSearchResults" @selected="showSelectResult"/>
                        <div class="small-map-container">
                            <small-map :search-location="this.addressSearchSelected" />
                        </div>
                        <div
                                v-if="submitted && errors.has('location')"
                                class="alert-danger"
                        >{{errors.first('location')}}</div>
                    </div>
                </div>
            </div>
            <div class="form-group language">
                <h5>What language do you speak?*</h5>
                <div>
                    <div>
                        <input
                                v-model="experience.language"
                                value="german"
                                type="checkbox"
                                class="form-control"
                                name="language-german"
                                id="language-german"
                                placeholder="Enter language"
                        />
                        <label for="language-german" id="label-german"/>
                    </div>
                    <div>
                        <input
                                v-model="experience.language"
                                value="english"
                                type="checkbox"
                                class="form-control"
                                name="language-english"
                                id="language-english"
                                placeholder="Enter language"
                        />
                        <label for="language-english" id="label-english"/>
                    </div>
                </div>
                <div
                        v-if="submitted && errors.has('language')"
                        class="alert-danger"
                >{{errors.first('language')}}</div>
            </div>
            <div class="form-group">
                <div class="expenses-container">
                    <h5>Are there any additional expenses?*</h5>
                    <div>
                        <div class="icons-expenses">
                            <button @click="subExpenses()"><i class="fas fa-minus-square"/></button>
                            <div>
                                <i class="fas fa-coins"/>
                                <p>~ {{this.experience.expenses}} €</p>
                            </div>
                            <button @click="addExpenses()"><i class="fas fa-plus-square"/></button>
                        </div>
                        <div class="expenses-input-container">
                            <label for="expense-items"/>
                            <input
                                    v-model="expenseItem"
                                    type="text"
                                    class="form-control"
                                    id="expense-items"
                                    placeholder="E.g. bus ticket, cable-car"
                            />
                            <button type="button" @click="addExpenseItems">
                                <i class="fas fa-plus-circle"/>
                            </button>
                        </div>
                        <div class="expenses-list-container">
                            <ul>
                                <li v-for="expense in experience.expenseItems" :key="expense">
                                    <p>{{expense}}</p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group terms-conditions">
                <div>
                    <input
                            v-model="experience.privacyPolicy"
                            class="form-control"
                            id="privacy-policy"
                            type="checkbox"
                            name="privacy-policy"
                    />
                    <label for="privacy-policy">
                        I agree to the <a href="#">Terms and Conditions</a>
                    </label>
                </div>
            </div>
            <div class="continue-button">
                <button class="congozo-btn-main btn-block" @click="submit">Save and Continue</button>
            </div>
        </div>
    </div>
</template>

<script>
    import SmallMap from "./maps/SmallMap";
    import Autocomplete from "./maps/Autocomplete";
    import DatePickComponent from "./DatePickComponent";

    export default {
        name: '',
        components: {
            DatePickComponent,
            SmallMap,
            Autocomplete
        },
        props: {
            page: Number
        },
        data() {
            return {
                experience: {
                    experienceName: '',
                    minParticipants: 1,
                    maxParticipants: 1,
                    duration: 1,
                    date: '',
                    time: '',
                    fixedMeetingPoint: true,
                    meetingLocation: '',
                    language: [],
                    expenses: 0.0,
                    expenseItems: [],
                    specialRequirements: [],
                    privacyPolicy: false,
                },
                expenseItem: '',
                submitted: false,
                addressSearchResults: [],
                addressSearchSelected: '',
                addressSearchSelectedFlag: false,
                showMap: "true"
            };
        },
        computed: {
        },
        created() {
        },
        methods: {
            submit() {
                this.$emit('nextPage', 2);
                this.$emit('experienceInfo', this.experience);
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
            },
            subMin() {
                if (this.experience.minParticipants > 1) {
                    this.experience.minParticipants--;
                }
            },
            addMin() {
                if (this.experience.minParticipants < this.experience.maxParticipants) {
                    this.experience.minParticipants++;
                }
            },
            subMax() {
                if (this.experience.maxParticipants > this.experience.minParticipants) {
                    this.experience.maxParticipants--;
                }
            },
            addMax() {
                this.experience.maxParticipants++;
            },
            subDur() {
                if (this.experience.duration > 1) {
                    this.experience.duration = this.experience.duration - 0.5;
                }
            },
            addDur() {
                this.experience.duration = this.experience.duration + 0.5;
            },
            subExpenses() {
                if (this.experience.expenses > 0) {
                    this.experience.expenses = this.experience.expenses - 0.5;
                }
            },
            addExpenses() {
                this.experience.expenses = this.experience.expenses + 0.5;
            },
            addExpenseItems() {
                this.experience.expenseItems.push(this.expenseItem);
                this.expenseItem = '';
            },
            setDate(date) {
                this.experience.date = date;
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
    };
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';

    .image-container {
        height: 300px;
        padding: 3rem 2rem;
        background: linear-gradient(rgba(255,255,255,.3), rgba(255,255,255,.3)), url("../assets/images/galen-crout-fItRJ7AHak8-unsplash.jpg");
        background-size: cover;
        background-position: center;

        h3, p {
            color: black;
            text-align: center;
            max-width: 600px;
            margin: auto;
        }
    }

    .main-container {
        width: 100%;
        max-width: 700px;
        margin: auto;
        height: auto;
        padding: 3rem 2rem;
    }

    .form-steps {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;

        .step {
            width: 33%;

            h6 {
                height: 40px;
                text-align: center;
                padding-top: 10px;
                color: white;
                background-color: rgba(0, 0, 0, 0.75);
                -webkit-clip-path: polygon(0% 0%, 90% 0%, 100% 50%, 100% 50%, 90% 100%, 10% 100%, 0% 100%, 10% 50%);
                clip-path: polygon(0% 0%, 90% 0%, 100% 50%, 100% 50%, 90% 100%, 10% 100%, 0% 100%, 10% 50%);
            }
        }

        .step:first-child {
            h6 {
                background-color: rgba(235, 0, 0, 0.75);
                clip-path: polygon(0% 0%, 90% 0%, 100% 50%, 100% 50%, 90% 100%, 0% 100%, 0% 0%, 0% 0%);
            }
        }
    }

    .form-group {
        margin: 0 auto;

        label {
            max-width: 600px;
            font-weight: 700;
            margin-left: 20px;
        }

        .form-control {
            height: 3rem;
            max-width: 600px;
            margin: auto auto 1rem auto;
        }

        .congozo-btn-main {
            margin-top: 3rem;
        }

        .small-map-container {
            width: 100%;
            height: 300px;
        }

        #location {
            margin-bottom: 0;
        }
    }

    .name {
        height: 150px;
        padding: 30px 0;
        background-color: #F0A002;
        color: white;

        input {
            border-radius: 20px;
        }
    }

    .participants-container, .duration-container, .date-container, .time-container, .location-container, .expenses-container, .language {
        margin-bottom: 2rem;

        h5 {
            color: white;
            background-color: rgba(0, 0, 0, 0.75);
            padding: 20px;
            margin-bottom: 2rem;
        }
    }

    .language {
        margin-top: 40px;

        div {
            display: flex;
            justify-content: space-around;
        }

        input[type=checkbox] {
            display: none;
        }
        label {
            cursor: pointer;
            display: inline-block;
            padding: 3px;
            width: 150px;
            height: 150px;
        }
        #label-german {
            background: url("../assets/images/germany.svg") transparent no-repeat;
            background-size: cover;
        }
        #label-english {
            background: url("../assets/images/united-kingdom.svg");
            background-size: cover;
        }
        input[type=checkbox]:checked + label {
            color: #fff;
            border: solid 1px  #F0A002;
            border-radius: 20px;
        }
    }

    .participants-container > div, .duration-container > div, .expenses-container > div {
        display: flex;
        justify-content: space-around;

        .icons-participants, .icons-duration, .icons-expenses {
            display: flex;

            .fa-plus-square, .fa-minus-square {
                color: #F0A002;
                font-size: 40px;
                margin: auto 10px;
            }

            .fa-coins {
                font-size: 100px;
                margin: auto;
                display: block;
            }

            button, button:focus {
                border: none;
                outline: 0;
                background-color: #F4F2F0;
            }

            p {
                font-size: 40px;
                margin: 0 20px;
                text-align: center;
            }
        }

        .icons-expenses {
            width: 100%;
            display: flex;
            justify-content: space-around;
        }

        .fa-users, .fa-coins {
            color: #F0A002;
            font-size: 60px;
        }

        h3 {
            text-transform: none;
            text-align: center;
            color: black;
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
                        width: 200px;
                        height: 250px;
                        border: solid 1px transparent;
                        border-radius: 20px;
                    }

                    .fa-map-marked-alt, .fa-comments, .fa-angle-double-down {
                        font-size: 80px;
                        margin: 30px auto;
                        display: block;
                    }

                    .fa-angle-double-down {
                        font-size: 50px;
                        margin:auto;
                    }

                    input:checked+label {
                        border-color: #f0a002;
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
            width: 600px;
            margin: auto auto 0 auto;
            border-radius: 20px;
            background-color: rgba(0, 0, 0, 0.75);
            color: white;
        }

        #location::placeholder {
            color: white;
        }
    }

    .expenses-container > div{
        flex-direction: column;

        .expenses-input-container {
            width: 600px;
            display: flex;
            margin: auto;

            #expense-items {
                width: 100%;
                border-top-right-radius: 0 !important;
                border-bottom-right-radius: 0 !important;
            }

            button {
                height: 3rem;
                width: 4rem;
                border-top-right-radius: 20px;
                border-bottom-right-radius: 20px;
                border: 1px solid #ced4da;
                outline: none;
            }

            button:focus {
                border: none;
            }

            .fa-plus-circle {
                color: #F0A002;
                font-size: 25px;
            }

            label {
                display: none;
            }
        }

        .expenses-list-container {
            li {
                width: 80%;
                margin: auto;
            }
            p {
                font-size: 20px;
                text-align: center;
                border-bottom: solid 2px #F0A002;
                padding: 5px;
                margin: 10px;
                word-break: break-word;
            }
        }
    }

    .terms-conditions {
        div {
            display: flex;

            input {
                max-width: 50px !important;
                margin: 0 !important;
            }
            label {
                font-size: 25px;
            }
        }
    }

    .continue-button {
        margin-top: 30px;
    }

</style>
