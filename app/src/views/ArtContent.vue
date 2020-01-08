<template>
    <div>
        <NavBar/>

        <v-row>
            <v-col cols="8">
                <v-img
                        :src="tempArtData.artPicUrl"
                        style="margin: 5%"
                        :max-height="tempArtData.height"
                        :max-width="tempArtData.length"
                >
                </v-img>

                <div align="center">
                    <v-img src="http://q3lynq058.bkt.clouddn.com/like%E7%BA%A2.png"
                           max-width="40px" max-height="40px" @click="handleLike"></v-img>
                </div>

            </v-col>

            <v-col cols="4">
                <h1 style="margin: 10%; font-size: 60px" @click="handleAuthorProfile(tempArtData.artAuthor)">
                    作者:{{tempArtData.userName}}
                </h1>
                <el-divider></el-divider>
                <h2 style="margin: 5%">
                    作品名:{{tempArtData.artName}}
                </h2>
                <h1 style="margin: 5%">
                    作品形容{{tempArtData.artContent}}
                </h1>
                <el-divider></el-divider>
                <h1>
                    尺寸:<br/>
                    高: {{tempArtData.height}} 长: {{tempArtData.length}}
                </h1>
            </v-col>
        </v-row>

        <div>
            <v-row
                    v-for="(comment, i) in commentList"
                    :key="i"
                    dense
            >
                <v-col cols="12">
                    <v-card
                    >
                        <v-card-title class="headline">{{comment.userName}} 于 {{createTime(comment.commentTime)}} 说到
                        </v-card-title>
                        <v-card-subtitle style="font-size: 30px">{{comment.commentContent}}</v-card-subtitle>
                    </v-card>
                </v-col>
            </v-row>
        </div>

        <v-container fluid
                     v-if="id!=''"
        >
            <v-textarea
                    v-model="content"
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
    import {defaultArtData, getArtDetail, updateArt, updateCommentCount, updateLikeCount} from "@/api/arts";
    import {createComment, defaultCommentData, getByCommentsByArtId, getComments} from "@/api/comments";
    import {UserModule} from "@/store/modules/user";
    import {cloneDeep} from 'lodash'
    import {Message} from "element-ui";
    import {createLike, getIsLikes} from "@/api/likes";

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
        private content = ''

        get id() {
            return UserModule.id
        }

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
            console.log(this.tempArtData)
            console.log(data)
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

        private handleAuthorProfile(id: bigint) {
            this.$router.push({path: '/other-profile', query: {id: String(id)}})
        }

        private async handleCommit() {
            let formData = new FormData()
            formData.append('commentBy', UserModule.id.toString())
            formData.append('commentContent', this.content)
            formData.append('parentId', this.tempArtData.id.toString())
            let datas = await createComment(formData)
            this.tempCommentData = datas.data
            this.commentList.push(this.tempCommentData)
            this.content = ''
            let formData2 = new FormData()
            formData2.append('id', this.tempArtData.id.toString())
            let dataBack = await updateCommentCount(formData2)
        }

        private async handleLike() {
            if (UserModule.token === '') {
                Message({
                    message: '请先登录！',
                    type: 'error',
                    duration: 5 * 1000,
                    offset: 100
                })
                return
            }

            let formData = new FormData()
            formData.append('userId', UserModule.id.toString())
            formData.append('artId', this.tempArtData.id.toString())
            const {data} = await getIsLikes(formData)

            console.log(data)

            if (data.len !== 0) {
                let formData2 = new FormData()
                let formData3 = new FormData()
                formData2.append('userId', UserModule.id.toString())
                formData2.append('artId', this.tempArtData.id.toString())
                formData3.append('id', this.tempArtData.id.toString())
                let d = await createLike(formData2)
                let d2 = await updateLikeCount(formData3)
                Message({
                    message: '成功喜欢',
                    type: 'success',
                    duration: 5 * 1000,
                    offset: 100
                })
            } else {
                Message({
                    message: '你已经喜欢过了！',
                    type: 'error',
                    duration: 5 * 1000,
                    offset: 100
                })
            }

        }

    }
</script>

<style scoped>
</style>
