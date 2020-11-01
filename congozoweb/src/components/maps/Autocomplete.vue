<template>
    <div class="autocomplete" v-show="this.items.length !== 0 && isOpen === true">
        <ul class="autocomplete-results">
            <li v-for="item in items"
                :key="item.lat"
                class="autocomplete-result-items"
                @click="setResult(item)"
            >
                {{ item.display_name }}
            </li>
        </ul>
    </div>
</template>
<script>
    export default {
        name: 'autocomplete',
        props: {
            items: Array
        },
        data() {
            return {
                isOpen: false
            }
        },
        methods: {
            setResult(result) {
                this.isOpen = false;
                this.$emit('selected', result)
            }
        },
        watch: {
            items: function () {
                this.isOpen = true;
            }
        }
    }
</script>

<style>
    .autocomplete {
        position: absolute;
        z-index: 1;
        background-color: white;
        max-width: 600px;
        border-radius: 20px;
        overflow: hidden;
        margin-left: 20px;
    }

    .autocomplete-results {
        padding: 0 10px;
        border: 1px solid rgba(0, 0, 0, 0.75);
        border-radius: 20px;
        max-height: 120px;
        overflow: auto;
        margin: 0;
    }

    .autocomplete-result-items {
        list-style: none;
        text-align: left;
        padding: 4px 2px;
        cursor: pointer;
        border-radius: 20px;
    }

    .autocomplete-result-items:hover {
        background-color: rgba(0, 0, 0, 0.75);
        color: white;
    }
</style>
