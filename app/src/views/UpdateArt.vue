<template>
    <div>
        <NavBar/>
        <v-row>
            <ProfileSide/>
            <v-col cols="8" style="margin-right: 8%; margin-top: 5%">
                <v-card align="center" style="margin-bottom: 5%">
                    <h2 style="padding: 5%">
                        发布作品
                    </h2>
                </v-card>
                <v-card>
                    <h2 align="center">
                        作品内容
                    </h2>
                    <el-divider></el-divider>
                    <v-form v-model="valid">
                        <h3>
                            作品标题
                        </h3>
                        <v-text-field
                                v-model="tempArtData.artName"
                                style="margin: 5%"
                                label="标题"
                                name="articleTitle"
                                :rules="titleRules"
                                filled
                        ></v-text-field>
                        <h3>
                            作品介绍
                        </h3>
                        <v-textarea
                                v-model="tempArtData.artContent"
                                style="margin: 5%"
                                name="articleContent"
                                filled
                                label="内容"
                                :rules="contentRules"
                                auto-grow
                        >
                        </v-textarea>

                        <h3>
                            作品尺寸
                        </h3>
                        <el-select
                                v-model="tempArtData.artSize"
                                class="filter-item"
                                :placeholder="'长度' + sizeList[0].length + '宽度' +sizeList[0].height"

                        >
                            <el-option
                                    v-for="item in sizeList"
                                    :key="item.id"
                                    :label="'长度' + item.length + '宽度' +item.height"
                                    :value="item.id"
                            />
                        </el-select>
                        <h3>
                            作品
                        </h3>
                        <el-select
                                v-model="tempArtData.artTag"
                                class="filter-item"
                                :placeholder="tagList[0].tagName"
                        >
                            <el-option
                                    v-for="item in tagList"
                                    :key="item.id"
                                    :label="item.tagName"
                                    :value="item.id"
                            />
                        </el-select>

                    </v-form>
                </v-card>

                <el-card>
                    <h2 align="center">
                        作品主体
                    </h2>
                    <el-divider></el-divider>
                    <el-upload
                            align="center"
                            class="avatar-uploader"
                            action="https://upload-z1.qiniup.com"
                            :show-file-list="false"
                            :data="postData"
                            :on-success="onCropUploadSuccess"
                            :before-upload="beforeAvatarUpload">
                        <img v-if="tempArtData.artPicUrl" :src="tempArtData.artPicUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-card>

                <el-card style="margin-bottom: 10%; margin-top: 5%">
                    <v-btn large min-height="100px" min-width="200px" @click="handlePublish" :disabled="!valid">
                        发布
                    </v-btn>
                </el-card>
            </v-col>
        </v-row>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import ProfileSide from "@/layout/components/ProfileSide.vue";
    import {createArticle, defaultArticleData, getArticleDetail} from "@/api/articles";
    import {getToken} from "@/api/token";
    import {isValidateEmail, isValidatePassword} from "@/utils/validate";
    import {UserModule} from "@/store/modules/user";
    import {
        getArts,
        createArt,
        getArtDetail,
        updateArt,
        deleteArt,
        defaultArtData,
    } from '@/api/arts'
    import {getUsersAll} from "@/api/users";
    import {getSizesAll} from "@/api/sizes";
    import {getTagsAll} from "@/api/tags";
    import {IArtData, ISizeData, ITagData} from "@/api/types";
    import { cloneDeep } from 'lodash'


    @Component({
        name: 'UpdateArt',
        components: {
            NavBar,
            ProfileSide
        }
    })
    export default class App extends Vue {
        private tagList: ITagData[] = []
        private sizeList: ISizeData[] = []
        private tempArtData = defaultArtData
        private valid = true

        private postData = {
            key: '',
            token: ''
        }
        private backUrl = 'http://q3lynq058.bkt.clouddn.com/'

        private titleRules = [
            (v: any) => !!v || '请输入邮箱!'
        ]
        private contentRules = [
            (v: any) => !!v || '请输入密码!'
        ]

        created() {
            this.genToken()
            this.getNeedData()
        }

        private async getNeedData() {
            this.tempArtData.id = BigInt(this.$route.query.id)
            let formData = new FormData()
            formData.append('id', this.tempArtData.id.toString())
            const { data } = await getArtDetail(formData)
            this.tempArtData = data

            let data1 = await getSizesAll()
            this.sizeList = data1.data

            data1 = await getTagsAll()
            this.tagList = data1.data
        }

        private async genToken() {
            let data = await getToken()
            this.postData.token = data.data
        }

        private onCropUploadSuccess(res: any, file: any) {
            this.tempArtData.artPicUrl = this.backUrl + res.key
        }

        private beforeAvatarUpload(file: any) {
            this.postData.key = file.uid.toString() + file.name;
            const isJPG = file.type === "image/jpeg";
            const isPNG = file.type === "image/png";
            const isLt2M = file.size / 1024 / 1024 < 4;
            if (!isJPG && !isPNG) {
                this.$message.error("图片只能是 JPG/PNG 格式!");
                return false;
            }
            if (!isLt2M) {
                this.$message.error("图片大小不能超过 4MB!");
                return false;
            }
        }

        private resetTempData() {
            this.tempArtData = cloneDeep(defaultArtData)
        }

        private async handlePublish() {
            let formData = new FormData()
            formData.append('id', this.tempArtData.id.toString())
            formData.append('artName', this.tempArtData.artName)
            formData.append('artAuthor', this.tempArtData.artAuthor.toString())
            formData.append('modifiedTime', Date.parse(new Date().toString()).toString())
            formData.append('artSize', this.tempArtData.artSize.toString())
            formData.append('artTag', this.tempArtData.artTag.toString())
            formData.append('artContent', this.tempArtData.artContent)
            formData.append('artPicUrl', this.tempArtData.artPicUrl)
            let datas = await updateArt(formData)
            datas = datas.data
            console.log(datas)
            this.$router.replace("/my-profile")
            this.resetTempData()
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
        width: 984px;
        height: 484px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 984px;
        height: 484px;
        display: block;
    }
</style>
