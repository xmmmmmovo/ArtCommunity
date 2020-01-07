<template>
    <div>
        <NavBar/>
    </div>
    <v-row>
        <ProfileSide/>
        <v-col>

        </v-col>
    </v-row>

</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import ProfileSide from "@/layout/components/ProfileSide.vue";

    @Component({
        name: 'AddArt',
        components: {
            NavBar,
            ProfileSide
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
