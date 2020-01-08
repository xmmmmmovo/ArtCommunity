<template>
    <div>
        <div>
            <NavBar/>
        </div>
        <row>
            <v-col cols="4">
                <el-upload
                        align="center"
                        class="avatar-uploader"
                        action="https://upload-z1.qiniup.com"
                        :show-file-list="false"
                        :data="postData"
                        :on-success="onCropUploadSuccess"
                        :before-upload="beforeAvatarUpload">
                    <img v-if="tempUser.userAvatar"
                           :src="tempUser.userAvatar"
                           class="avatar" max-height="300px" max-width="300px">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
                <v-card
                >
                    <v-form
                            ref="form"
                            v-model="valid"
                            lazy-validation
                    >
                        <v-text-field
                                v-model="tempUser.userName"
                                label="用户名"
                                name="userName"
                                prepend-icon="person"
                                type="text"
                                :rules="nameRules"
                                @input="$v.userName.$touch()"
                                @blur="$v.userName.$touch()"
                        />
                        <v-text-field
                                v-model="tempUser.userEmail"
                                label="用户邮箱"
                                name="userEmail"
                                prepend-icon="email"
                                type="text"
                                :rules="emailRules"
                                @input="$v.userEmail.$touch()"
                                @blur="$v.userEmail.$touch()"
                        />
                        <v-text-field
                                v-model="pwd"
                                label="密码"
                                name="userPassword"
                                prepend-icon="lock"
                                type="password"
                                :rules="passwordRules"
                                :counter="6"
                                @input="$v.userPassword.$touch()"
                                @blur="$v.userPassword.$touch()"
                        />
                        <b-button @click="handleUpdate">修改</b-button>
                    </v-form>
                </v-card>
            </v-col>
        </row>

    </div>


</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import ProfileSide from "@/layout/components/ProfileSide.vue";
    import {IUserState, UserModule} from "@/store/modules/user";
    import {parseTime} from "@/utils"
    import {IArtData, IArticleData, ILikeData, IUserData} from "@/api/types";
    import {getArts, getUserArts} from "@/api/arts";
    import {getUserArticles} from "@/api/articles";
    import {getUserLikes} from "@/api/likes";
    import {defaultUserData, getUserDetail} from "@/api/users";
    import da from "element-ui/src/locale/lang/da";

    @Component({
        name: 'ProfileModify',
        components: {
            NavBar,
            ProfileSide
        }
    })
    export default class App extends Vue {

        private tempUser:IUserData = defaultUserData
        private pwd = ''

        get name() {
            return UserModule.name
        }

        get email() {
            return UserModule.email
        }

        get avatar() {
            return UserModule.avatar
        }

        created() {
            this.init()
        }

        private async init() {
            let formData = new FormData()
            formData.append('id', UserModule.id.toString())
            const {data} = await getUserDetail(formData)
            this.tempUser = data
        }

        private handleUpdate() {

        }

        private createTime(time: string | number) {
            return parseTime(time)
        }
    }
</script>

<style scoped>
</style>
