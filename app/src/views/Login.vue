<template>
    <v-app id="inspire">
        <v-content
                class="bk_content"
        >
            <v-container
                    class="fill-height"
                    fluid
            >
                <v-row
                        align="center"
                        justify="center"
                >
                    <v-col
                            cols="12"
                            sm="8"
                            md="4"
                    >
                        <v-card class="elevation-12">
                            <v-toolbar
                                    color="primary"
                                    dark
                                    flat
                            >
                                <v-toolbar-title>登录</v-toolbar-title>
                            </v-toolbar>
                            <v-card-text>
                                <v-form
                                        ref="form"
                                        v-model="valid"
                                        lazy-validation
                                >
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
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer/>
                                <v-btn color="white"
                                       @click="handleRegister">
                                    注册
                                </v-btn>
                                <v-btn color="primary"
                                       @click="handleLogin"
                                       :disabled="!valid"
                                >
                                    登录
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import {isValidateEmail, isValidatePassword} from "@/utils/validate";
    import router from "@/router";
    import {UserModule} from "@/store/modules/user";


    @Component({
        name: 'Login'
    })
    export default class App extends Vue {
        // 初始化数据
        private userEmail = ''
        private userPassword = ''
        private valid = true

        private emailRules = [
            (v: any) => !!v || '请输入邮箱!',
            (v: any) => isValidateEmail(v) || '邮箱格式有误！'
        ]
        private passwordRules = [
            (v: any) => !!v || '请输入密码!',
            (v: any) => isValidatePassword(v) || '密码不够长度!'
        ]


        private async handleLogin() {
            if (this.$refs.form.validate()) {
                await UserModule.Login({
                    userEmail: this.userEmail,
                    userPassword: this.userPassword
                })
                this.$router.replace("/")
            }
        }

        private async handleRegister() {
            this.$router.replace("/register")
        }
    }
</script>

<style scoped>
    .bk_content {
        /*background-image: url("../assets/login_bk.jpg");*/
    }
</style>
