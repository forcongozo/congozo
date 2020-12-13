<template>
    <div class="congozo-container congozo-bg-main">
        <experience-first v-if="page === 1" @nextPage="submit" @experienceInfo="saveExperienceInfo" :init-experience="experience"/>
        <experience-second v-if="page === 2" @nextPage="submit" @experienceInfo="saveExperienceInfo" :init-experience="experience"/>
        <experience-third v-if="page === 3" @nextPage="submit" @experienceInfo="saveExperienceInfo" :init-experience="experience"/>
        <experience-fourth v-if="page === 4" @nextPage="submit" @experienceInfo="saveExperienceInfo" :init-experience="experience"/>
        <experience-fifth v-if="page === 5" @nextPage="submit" @experienceInfo="saveExperienceInfo" :init-experience="experience"/>
        <experience-sixth v-if="page === 6" @nextPage="submit" @experienceInfo="saveExperienceInfo" :init-experience="experience"/>
        <progress-bar class="progress-bar-custom" bar-color="#dc720f" :val="this.progressBarValue" :text="progressBarText" text-position="top" size="10"/>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex'

    import ExperienceFirst from "../components/ExperienceFirst";
    import ExperienceSecond from "../components/ExperienceSecond";
    import ExperienceThird from "../components/ExperienceThird";
    import ExperienceFourth from "../components/ExperienceFourth";
    import ExperienceFifth from "../components/ExperienceFifth";
    import ExperienceSixth from "../components/ExperienceSixth";

    export default {
        name: '',
        components: {
            ExperienceSixth,
            ExperienceFifth,
            ExperienceFourth,
            ExperienceFirst,
            ExperienceSecond,
            ExperienceThird
        },
        data() {
            return {
                page: 1,
                experience: {},
                progressBarValue: 16,
                progressBarText: "Schritt 1 von 6"
            };
        },
        computed: {
            ...mapGetters(['experienceData'])
        },
        created() {
        },
        methods: {
            submit(value) {
                if (value < 7){
                    this.page = value;
                    this.calculateProgressBarValue(value);
                } else {
                    this.createExperience();
                }
            },
            saveExperienceInfo(value) {
                Object.entries(value).forEach(entry => {
                    this.experience[entry[0]] = entry[1];
                });
                this.$store.dispatch('saveExperienceData', value);
            },
            async createExperience() {
                if (!!this.experience.myPersonalPhotos && this.experience.myPersonalPhotos.length !== 0) {
                    let imagesForm = new FormData();
                    this.experience.myPersonalPhotos.forEach(item => {
                        imagesForm.append('image', item, item.name)
                    });
                    this.experience.myPersonalPhotos = imagesForm
                }
                await this.$http.post('http://localhost:9090/api/public/newAdventure', this.experience)
                    .then(() => {
                        this.experience = {};
                        this.page = 3;
                    })
                    .catch(err => {
                        console.log(err)
                    })
            },
            calculateProgressBarValue(pageValue) {
                this.progressBarValue = 16.6 * (pageValue );
                this.progressBarText = "Schritt " + pageValue + " von 6";
            }
        },

    };
</script>

<style scoped lang="scss">

    .progress-bar-custom {
        // position: -webkit-sticky;
        // position: sticky;
        // bottom: 0;
    }
</style>
