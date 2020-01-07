<template>
    <div>
        <div>
            <NavBar/>
        </div>
        <v-img
                src="http://q3lynq058.bkt.clouddn.com/profile/%E9%A1%B6%E9%83%A8.jpg"
        ></v-img>
        <v-row>
            <v-col cols="2" style="margin-left: 8%">
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
            <v-col cols="8" style="margin-right: 8%">
                <v-tabs grow>
                    <v-tab>作品</v-tab>
                    <v-tab-item>
                        <div
                                v-for="(art, i) in artList"
                                :key="i"
                                style="margin-top: 5%"
                        >
                            <v-row>
                                <v-col cols="6">
                                    <v-img
                                            :src="art.artPicUrl"
                                            max-height="984px"
                                            max-width="484px"
                                    >
                                    </v-img>
                                </v-col>
                                <v-col cols="6">
                                    <h2>
                                        标题: {{art.artName}}
                                    </h2>
                                    <h3>
                                        创作时间: {{createTime(art.createTime)}}
                                    </h3>
                                    <h3>
                                        点赞数: {{art.artLikeNum}}
                                    </h3>
                                    <h4>
                                        尺寸: {{art.length}} * {{art.height}}
                                    </h4>
                                    <h4>
                                        分类: {{art.tagName}}
                                    </h4>
                                </v-col>
                            </v-row>
                        </div>
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
    import {IArtData} from "@/api/types";
    import {getArts} from "@/api/arts";

    @Component({
        name: 'SelfProfile',
        components: {
            NavBar
        }
    })
    export default class App extends Vue {
        private artList: IArtData[] = []


        get name() {
            return UserModule.name
        }

        get registerTime() {
            return parseTime(UserModule.registerTime)
        }

        created() {
            this.getList()
        }

        private async getList() {
            console.log(UserModule.token)
            let formData = new FormData();
            formData.append("page", String(0))
            formData.append("size", String(0))
            formData.append("token", UserModule.token)
            const {data} = await getArts(formData)
            this.artList = data.list
        }

        private createTime(time: string | number) {
            return parseTime(time)
        }
    }
</script>

<style scoped>
</style>
