<template>
    <div>
        <NavBar/>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";

    @Component({
        name: 'AddArticle',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {

    }
</script>

<style scoped>
    .bk_content {
        /*background-image: url("../assets/login_bk.jpg");*/
    }
</style>
