<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.title"
        :placeholder="搜索管理员名"
        style="width: 200px;"
        class="filter-item"
        @keyup.enter.native="handleFilter"
      />
      <el-button
        v-waves
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        @click="handleFilter"
      >
        搜索
      </el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >
        添加
      </el-button>
      <el-button
        v-waves
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
      >
        导出
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        :label="id"
        prop="id"
        sortable="custom"
        align="center"
        width="80"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column
        :label="registerTime"
        width="180px"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.registerTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        :label="adminName"
        min-width="150px"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.adminName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        :label="adminEmail"
        width="180px"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.adminEmail }}</span>
        </template>
      </el-table-column>
      <el-table-column
        :label="adminAvatar"
        width="105px"
      >
        <template slot-scope="scope">
          <el-image
            :fit="contain"
            :src='scope.row.adminAvatar'
          >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column
        :label="roles"
        width="105px"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.roles }}</span>
        </template>
      </el-table-column>
      <el-table-column
        :label="操作"
        align="center"
        width="230"
        class-name="fixed-width"
      >
        <template slot-scope="{row}">
          <el-button
            type="primary"
            size="mini"
            @click="handleUpdate(row)"
          >
            编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteData(row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

<!--    分页相关-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getList"
    />

<!--    修改用的dialog-->
    <el-dialog
      :title="textMap[dialogStatus]"
      :visible.sync="dialogFormVisible"
    >
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="tempAdminData"
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item
          :label="管理员编号"
        >
          <span v-model="tempArticleData.id" />
        </el-form-item>
        <el-form-item
          :label="管理员姓名"
          :prop="adminName"
        >
          <el-input v-model="tempArticleData.adminName" />
        </el-form-item>
        <el-form-item
          :label="管理员邮箱"
          :prop="adminEmail"
        >
          <el-input v-model="tempArticleData.adminEmail" />
        </el-form-item>
        <el-form-item
          v-if="dialogStatus==='create'"
          :label="管理员密码"
        >
          <el-input v-model="password" />
        </el-form-item>
        <el-form-item
          :label="权限"
          prop="roles"
        >
          <el-select
            v-model="tempArticleData.type"
            class="filter-item"
            placeholder="Please select"
          >
            <el-option
              :key="admin"
              :label="admin"
              :value="admin"
            />
            <el-option
              :key="editor"
              :label="editor"
              :value="editor"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          :label="管理员头像"
        >
          <el-image
            :src="tempAdminData.adminAvatar"
            :fit="contain"
            @click="toggleShow"
          />
          <avatar-upload
            v-model="showImageUpload"
            field="avatar"
            :width="300"
            :height="300"
            :params="params"
            :headers="headers"
            url="https://httpbin.org/post"
            @close="onClose"
            @crop-upload-success="onCropUploadSuccess"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button
          type="primary"
          @click="dialogStatus==='create'?createData():updateData()"
        >
          确定
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { Form } from 'element-ui'
import { cloneDeep } from 'lodash'
import {getAdmins, getAdminPageviews, createAdmin, updateAdmin, deleteAdmin, defaultAdminData} from '@/api/admins'
import { IArticleData } from '@/api/types'
import { exportJson2Excel } from '@/utils/excel'
import { formatJson } from '@/utils'
import Pagination from '@/components/Pagination/index.vue'

@Component({
  name: 'AdminsTable',
  components: {
    Pagination
  }
})
export default class extends Vue {
  private tableKey = 0
  private list: IArticleData[] = []
  private total = 0
  private listLoading = true
  private listQuery = {
    page: 1,
    size: 20
  }
  private textMap = {
    update: '编辑',
    create: '创建'
  }
  private dialogFormVisible = false
  private dialogStatus = ''
  private showImageUpload = false
  private rules = {
    adminName: [{ required: true, message: 'type is required', trigger: 'change' }],
    adminEmail: [{ required: true, message: 'timestamp is required', trigger: 'change' }],
  }
  private downloadLoading = false
  private tempAdminData = defaultAdminData
  private password = ''

  private onCropUploadSuccess(jsonData: any, field: string) {
    this.showImageUpload = false
    this.tempAdminData.adminAvatar = jsonData.files[field]
  }

  private onClose() {
    this.showImageUpload = false
  }

  private toggleShow() {
    this.showImageUpload = !this.showImageUpload
  }

  created() {
    this.getList()
  }

  private async getList() {
    this.listLoading = true
    let formData = new FormData()
    formData.append('page', this.listQuery.page.toString())
    formData.append('size', this.listQuery.size.toString())
    const { data } = await getAdmins(formData)
    this.list = data.list
    this.total = data.total
    // Just to simulate the time of the request
    setTimeout(() => {
      this.listLoading = false
    }, 0.5 * 1000)
  }

  private handleFilter() {
    this.listQuery.page = 1
    this.getList()
  }

  private handleModifyStatus(row: any, status: string) {
    this.$message({
      message: '操作成功',
      type: 'success'
    })
    row.status = status
  }

  private resetTempData() {
    this.tempAdminData = cloneDeep(defaultAdminData)
  }

  private handleCreate() {
    this.resetTempData()
    this.dialogStatus = 'create'
    this.dialogFormVisible = true
    this.$nextTick(() => {
      (this.$refs['dataForm'] as Form).clearValidate()
    })
  }

  // 创建新用户
  private createData() {
    (this.$refs['dataForm'] as Form).validate(async(valid) => {
      if (valid) {
        let { id, ...articleData } = this.tempAdminData
        let formData = new FormData()
        formData.append('adminName', articleData.adminName)
        formData.append('adminName', articleData.adminName)
        formData.append('adminName', articleData.adminName)
        const { data } = await createAdmin({ article: articleData })
        this.list.unshift(data.article)
        this.dialogFormVisible = false
        this.$notify({
          title: '成功',
          message: '创建成功',
          type: 'success',
          duration: 2000
        })
      }
    })
  }

  private handleUpdate(row: any) {
    this.tempAdminData = Object.assign({}, row)
    this.dialogStatus = 'update'
    this.dialogFormVisible = true
    this.$nextTick(() => {
      (this.$refs['dataForm'] as Form).clearValidate()
    })
  }

  private updateData() {
    (this.$refs['dataForm'] as Form).validate(async(valid) => {
      if (valid) {
        const { data } = await updateAdmin(tempData.id, { article: tempData })
        for (const v of this.list) {
          if (v.id === data.article.id) {
            const index = this.list.indexOf(v)
            this.list.splice(index, 1, data.article)
            break
          }
        }
        this.dialogFormVisible = false
        this.$notify({
          title: '成功',
          message: '更新成功',
          type: 'success'
        })
      }
    })
  }

  private deleteData(row: any) {
    this.$alert('请确认是否删除', '确认删除', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
      }).then(async () => {
      this.tempAdminData = Object.assign({}, row)
      let formData = new FormData()
      formData.append('id', this.tempAdminData.id.toString())
      const { data } = await deleteAdmin(formData)
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success'
      })
    }).catch(() => {
      this.$message({
        type: 'info',
        message: '已取消删除'
      })
    })
  }

  private handleDownload() {
    this.downloadLoading = true
    const tHeader = ['id', 'adminName', 'adminEmail', 'registerTime', 'roles']
    const filterVal = ['id', 'adminName', 'adminEmail', 'registerTime', 'roles']
    const data = formatJson(filterVal, this.list)
    exportJson2Excel(tHeader, data, 'table-list')
    this.downloadLoading = false
  }
}
</script>
