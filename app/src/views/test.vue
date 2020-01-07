<template>
    <div>
        <b-carousel
                id="carousel"
                :interval="4000"
                controls
                indicators
                background="#ababab"
                img-width="1024px"
                img-height="400px"
                style="text-shadow: 1px 1px 2px #333; margin: 3%;"
        >
            <b-carousel-slide
                    v-for="(article, i) in articles"
                    :key="i"
                    :caption="article.articleTitle"
                    :img-src="article.articleFront"
                    @click="articleRoute(article.id)"
            >
            </b-carousel-slide>
        </b-carousel>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {IArticleData} from "@/api/types";
    import {getArticles} from "@/api/articles";

    @Component({
        name: 'test',
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

        private articleRoute(id: bigint) {
            console.log(id)
        }

    }
</script>

<style scoped>
</style>
