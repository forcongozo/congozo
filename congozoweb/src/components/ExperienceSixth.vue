<template>
    <div class="congozo-container congozo-bg-main">
        <div class="main-container">
            <div class="back-button">
                <button class="back-btn" @click="back"><i class="fas fa-arrow-left"/><span>Zurück</span></button>
            </div>
            <div class="form-group">
                <div class="description-container">
                    <h4>Schreiben Sie eine kurze Beschreibung Ihrer Erlebnis</h4>
                    <label for="description"/>
                    <textarea
                            v-model="experience.description"
                            type="text"
                            class="form-control"
                            name="description"
                            id="description"
                            placeholder="Beschreibe in kurzen Worten, was du bei deinem Erlebnis machen willst!
Ein Beispiel:
Begleite mich auf meinem Spaziergang mit meinen Hunden durch die Praterallee. Erlebe Natur pur, beobachte Rehe, schöne Schmetterlinge und andere Tiere, die in der Praterallee zuhause sind. Dazu erfährst du noch viel Interessantes über Hunde."
                            maxlength="400"
                            v-validate="'required'"
                    />
                    <span v-if="errors.has('description')">Beschreibung ist leer</span>
                </div>
            </div>
            <div class="form-group terms-conditions no-border">
                <div>
                    <input
                            v-model="experience.privacyPolicy"
                            class="form-control"
                            id="privacy-policy"
                            type="checkbox"
                            name="privacy-policy"
                            v-validate="'required'"
                    />
                    <label for="privacy-policy">
                        Ich stimme den <a href="#">Allgemeinen Geschäftsbedingungen</a> zu
                    </label>
                    <span v-if="errors.has('privacy-policy')">You have to agree to the Terms and Conditions</span>
                </div>
            </div>
            <div class="continue-button">
                <button class="congozo-btn-main btn-block" @click="submit">Erlebnis erstellen</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ExperienceSixth",
        props: {
            initExperience: {
                description: String,
                privacyPolicy: Boolean
            }
        },
        data() {
            return {
                experience: {
                    description: this.initExperience.description || '',
                    privacyPolicy: this.initExperience.privacyPolicy || false
                }
            }
        },
        methods: {
            back() {
                this.$emit('nextPage', 5);
            },
            submit() {
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.$emit('nextPage', 7);
                        this.$emit('experienceInfo', this.experience);
                    }
                    else {
                        this.handleValidationErrorAdvanced();
                    }
                });
            }
        }
    }
</script>

<style scoped lang="scss">
    @import '../style/congozo.css';

    .main-container {
        width: 100%;
        max-width: 600px;
        margin: auto;
        height: auto;
        padding: 3rem 2rem;

        .form-group {
            border-bottom: solid 1px #cdcdcd;

            .form-control {
                height: 3rem;
                max-width: 600px;
                margin: auto auto 1rem auto;
                border-radius: 20px;
            }
        }

        .description-container {
            margin-bottom: 2rem;

            h5 {
                color: white;
                background-color: rgba(0, 0, 0, 0.75);
                padding: 20px;
            }

            textarea {
                height: 250px !important;
                border-radius: 10px !important;
                padding: 20px;
            }
        }

        .terms-conditions {
            border-bottom: none;

            div {
                display: flex;
                padding: 0 20px;

                input {
                    max-width: 2rem !important;
                    margin: 0 !important;
                }
                label {
                    font-size: 1.05rem;
                    margin: auto auto auto 20px;
                }
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
