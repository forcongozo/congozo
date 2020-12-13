<template>
    <div class="congozo-container congozo-bg-main">
        <div class="main-container">
            <div class="back-button">
                <button class="back-btn" @click="back"><i class="fas fa-arrow-left"/><span>Zurück</span></button>
            </div>
            <div class="form-group">
                <div class="expenses-container">
                    <h4>Gibt es zusätzliche Kosten?</h4>
                    <div>
                        <div class="icons-expenses">
                            <div>
                                <i class="fas fa-coins"/>
                                <p>~ {{this.experience.expenses}} €</p>
                                <vue-slider v-model="experience.expenses" class="vue-slider"
                                            dotSize="30"
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
                                    placeholder="z.B. Busfahrschein, Eintrittspreis, ..."
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
                    <h4>Gibt es spezielle Anforderungen?</h4>
                    <div>
                        <div class="requirements-input-container">
                            <label for="requirement-items"/>
                            <input
                                    v-model="specRequirement"
                                    type="text"
                                    class="form-control"
                                    id="requirement-items"
                                    placeholder="z.B. rollstuhlfreundlich, taubstumm, älter als 18, ..."
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
                <button class="congozo-btn-main btn-block" @click="submit">Speichern und Weiter</button>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        name: "ExperienceThird.vue",
        components: {},
        props: {
            initExperience: {
                expenses: Number,
                expenseItems: Array,
                specialRequirements: Array
            }
        },
        data() {
            return {
                experience: {
                    expenses: this.initExperience.expenses || 0.0,
                    expenseItems: this.initExperience.expenseItems || [],
                    specialRequirements: this.initExperience.specialRequirements || []
                },
                expenseItem: '',
                specRequirement: ''
            };
        },
        methods: {
            back() {
                this.$emit('nextPage', 2);
            },
            submit() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.$emit('nextPage', 4);
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
            }
        }
    }
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';

    .vue-slider {
        margin: 20px auto;
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

        .error-message {
            margin: auto 20px 20px;
        }
    }

    .expenses-container, .requirements-container {
        margin-bottom: 2rem;

        h5 {
            color: white;
            background-color: rgba(0, 0, 0, 0.75);
            padding: 20px;
            margin-bottom: 2rem;
        }
    }

    .expenses-container > div {
        display: flex;
        justify-content: space-around;

        .icons-expenses {
            display: flex;

            div {
                width: 100%;
            }

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
