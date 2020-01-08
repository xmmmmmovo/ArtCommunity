<template>
    <div>
        <div>
            <NavBar/>
        </div>
        <row>
            <ProfileSide/>
            <v-col cols="4">
                <v-card
                        max-width="300px"
                >
                    <v-form
                            ref="form"
                            v-model="valid"
                            lazy-validation
                    >
                        <v-text-field
                                v-model="userName"
                                label="用户名"
                                name="userName"
                                prepend-icon="person"
                                type="text"
                                :rules="nameRules"
                                @input="$v.userName.$touch()"
                                @blur="$v.userName.$touch()"
                        />
                        <v-text-field
                                v-model="userEmail"
                                label="用户邮箱"
                                name="userEmail"
                                prepend-icon="email"
                                type="text"
                                :rules="emailRules"
                                @input="$v.userEmail.$touch()"
                                @blur="$v.userEmail.$touch()"
                        />
                        <v-text-field
                                v-model="userPassword"
                                label="密码"
                                name="userPassword"
                                prepend-icon="lock"
                                type="password"
                                :rules="passwordRules"
                                :counter="6"
                                @input="$v.userPassword.$touch()"
                                @blur="$v.userPassword.$touch()"
                        />
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
    import {UserModule} from "@/store/modules/user";
    import {parseTime} from "@/utils"
    import {IArtData, IArticleData, ILikeData} from "@/api/types";
    import {getArts, getUserArts} from "@/api/arts";
    import {getUserArticles} from "@/api/articles";
    import {getUserLikes} from "@/api/likes";

    @Component({
        name: 'ProfileModify',
        components: {
            NavBar,
            ProfileSide
        }
    })
    export default class App extends Vue {

        get name() {
            return UserModule.name
        }

        get email() {
            return UserModule.email
        }

        created() {
            this.init()
        }

        private async init() {

        }

        private createTime(time: string | number) {
            return parseTime(time)
        }
    }
</script>

<style scoped>
</style>
