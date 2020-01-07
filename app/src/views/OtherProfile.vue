<template>
    <div>
        <NavBar/>
        <v-img
                src="http://q3lynq058.bkt.clouddn.com/%E9%A1%B6%E9%83%A82.jpg"
        ></v-img>
        <div style="margin: 5%">
            <v-img :src="tempUserData.userAvatar"
            max-width="300px"
            max-height="300px"
            style="margin: 2%"></v-img>
            <h1 style="margin: 5%">{{tempUserData.userName}}</h1>
            <h3>入驻社区时间: {{createTime(tempUserData.registerTime)}}</h3>
            <h4> 个人简介:</h4>
        </div>

        <v-tabs grow>
            <v-tab>作品</v-tab>
            <v-tab-item>
                <div
                        v-for="(art, i) in artList"
                        :key="i"
                        style="margin-top: 5%"
                >
                    <v-row>
                        <v-col cols="6">
                            <v-img
                                    :src="art.artPicUrl"
                                    max-height="984px"
                                    max-width="484px"
                            >
                            </v-img>
                        </v-col>
                        <v-col cols="6">
                            <h2>
                                标题: {{art.artName}}
                            </h2>
                            <h3>
                                创作时间: {{createTime(art.createTime)}}
                            </h3>
                            <h3>
                                点赞数: {{art.artLikeNum}}
                            </h3>
                            <h4>
                                尺寸: {{art.length}} * {{art.height}}
                            </h4>
                            <h4>
                                分类: {{art.tagName}}
                            </h4>
                        </v-col>
                    </v-row>
                </div>
            </v-tab-item>

            <v-tab>文章</v-tab>
            <v-tab-item>
                <div
                        v-for="(article, i) in articleList"
                        :key="i"
                        style="margin-top: 5%"
                >
                    <v-row>
                        <v-col cols="6">
                            <h2>
                                标题: {{article.articleTitle}}
                            </h2>
                            <h3>
                                创作时间: {{createTime(article.createTime)}}
                            </h3>
                        </v-col>
                        <v-col cols="6">
                            <v-img
                                    :src="article.articleFront"
                                    max-height="984px"
                                    max-width="484px"
                                    @click="handleArticleContent(article.id)"
                            >
                            </v-img>
                        </v-col>
                    </v-row>
                </div>
            </v-tab-item>
        </v-tabs>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {defaultArticleData, getArticleDetail, getUserArticles} from "@/api/articles";
    import {defaultUserData, getUserDetail} from "@/api/users";
    import {parseTime} from "@/utils";
    import {UserModule} from "@/store/modules/user";
    import {getUserArts} from "@/api/arts";
    import {IArtData, IArticleData} from "@/api/types";
    import da from "element-ui/src/locale/lang/da";

    @Component({
        name: 'OtherProfile',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        private artList: IArtData[] = []
        private articleList: IArticleData[] = []
        private tempUserData = defaultUserData

        created() {
            this.init()
            this.getArtList()
            this.getArticleList()
        }

        private async init() {
            this.tempUserData.id = BigInt(this.$route.query.id)
            let formData = new FormData()
            formData.append('id', this.tempUserData.id.toString())
            const { data } = await getUserDetail(formData)
            this.tempUserData = data
        }

        private async getArtList() {
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            formData.append("id", this.tempUserData.id.toString())
            const {data} = await getUserArts(formData)
            this.artList = data.list
        }

        private async getArticleList() {
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            formData.append("id", this.tempUserData.id.toString())
            const {data} = await getUserArticles(formData)
            this.articleList = data.list
        }

        private createTime(time: string | number) {
            return parseTime(time)
        }

        private handleArticleContent(id: bigint) {
            this.$router.push({path: '/content-article', query: {id: String(id)}})
        }

        private handleArtContent(id: bigint) {

        }
    }
</script>

<style scoped>
</style>
