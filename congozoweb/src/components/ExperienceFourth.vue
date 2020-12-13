<template>
    <div class="congozo-container congozo-bg-main">
        <div class="main-container">
            <div class="back-button">
                <button class="back-btn" @click="back"><i class="fas fa-arrow-left"/><span>Zurück</span></button>
            </div>
            <div class="form-group">
                <div class="participants-container">
                    <div>
                        <div class="icons-participants">
                            <p>{{this.experience.minParticipants}}</p>
                            <p>Min</p>
                        </div>
                        <div>
                            <h4>Teilnehmerzahl</h4>
                            <i class="fas fa-users"/>
                        </div>
                        <div class="icons-participants">
                            <p>{{this.experience.maxParticipants}}</p>
                            <p>Max</p>
                        </div>
                    </div>
                    <vue-slider v-model="participantsValue"
                                :enable-cross="false"
                                dotSize="30"
                                :min=1
                                :max=10
                                :minRange=1
                    />
                </div>
            </div>
            <div class="form-group">
                <div class="duration-container">
                    <div>
                        <div class="icons-duration">
                            <div>
                                <h4>Erlebnisdauer in Stunden</h4>
                                <p>{{this.experience.duration}} <span>h</span></p>
                                <vue-slider v-model="experience.duration" class="vue-slider"
                                            dotSize="30"
                                            :min=1
                                            :max=12
                                            :interval=0.5
                                />
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group no-border">
                <div class="date-container">
                    <h4>Datum & Uhrzeit</h4>
                    <date-pick-component @meetingDate="setDate" />
                    <div
                            v-if="submitted && errors.has('date')"
                            class="alert-danger"
                    >{{errors.first('date')}}</div>
                </div>
            </div>
            <div class="form-group no-border">
                <div class="time-container">
                    <label for="time"/>
                    <input
                            v-model="experience.time"
                            type="time"
                            class="form-control"
                            name="time"
                            id="time"
                            placeholder="Zeit eingeben:"
                    />
                    <div
                            v-if="submitted && errors.has('time')"
                            class="alert-danger"
                    >{{errors.first('time')}}</div>
                </div>
            </div>
            <div class="continue-button">
                <button class="congozo-btn-main btn-block" @click="submit">Speichern und Weiter</button>
            </div>
        </div>
    </div>
</template>

<script>
    import DatePickComponent from "./DatePickComponent";

    export default {
        name: "ExperienceFourth",
        components: {
            DatePickComponent
        },
        props: {
            initExperience: {
                minParticipants: Number,
                maxParticipants: Number,
                duration: Number,
                date: String,
                time: String
            }
        },
        data() {
            return {
                experience: {
                    minParticipants: this.initExperience.minParticipants || 1,
                    maxParticipants: this.initExperience.maxParticipants ||  5,
                    duration: this.initExperience.duration ||  1,
                    date: this.initExperience.date ||  '',
                    time: this.initExperience.time || ''
                },
                participantsValue: [this.initExperience.minParticipants || 1,this.initExperience.maxParticipants ||  5],
                submitted: false
            }
        },
        methods: {
            back() {
                this.$emit('nextPage', 3);
            },
            submit() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.$emit('nextPage', 5);
                        this.$emit('experienceInfo', this.experience);
                    }
                    else {
                        this.handleValidationErrorAdvanced();
                    }
                });
            },
            setDate(date) {
                this.experience.date = date;
            },
            save() {
                this.$store.dispatch('saveExperienceData', this.experience);
                this.$modal.hide('experience-info')
            }
        },
        watch: {
            'participantsValue': function () {
                this.experience.minParticipants = this.participantsValue[0];
                this.experience.maxParticipants = this.participantsValue[1];
            }
        }
    }
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';

    .vue-slider {
        margin: 20px auto 0;
        padding: 10px 5px !important;
        max-width: 500px;

        .vue-slider-process {
            background-color: #F0A002;
        }

        .vue-slider-dot-tooltip-inner {
            border-color: #F0A002;
            background-color: #F0A002;
        }
    }

    .vm--modal {
        overflow: auto !important;
    }
    .main-container {
        width: 100%;
        max-width: 600px;
        margin: auto;
        height: auto;
        padding: 3rem 2rem;

        .form-group {
            width: 100%;
            margin: 0 auto 1.5rem auto;
            border-bottom: solid 1px #cdcdcd;

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
        }

        .no-border {
            border: none;
        }

        .participants-container, .duration-container, .time-container {
            margin-bottom: 1.5rem;

            h5 {
                color: white;
                background-color: rgba(0, 0, 0, 0.75);
                padding: 20px;
                margin-bottom: 2rem;
            }
        }

        .time-container {
            margin-bottom: 0;
            padding: 0 20px;

            label {
                display: none;
            }

            input {
                margin: 0 !important;
                border: 1px solid #f0a002;
                border-radius: 20px;
            }

            input:before {
                content: attr(placeholder);
                margin-right: 0.5rem;
            }

            input[type="date"]:focus:before,
            input[type="date"]:valid:before {
                content: "";
            }
        }

        .participants-container > div, .duration-container > div {
            display: flex;
            justify-content: space-around;
            margin-bottom: 0;

            .icons-participants, .icons-duration {
                display: flex;
                flex-direction: column;
                width: 100%;

                .fa-plus-square, .fa-minus-square {
                    color: #F0A002;
                    font-size: 2rem;
                    margin: auto 10px;
                }

                button, button:focus {
                    border: none;
                    outline: 0;
                    background-color: transparent;
                }

                p {
                    font-size: 30px;
                    margin: 0 10px;
                    text-align: center;
                }

                h3 {
                    text-transform: none;
                    text-align: center;
                    color: black;
                }
            }

            .fa-users {
                display: block;
                margin: auto;
                color: #F0A002;
                font-size: 3rem !important;
            }
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
