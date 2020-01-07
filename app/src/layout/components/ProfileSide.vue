<template>
    <v-col cols="2" style="margin-left: 8%">
        <v-img
                :src="avatar"
                max-height="984px"
                max-width="484px">
        </v-img>
        <h1>
            {{name}}
        </h1>
        <h3>
            入驻社区时间<br>
        </h3>
        <h3>
            {{registerTime}}
        </h3>

        <v-btn x-large color="indigo" block outlined style="margin-top: 20%"
        @click="addArticleRoute">添加文章</v-btn>
        <v-btn x-large color="indigo" block outlined style="margin-top: 20%"
        @click="addArtRoute">添加画作</v-btn>
        <v-btn x-large color="indigo" block outlined style="margin-top: 20%"
        @click="changeProfileRoute">修改个人信息</v-btn>
    </v-col>
</template>

<script lang="ts">
    import Component from "vue-class-component";
    import Vue from "vue";
    import {UserModule} from "@/store/modules/user";
    import router from "@/router";
    import {parseTime} from "@/utils";

    @Component({
        name: 'ProfileSide'
    })
    export default class App extends Vue {

        get name() {
            return UserModule.name
        }

        get registerTime() {
            return parseTime(UserModule.registerTime)
        }

        get avatar() {
            return UserModule.avatar
        }

        private addArticleRoute() {
            this.$router.replace('/add-article')
        }

        private addArtRoute() {
            this.$router.replace('/add-art')
        }

        private changeProfileRoute() {

        }
    }
</script>

<style lang="scss" scoped>
</style>
