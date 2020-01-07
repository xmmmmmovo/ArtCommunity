<template>
    <div>
        <NavBar/>
        <h1></h1>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {defaultArticleData, getArticleDetail} from "@/api/articles";

    @Component({
        name: 'Main',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        private tempArticleData = defaultArticleData

        created() {
            this.init()
        }

        private async init() {
            this.tempArticleData.id = BigInt(this.$route.query.id)
            let formData = new FormData()
            formData.append('id', this.tempArticleData.id.toString())
            const { data } = await getArticleDetail(formData)
            this.tempArticleData = data
        }

    }
</script>

<style scoped>
</style>
