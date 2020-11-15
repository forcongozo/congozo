<template>
    <modal
        classes="v--modal modal-experience"
        name="experience-info"
        width="600px"
        height="auto"
        :adaptive="true"
        :scrollable="true"
        @opened="opened"
    >
        <div class="modal-content">
            <div class="form-group">
                <div class="participants-container">
                    <div>
                        <div class="icons-participants">
                            <p>{{this.experience.minParticipants}}</p>
                            <p>Min</p>
                        </div>
                        <div>
                            <h4>Participants</h4>
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
                                <h4>Experience Duration in Hours</h4>
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
                    <h4>Date & Time</h4>
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
                            placeholder="Enter time:"
                    />
                    <div
                            v-if="submitted && errors.has('time')"
                            class="alert-danger"
                    >{{errors.first('time')}}</div>
                </div>
            </div>
        </div>
        <div class="modal-actions">
            <button @click="save()">Save</button>
        </div>
    </modal>
</template>

<script>
    import DatePickComponent from "../DatePickComponent";

    export default {
        name: "ExperienceInfoModal",
        components: {
            DatePickComponent
        },
        data() {
            return {
                experience: {
                    minParticipants: 1,
                    maxParticipants: 5,
                    duration: 1,
                    date: '',
                    time: ''
                },
                participantsValue: [1,5],
                submitted: false
            }
        },
        methods: {
            opened () {
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

<style lang="scss">

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

    .modal-experience {
        overflow-y: auto;
    }

    .vm--modal {
        overflow: auto !important;
    }

    .modal-content {
        padding: 20px;
    }

    .modal-actions {
        padding: 20px 40px;

        button {
            color: #F0A002;
            background-color: transparent;
            border: solid 1px #F0A002;
            border-radius: 20px;
            width: 100%;
            margin: auto;
            height: 3rem;
            text-transform: uppercase;
        }
    }

    .form-group {
        width: 100%;
        margin: 0 auto;
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
</style>
