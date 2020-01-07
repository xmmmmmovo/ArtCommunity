<template>
    <div>
        <NavBar/>
        <div style="margin: 5%">
            <h1 style="margin: 2%; font-size: 60px">{{tempArticleData.articleTitle}}</h1>
            <h2 style="margin: 2%" @click="handleOtherProfile(tempArticleData.articleAuthor)">作者: {{tempArticleData.userName}}</h2>
            <h3 style="margin: 2%">时间: {{createTime(tempArticleData.createTime)}}</h3>
            <v-img
                    :src="tempArticleData.articleFront"
                    min-width="1024px"
                    min-height="768px"
                    max-height="1024px"
                    max-width="768px"
            ></v-img>
            <blockquote class="blockquote" style="margin-top: 5%; font-size: 30px">
                {{tempArticleData.articleContent}}
            </blockquote>
        </div>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {defaultArticleData, getArticleDetail} from "@/api/articles";
    import {parseTime} from "@/utils";

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

        private createTime(time: string | number) {
            return parseTime(time)
        }

        private handleOtherProfile(id: bigint) {
            this.$router.push({path: '/other-profile', query: {id: String(id)}})
        }

    }
</script>

<style scoped>
</style>
