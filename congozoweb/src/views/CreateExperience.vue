<template>
    <div class="congozo-container congozo-bg-main">
        <experience-first v-if="page === 1" @nextPage="submit" @experienceInfo="saveExperienceInfo" :page="page"/>
        <experience-second v-if="page === 2" @nextPage="submit" @experienceInfo="saveExperienceInfo" />
        <experience-third v-if="page === 3"/>
    </div>
</template>

<script>
    import ExperienceFirst from "../components/ExperienceFirst";
    import ExperienceSecond from "../components/ExperienceSecond";
    import ExperienceThird from "../components/ExperienceThird";

    export default {
        name: '',
        components: {
            ExperienceFirst,
            ExperienceSecond,
            ExperienceThird
        },
        data() {
            return {
                page: 1,
                experience: {}
            };
        },
        computed: {
        },
        created() {
        },
        methods: {
            submit(value) {
                if (value < 3){
                    this.page = value
                } else {
                    this.createExperience();
                }
            },
            saveExperienceInfo(value) {
                Object.entries(value).forEach(entry => {
                    this.experience[entry[0]] = entry[1];
                });
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
            }
        }
    };
</script>

<style scoped lang="scss">

</style>
