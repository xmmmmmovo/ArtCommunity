<template>
    <div>
        <NavBar/>
        <v-row>
            <ProfileSide/>
            <v-col cols="8" style="margin-right: 8%">
                <v-card>
                    写文章
                </v-card>
            </v-col>
        </v-row>
    </div>
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

</style>
