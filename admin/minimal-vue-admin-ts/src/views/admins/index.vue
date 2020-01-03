<template>
  <div class="admin-container">
    <component :is="currentRole" />
  </div>
</template>

<script lang="ts">
  import { Component, Vue } from 'vue-property-decorator'
  import { AdminModule } from '@/store/modules/admin'
  import AdminsTable from './admin/index.vue'
  import EditorNotAllow from './editor/index.vue'

  @Component({
    name: 'admin',
    components: {
      AdminsTable,
      EditorNotAllow
    }
  })
  export default class extends Vue {
    private currentRole = 'EditorNotAllow'

    get roles() {
      return AdminModule.roles
    }

    created() {
      if (this.roles.includes('admin')) {
        this.currentRole = 'AdminsTable'
      }
    }
  }
</script>
