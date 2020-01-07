<template>
    <div>
        <NavBar/>
        <v-row
                @click="handleArticleContent(article.id)"
                style="margin-left: 10%; margin-right: 10%; margin-top: 5%"
                v-for="(article, i) in articleList"
                :key="i">
            <v-col
                    cols="6"
            >
                <v-card width="640px" height="480px">
                    <v-card-title>{{article.articleTitle}}</v-card-title>
                    <v-card-subtitle class="pb-0">作者: {{article.userName}}</v-card-subtitle>
                    <v-card-text>时间: {{createTime(article.createTime)}}</v-card-text>
                    <span style="max-width: 640px;
                     max-height: 200px; overflow: hidden;
                      white-space: nowrap; text-overflow: ellipsis; margin: 10px">
                        {{article.articleContent}}</span>
                </v-card>
            </v-col>

            <v-col cols="6">
                <v-img :src="article.articleFront" max-width="640px" max-height="480px">
                </v-img>
            </v-col>
        </v-row>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {UserModule} from "@/store/modules/user";
    import {getArticles} from "@/api/articles";
    import {IArticleData} from "@/api/types";
    import {parseTime} from "@/utils";

    @Component({
        name: 'Main',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        private articleList: IArticleData[] = []

        created() {
            this.getArticleList()
        }


        private async getArticleList() {
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            const {data} = await getArticles(formData)
            this.articleList = data.list
        }

        private handleArticleContent(id: bigint) {
            this.$router.push({path: '/content-article', query: {id: String(id)}})
        }

        private createTime(time: string | number) {
            return parseTime(time)
        }
    }
</script>

<style scoped>
</style>
