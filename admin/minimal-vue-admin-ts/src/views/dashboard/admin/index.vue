<template>
  <div class="editor-container">
    <div class="clearfix">
      <github-corner style="position: absolute; top: 0px; border: 0; right: 0;" />
      <div class="info-container">
        <span class="display_name">欢迎您！{{ name }}</span>
        <span style="font-size:20px;padding-top:20px;display:inline-block;">您现在的身份是：管理员</span>
      </div>
    </div>
    <div>
      <img
        :src="emptyGif"
        class="emptyGif"
      >
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { AdminModule } from '@/store/modules/admin'
import PanThumb from '@/components/PanThumb/index.vue'
import GithubCorner from '@/components/GithubCorner/index.vue'

@Component({
  name: 'AdminDashboard'
})
export default class extends Vue {
  private emptyGif = 'https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3'

  get name() {
    return AdminModule.name
  }

  get avatar() {
    return AdminModule.avatar
  }

  get roles() {
    return AdminModule.roles
  }
}
</script>

<style lang="scss" scoped>
.emptyGif {
  display: block;
  width: 45%;
  margin: 0 auto;
}

.editor-container {
  background-color: #e3e3e3;
  min-height: 100vh;
  padding: 50px 60px 0px;

  .info-roles {
    font-size: 12px;
    font-weight: 700;
    color: #333;
    display: block;
  }

  .info-container {
    position: relative;
    margin-left: 190px;
    height: 150px;
    line-height: 200px;

    .display_name {
      font-size: 48px;
      line-height: 48px;
      color: #212121;
      position: absolute;
      top: 25px;
    }
  }
}
</style>
