<template>
    <date-pick
            v-model="date"
            :displayFormat="'DD.MM.YYYY'"
            :hasInputElement="false"
    />
</template>

<script>
    import DatePick from 'vue-date-pick';
    import '../style/date-picker/vueDatePick.scss';

    export default {
        components: {DatePick},
        data: () => ({
            date: '2019-01-01'
        }),
        watch: {
            date: function () {
                this.$emit('meetingDate', this.date)
            }
        },
        mounted() {
            let today = new Date();
            this.date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
        }
    };
</script>

<style scoped lang="scss">
    @import '../style/date-picker/vueDatePick.scss';

</style>
