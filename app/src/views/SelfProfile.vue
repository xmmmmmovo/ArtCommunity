<template>
    <div>
        <div>
            <NavBar/>
        </div>
        <v-img
                src="http://q3lynq058.bkt.clouddn.com/profile/%E9%A1%B6%E9%83%A8.jpg"
        ></v-img>
        <v-row>
            <ProfileSide/>
            <v-col cols="8" style="margin-right: 8%">
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
                                            @click="handleArtContent(art.id)"
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
                                    <v-btn @click="handleUpdateArt(art.id)">
                                        修改作品
                                    </v-btn>
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
                                    <v-btn @click="handleUpdateArticle(article.id)">
                                        修改文章
                                    </v-btn>
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

                    <v-tab>点赞作品</v-tab>
                    <v-tab-item>
                        <div
                                v-for="(like, i) in likeList"
                                :key="i"
                                style="margin-top: 5%"
                        >
                            <v-row>
                                <v-col cols="6">
                                    <v-img
                                            :src="like.artPicUrl"
                                            max-height="984px"
                                            max-width="484px"
                                            @click="handleArtContent(like.artId)"
                                    >
                                    </v-img>
                                </v-col>
                                <v-col cols="6">
                                    <h2>
                                        标题: {{like.artName}}
                                    </h2>
                                    <h3>
                                        点赞时间: {{createTime(like.likeTime)}}
                                    </h3>
                                    <h4>
                                        分类: {{like.tagName}}
                                    </h4>
                                </v-col>
                            </v-row>
                        </div>

                    </v-tab-item>
                </v-tabs>

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
    import {parseTime} from "@/utils/index"
    import {IArtData, IArticleData, ILikeData} from "@/api/types";
    import {getArts, getUserArts} from "@/api/arts";
    import {getUserArticles} from "@/api/articles";
    import {getUserLikes} from "@/api/likes";

    @Component({
        name: 'SelfProfile',
        components: {
            NavBar,
            ProfileSide
        }
    })
    export default class App extends Vue {
        private artList: IArtData[] = []
        private articleList: IArticleData[] = []
        private likeList: ILikeData[] = []

        created() {
            this.getArtList()
            this.getArticleList()
            this.getLikeList()
        }

        private async getArtList() {
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            formData.append("id", UserModule.id.toString())
            const {data} = await getUserArts(formData)
            this.artList = data.list
        }

        private async getArticleList() {
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            formData.append("id", UserModule.id.toString())
            const {data} = await getUserArticles(formData)
            this.articleList = data.list
        }

        private async getLikeList() {
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            formData.append("id", UserModule.id.toString())
            const {data} = await getUserLikes(formData)
            this.likeList = data.list
            console.log(data.list)
            console.log(this.likeList)
        }

        private createTime(time: string | number) {
            return parseTime(time)
        }

        private handleUpdateArticle(id: bigint) {
            this.$router.push({path: '/update-article', query: {id: String(id)}})
        }

        private handleUpdateArt(id: bigint) {
            this.$router.push({path: '/update-art', query: {id: String(id)}})
        }

        private handleArticleContent(id: bigint) {
            this.$router.push({path: '/content-article', query: {id: String(id)}})
        }

        private handleArtContent(id: bigint) {
            this.$router.push({path: '/content-art', query: {id: String(id)}})
        }
    }
</script>

<style scoped>
</style>
