<template>
    <div>
        <div>
            <NavBar/>
        </div>

        <h1 style="margin: 2%">艺术分类</h1>
        <el-divider content-position="left">
            就在下面
        </el-divider>
        <v-row justify="space-around" align="center">
            <v-col cols="4">
                <v-col align="center">
                    <v-img src="http://q3lynq058.bkt.clouddn.com/front/%E5%BD%93%E4%BB%A3.jpg"
                           max-height="984px" max-width="484px"></v-img>
                    <h2>当代艺术</h2>
                    <h3>当今的领先艺术家和新型人才</h3>
                </v-col>
                <v-col align="center">
                    <v-img src="http://q3lynq058.bkt.clouddn.com/front/%E6%88%98%E5%90%8E.jpg"
                           max-height="984px" max-width="484px"></v-img>
                    <h2>战后</h2>
                    <h3>从抽象表现主义到波普主义</h3>
                </v-col>
            </v-col>
            <v-col cols="4">
                <v-col align="center">
                    <v-img src="http://q3lynq058.bkt.clouddn.com/front/%E5%8D%B0%E8%B1%A1%E6%B4%BE.jpg"
                           max-height="984px" max-width="484px"></v-img>
                    <h2>印象派与现实</h2>
                    <h3>抽象、超现实的诞生</h3>
                </v-col>
                <v-col align="center">
                    <v-img src="http://q3lynq058.bkt.clouddn.com/front/20%E4%B8%96%E7%BA%AA.jpg"
                           max-height="984px" max-width="484px"></v-img>
                    <h2>20世纪前</h2>
                    <h3>古罗马、文艺复兴时期等等</h3>
                </v-col>
            </v-col>
            <v-col cols="4">
                <v-col align="center">
                    <v-img src="http://q3lynq058.bkt.clouddn.com/front/%E6%91%84%E5%BD%B1.png"
                           max-height="984px" max-width="484px"></v-img>
                    <h2>摄影</h2>
                    <h3>透过镜头——从刻板印象到数字</h3>
                </v-col>
                <v-col align="center">
                    <v-img src="http://q3lynq058.bkt.clouddn.com/front/%E8%A1%97%E5%A4%B4%E8%89%BA%E6%9C%AF.png"
                           max-height="984px" max-width="484px"></v-img>
                    <h2>街头艺术</h2>
                    <h3>涂鸦、黑玩具和滑板文化的兴起</h3>
                </v-col>
            </v-col>
        </v-row>

        <h1 style="margin: 2%">艺术作品</h1>
        <el-divider content-position="left">
            就在下面
        </el-divider>
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
                </v-col>
            </v-row>
        </div>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {IArticleData} from "@/api/types";
    import {getArticles} from "@/api/articles";
    import {getArts, getArtsAll} from "@/api/arts";
    import {parseTime} from "@/utils";

    @Component({
        name: 'Main',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        private artList: IArticleData[] = []

        created() {
            this.getData()
        }

        private async getData() {
            let formData = new FormData()
            const {data} = await getArts(formData)
            this.artList = data.list
            console.log(this.artList)
        }

        private articleRoute(id: bigint) {
            console.log(id)
        }

        private createTime(time: string | number) {
            return parseTime(time)
        }

        private handleArtContent(id: bigint) {
            this.$router.push({path: '/content-art', query: {id: String(id)}})
        }

    }
</script>

<style scoped>
</style>
