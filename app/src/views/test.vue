<template>
    <div>
        <el-upload
                class="avatar-uploader"
                action="https://jsonplaceholder.typicode.com/posts/"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <el-upload
                class="avatar-uploader"
                action="https://upload-z1.qiniup.com"
                :show-file-list="false"
                :data="postData"
                :on-success="onCropUploadSuccess"
                :before-upload="beforeAvatarUpload">
            <img v-if="tempArticleData.articleFront" :src="tempArticleData.articleFront" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {IArticleData} from "@/api/types";
    import {defaultArticleData, getArticles} from "@/api/articles";

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
            const {data} = await getArticles(formData)
            this.articles = data.list
            console.log(this.articles)
        }

        private articleRoute(id: bigint) {
            console.log(id)
        }

        private tempArticleData = defaultArticleData

        private postData = {
            key: '',
            token: ''
        }
        private backUrl = 'http://q3lynq058.bkt.clouddn.com/'

        private onCropUploadSuccess(res: any, file: any) {
            this.tempArticleData.articleFront = this.backUrl + res.key
        }

        private beforeAvatarUpload(file: any) {
            this.postData.key = file.uid.toString() + file.name;
            const isJPG = file.type === "image/jpeg";
            const isPNG = file.type === "image/png";
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG && !isPNG) {
                this.$message.error("图片只能是 JPG/PNG 格式!");
                return false;
            }
            if (!isLt2M) {
                this.$message.error("图片大小不能超过 2MB!");
                return false;
            }
        }

    }
</script>

<style scoped>

    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>
