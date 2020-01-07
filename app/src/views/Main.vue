
<template>
    <div>
        <NavBar/>
        <div>
            <v-carousel>
                <v-carousel-item
                        v-for="(article, i) in articles"
                        :key="i"
                        :src="article.articleFron"
                >
                    <v-sheet
                            tile
                    >
                        <v-row
                                align="start"
                                justify="center"
                        >
                            <div class="display-3" v-model="article.articleTitle">
                            </div>
                        </v-row>
                    </v-sheet>
                </v-carousel-item>
            </v-carousel>
        </div>

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
            const { data } = await getArticles(formData)
            this.articles = data.list
            console.log(this.articles)
        }

    }
</script>

<style scoped>
</style>
