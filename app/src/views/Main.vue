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

        <h1 style="margin: 2%">这里写一个conponents</h1>
        <el-divider content-position="left">
            就在下面
        </el-divider>

        <h1 style="margin: 2%">精选文章</h1>
        <el-divider content-position="left">
            就在下面
        </el-divider>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {IArticleData} from "@/api/types";
    import {getArticles} from "@/api/articles";

    @Component({
        name: 'Main',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        private articles: IArticleData[] = []

        created() {
            this.getData()
        }

        private async getData() {
            let formData = new FormData()
            formData.append('size', String(3))
            const {data} = await getArticles(formData)
            this.articles = data.list
            console.log(this.articles)
        }

        private articleRoute(id: bigint) {
            console.log(id)
        }

    }
</script>

<style scoped>
</style>
