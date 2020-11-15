<template>
    <div class="congozo-container congozo-bg-main">
        <div class="image-container">
            <h3>Basic Experience Info</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio dolorem magni quam.</p>
        </div>
        <div class="main-container">
            <div class="form-group name">
                <label for="name">Give your experience a title*</label>
                <input
                        v-model="experience.experienceName"
                        type="text"
                        class="form-control"
                        name="name"
                        id="name"
                        placeholder="Enter title"
                        v-validate="'required'"
                />
                <span v-if="errors.has('name')" class="error-message">Experience title is empty</span>
            </div>
            <div class="modal-button">
                <button class="btn-block" @click="openModal">Select date and time</button>
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
            </div>
            <div class="form-group">
                <div class="expenses-container">
                    <h5>Are there any additional expenses?*</h5>
                    <div>
                        <div class="icons-expenses">
                            <div>
                                <i class="fas fa-coins"/>
                                <p>~ {{this.experience.expenses}} €</p>
                                <vue-slider v-model="experience.expenses" class="vue-slider"
                                            dotSize="20"
                                            width="300px"
                                            :min=0
                                            :max=24
                                            :interval=0.5
                                />
                            </div>
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
                        <div class="expenses-list-container" v-if="experience.expenseItems.length !== 0">
                            <ul>
                                <li v-for="expense in experience.expenseItems" :key="expense">
                                    <p>
                                        {{expense}}
                                        <button type="button" @click="removeExpenseItem(expense)">
                                            <i class="fas fa-minus-circle"/>
                                        </button>
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="requirements-container">
                    <h5>Are there any special requirements?*</h5>
                    <div>
                        <div class="requirements-input-container">
                            <label for="requirement-items"/>
                            <input
                                    v-model="specRequirement"
                                    type="text"
                                    class="form-control"
                                    id="requirement-items"
                                    placeholder="E.g. wheelchair friendly, deaf-mute"
                            />
                            <button type="button" @click="addSpecialRequirements">
                                <i class="fas fa-plus-circle"/>
                            </button>
                        </div>
                        <div class="requirements-list-container" v-if="experience.specialRequirements.length !== 0">
                            <ul>
                                <li v-for="requirement in experience.specialRequirements" :key="requirement">
                                    <p>
                                        {{requirement}}
                                        <button type="button" @click="removeRequirementItem(requirement)">
                                            <i class="fas fa-minus-circle"/>
                                        </button>
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="continue-button">
                <button class="congozo-btn-main btn-block" @click="submit">Save and Continue</button>
            </div>
        </div>
        <ExperienceInfoModal/>
    </div>
</template>

<script>
    import SmallMap from "./maps/SmallMap";
    import Autocomplete from "./maps/Autocomplete";
    import ExperienceInfoModal from "./modals/ExperienceInfoModal";

    export default {
        name: '',
        components: {
            SmallMap,
            Autocomplete,
            ExperienceInfoModal
        },
        props: {
            page: Number
        },
        data() {
            return {
                experience: {
                    experienceName: '',
                    fixedMeetingPoint: true,
                    meetingLocation: '',
                    language: [],
                    expenses: 0.0,
                    expenseItems: [],
                    specialRequirements: [],
                    privacyPolicy: false,
                },
                expenseItem: '',
                specRequirement: '',
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
                this.$validator.validate().then(valid => {
                    if (valid) {
                        console.log('no error');
                        this.$emit('nextPage', 2);
                        this.$emit('experienceInfo', this.experience);
                    }
                    else {
                        console.log('error');
                    }
                });
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
            addExpenseItems() {
                if (this.expenseItem !== ''){
                    this.experience.expenseItems.push(this.expenseItem);
                    this.expenseItem = '';
                }
            },
            removeExpenseItem(item) {
                this.experience.expenseItems.forEach( (elem, index) => {
                    if (elem.localeCompare(item) === 0) {
                        this.experience.expenseItems.splice(index,1);
                    }
                })
            },
            addSpecialRequirements() {
                if (this.specRequirement !== '') {
                    this.experience.specialRequirements.push(this.specRequirement);
                    this.specRequirement = '';
                }
            },
            removeRequirementItem(item) {
                this.experience.specialRequirements.forEach( (elem, index) => {
                    if (elem.localeCompare(item) === 0) {
                        this.experience.specialRequirements.splice(index,1);
                    }
                })
            },
            openModal() {
                this.$modal.show('experience-info')
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

    .vue-slider {
        margin: 20px 0;
    }

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

    .modal-button {
        padding: 30px 10px;

        button {
            color: #F0A002;
            background-color: transparent;
            border: solid 1px #F0A002;
            border-radius: 20px;
            max-width: 600px;
            margin: auto;
            height: 3rem;
            text-transform: uppercase;
        }
    }

    .form-group {
        border-bottom: none;
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

    .location-container, .expenses-container, .language, .requirements-container {
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
            width: 7rem;
            height: 7rem;
        }
        #label-german {
            background: url("../assets/images/germany.svg") transparent no-repeat;
            background-size: cover;
            margin: auto;
        }
        #label-english {
            background: url("../assets/images/united-kingdom.svg");
            background-size: cover;
            margin: auto;
        }
        input[type=checkbox]:checked + label {
            color: #fff;
            border: solid 1px  #F0A002;
            border-radius: 20px;
        }
    }

    .expenses-container > div {
        display: flex;
        justify-content: space-around;

        .icons-expenses {
            display: flex;

            .fa-plus-square, .fa-minus-square {
                color: #F0A002;
                font-size: 2rem;
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
                margin: 0 10px;
                text-align: center;
            }
        }

        .icons-expenses {
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .fa-coins {
            color: #F0A002;
            font-size: 3rem !important;
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
                        width: 100%;
                        max-width: 200px;
                        height: 230px;
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
            max-width: 600px;
            margin: auto auto 0 auto;
            border-radius: 20px;
            background-color: rgba(0, 0, 0, 0.75);
            color: white;
        }

        #location::placeholder {
            color: white;
        }
    }

    .expenses-container > div, .requirements-container > div{
        flex-direction: column;

        .expenses-input-container, .requirements-input-container {
            width: 90%;
            display: flex;
            margin: auto;

            #expense-items, #requirement-items {
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

        .expenses-list-container, .requirements-list-container {
            li {
                width: 80%;
                margin: auto;
            }
            p {
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                font-size: 20px;
                text-align: center;
                border-bottom: solid 2px #F0A002;
                padding: 10px 20px;
                margin: 10px;
                word-break: break-word;
            }

            button {
                height: 2rem;
                min-width: 4rem;
                border: none;
                outline: none;
                background-color: inherit;
                margin: auto 0;
            }

            button:focus {
                border: none;
            }

            .fa-minus-circle {
                color: #F0A002;
                font-size: 25px;
            }
        }
    }


    .continue-button {
        margin-top: 30px;
    }

    @media (max-width: 400px) {
        .participants-container > div {
            flex-direction: column;

            div {
                justify-content: center;

                svg {
                    display: block;
                    margin: 20px auto;
                }
            }
        }
    }

</style>
