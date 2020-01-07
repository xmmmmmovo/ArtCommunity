<template>
    <div>
        <div>
            <NavBar/>
        </div>
        <v-img
                src="http://q3lynq058.bkt.clouddn.com/profile/%E9%A1%B6%E9%83%A8.jpg"
        ></v-img>
        <v-row>
            <v-col cols="2" style="margin-left: 10%">
                <h1>
                    {{name}}
                </h1>
                <h3>
                    入驻社区时间<br>
                </h3>
                <h3>
                    {{registerTime}}
                </h3>

                <v-btn x-large color="indigo" block outlined style="margin-top: 20%">添加文章</v-btn>
                <v-btn x-large color="indigo" block outlined style="margin-top: 20%">添加画作</v-btn>
                <v-btn x-large color="indigo" block outlined style="margin-top: 20%">修改个人信息</v-btn>
            </v-col>
            <v-col cols="7" style="margin-right: 10%">
                <v-tabs grow>
                    <v-tab>作品</v-tab>
                    <v-tab-item>
                        1
                    </v-tab-item>

                    <v-tab>文章</v-tab>
                    <v-tab-item>
                        2
                    </v-tab-item>

                    <v-tab>点赞作品</v-tab>
                    <v-tab-item>
                        3
                    </v-tab-item>
                </v-tabs>

            </v-col>
        </v-row>
    </div>


</template>

<script lang="ts">
    import Vue from 'vue'
    import Component from 'vue-class-component'
    import NavBar from "@/layout/components/NavBar.vue";
    import {UserModule} from "@/store/modules/user";
    import {parseTime} from "@/utils/index"

    @Component({
        name: 'SelfProfile',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        get name() {
            return UserModule.name
        }

        get registerTime() {
            return parseTime(UserModule.registerTime)
        }
    }
</script>

<style scoped>
    .bk_content {
        /*background-image: url("../assets/login_bk.jpg");*/
    }
</style>
