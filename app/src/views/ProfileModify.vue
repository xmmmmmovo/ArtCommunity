<template>
    <div>
        <div>
            <NavBar/>
        </div>
        <v-row>
            <ProfileSide/>
            <v-col cols="8" style="margin-right: 8%">

                <h2>
                    基本信息
                </h2>



            </v-col>
        </v-row>
    </div>


</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import ProfileSide from "@/layout/components/ProfileSide.vue";
    import {UserModule} from "@/store/modules/user";
    import {parseTime} from "@/utils"
    import {IArtData, IArticleData, ILikeData} from "@/api/types";
    import {getArts, getUserArts} from "@/api/arts";
    import {getUserArticles} from "@/api/articles";
    import {getUserLikes} from "@/api/likes";

    @Component({
        name: 'ProfileModify',
        components: {
            NavBar,
            ProfileSide
        }
    })
    export default class App extends Vue {

        created() {
            this.init()
        }

        private async init() {

        }

        private createTime(time: string | number) {
            return parseTime(time)
        }
    }
</script>

<style scoped>
</style>
