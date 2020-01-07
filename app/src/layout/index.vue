<template>
    <div class="main-container">
        <nav-bar />
    </div>
</template>

<script lang="ts">
    import {NavBar} from "@/layout/components";
    import {Component} from "vue-property-decorator";
    import Vue from 'vue'

    @Component({
        name: 'Layout',
        components: {NavBar}
    })
    export default class App extends Vue{
    }
</script>

<style scoped>

</style>
