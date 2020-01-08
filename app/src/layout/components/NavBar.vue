<template>
    <div>
        <v-app-bar
                color="deep-purple accent-4"
        >
            <!--            <img src="./src/assets/logo.png"/>-->

            <v-toolbar-title @click="main">社区本体</v-toolbar-title>

            <v-spacer></v-spacer>

            <el-button
                    class="btn"
                    type="text"
                    @click="articleRoute"

            >
                艺术品
            </el-button>
            <el-button
                    class="btn"
                    type="text"
                    @click="articleRoute"
            >
                文章
            </el-button>
            <!--            <el-button-->
            <!--                    class="btn"-->
            <!--                    type="text"-->
            <!--            >-->
            <!--                点赞排行-->
            <!--            </el-button>-->
            <el-button
                    class="btn"
                    type="text"
                    v-if="status"
                    @click="registerRoute"
            >
                注册
            </el-button>
            <el-button
                    class="btn"
                    type="text"
                    v-if="status"
                    @click="loginRoute"
            >
                登录
            </el-button>

            <el-dropdown
                    class="avatar-container right-menu-item hover-effect"
                    trigger="click"
                    v-else
            >
                <div class="avatar-wrapper">
                    <v-avatar tile
                              :src="avatar"
                              class="user-avatar"
                    />
                    <i class="el-icon-caret-bottom"/>
                </div>
                <el-dropdown-menu slot="dropdown">
                    <router-link to="/my-profile">
                        <el-dropdown-item>
                            <a>
                                欢迎您！{{name}}
                            </a>
                        </el-dropdown-item>
                    </router-link>

                    <router-link to="/">
                        <el-dropdown-item
                        >
                            主页
                        </el-dropdown-item>
                    </router-link>
                    <el-dropdown-item divided>
                    <span
                            style="display:block;"
                            @click="logout"
                    >登出
                    </span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>

        </v-app-bar>
    </div>
</template>

<script lang="ts">
    import Component from "vue-class-component";
    import Vue from "vue";
    import {UserModule} from "@/store/modules/user";
    import router from "@/router";

    @Component({
        name: 'NavBar'
    })
    export default class App extends Vue {
        get avatar() {
            return UserModule.avatar
        }

        get name() {
            return UserModule.name
        }

        get token() {
            return UserModule.token
        }

        get status() {
            if (UserModule.token === '') {
                return true
            }
            return false
        }

        private async logout() {
            await UserModule.LogOut()
            this.$router.replace(`/login`)
        }

        private main() {
            this.$router.replace('/')
        }

        private artRoute() {
            this.$router.replace('/')
        }

        private articleRoute() {
            this.$router.replace('/list-article')
        }

        private registerRoute() {
            this.$router.replace('/register')

        }

        private loginRoute() {
            this.$router.replace('/login')
        }

        private selfRoute() {
            this.$router.replace('/my-profile')
        }
    }
</script>

<style lang="scss" scoped>
    .btn {
        margin: 1%;
    }

    .navbar {
        height: 50px;
        overflow: hidden;
        position: relative;
        background: #fff;
        box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

        .hamburger-container {
            line-height: 46px;
            height: 100%;
            float: left;
            padding: 0 15px;
            cursor: pointer;
            transition: background .3s;
            -webkit-tap-highlight-color: transparent;

            &:hover {
                background: rgba(0, 0, 0, .025)
            }
        }

        .breadcrumb-container {
            float: left;
        }

        .right-menu {
            float: right;
            height: 100%;
            line-height: 50px;

            &:focus {
                outline: none;
            }

            .right-menu-item {
                display: inline-block;
                padding: 0 8px;
                height: 100%;
                font-size: 18px;
                color: #5a5e66;
                vertical-align: text-bottom;

                &.hover-effect {
                    cursor: pointer;
                    transition: background .3s;

                    &:hover {
                        background: rgba(0, 0, 0, .025)
                    }
                }
            }

            .avatar-container {
                margin-right: 30px;

                .avatar-wrapper {
                    margin-top: 5px;
                    position: relative;

                    .user-avatar {
                        cursor: pointer;
                        width: 40px;
                        height: 40px;
                        border-radius: 10px;
                    }

                    .el-icon-caret-bottom {
                        cursor: pointer;
                        position: absolute;
                        right: -20px;
                        top: 25px;
                        font-size: 12px;
                    }
                }
            }
        }
    }
</style>
