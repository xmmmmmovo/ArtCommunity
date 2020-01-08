<template>
    <div>
        <NavBar/>

        <v-row>
            <v-col cols="8">
                <v-img
                        :src="tempArtData.artPicUrl"
                >
                </v-img>
            </v-col>

            <v-col cols="4">
                <h1>
                    {{tempArtData.artName}}
                </h1>
                <h1>
                    {{tempArtData.artName}}
                </h1>
                <h1>
                    {{tempArtData.artName}}
                </h1>
                <h1>
                    {{tempArtData.artName}}
                </h1>
            </v-col>
        </v-row>

        <v-container fluid>
            <v-textarea
                    v-model="tempCommentData.commentContent"
                    label="评论内容"
                    filled
                    auto-grow
            ></v-textarea>
            <v-btn large @click="handleCommit">
                提交
            </v-btn>
        </v-container>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {ICommentData} from "@/api/types";
    import {createArticle, getArticleDetail, getArticles} from "@/api/articles";
    import {parseTime} from "@/utils";
    import {defaultArtData, getArtDetail} from "@/api/arts";
    import {createComment, defaultCommentData, getByCommentsByArtId, getComments} from "@/api/comments";
    import {UserModule} from "@/store/modules/user";

    @Component({
        name: 'ArtContent',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        private tempArtData = defaultArtData
        private tempCommentData = defaultCommentData
        private commentList: ICommentData[] = []

        created() {
            this.init()
        }

        private async init() {
            await this.getTmpArt()
            await this.getComments()
        }

        private async getTmpArt() {
            this.tempArtData.id = BigInt(this.$route.query.id)
            let formData = new FormData()
            formData.append('id', this.tempArtData.id.toString())
            const {data} = await getArtDetail(formData)
            this.tempArtData = data
        }

        private async getComments() {
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            formData.append("id", this.tempArtData.id.toString())
            const {data} = await getByCommentsByArtId(formData)
            this.commentList = data.list
            console.log(this.commentList)
        }

        private createTime(time: string | number) {
            return parseTime(time)
        }

        private async handleCommit() {
            let formData = new FormData()
            formData.append('commentBy', UserModule.id.toString())
            formData.append('commentContent', this.tempCommentData.commentContent)
            formData.append('parentId', this.tempArtData.id.toString())
            let datas = await createComment(formData)
            datas = datas.data
            console.log(datas)
        }

    }
</script>

<style scoped>
</style>
