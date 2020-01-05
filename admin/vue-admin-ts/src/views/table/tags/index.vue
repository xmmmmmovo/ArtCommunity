<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.title"
        :placeholder="搜索标签名"
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
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        label="id"
        align="center"
        width="80"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="tagName"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.tagName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="tagDescribe"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.tagDescribe }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="usageCount"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.usageCount }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
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


<!--        修改用的dialog-->
        <el-dialog
          :title="textMap[dialogStatus]"
          :visible.sync="dialogFormVisible"
        >
          <el-form
            ref="dataForm"
            :rules="rules"
            :model="tempTagData"
            label-position="left"
            label-width="100px"
            style="width: 400px; margin-left:50px;"
          >
            <el-form-item
              label="标签名"
              prop="tagName"
            >
              <el-input v-model="tempTagData.tagName" />
            </el-form-item>
            <el-form-item
              label="标签描述"
              prop="tagDescribe"
            >
              <el-input
                v-model="tempTagData.tagDescribe"
                :autosize="{minRows: 3, maxRows: 5}"
                type="textarea"
                placeholder="请在此输入"
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
import {
  getTags,
  createTag,
  getTagDetail,
  updateTag,
  deleteTag,
  defaultTagData
} from '@/api/tags'
import {
  getToken
} from '@/api/token'
import {ITagData, IArticleData} from '@/api/types'
import { exportJson2Excel } from '@/utils/excel'
import { formatJson } from '@/utils'
import Pagination from '@/components/Pagination/index.vue'
import * as moment from 'moment'
import {genUpToken} from "@/utils/token";

@Component({
  name: 'tagsTable',
  components: {
    Pagination
  }
})
export default class extends Vue {
  private list: ITagData[] = []
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
  private rules = {
    tagName: [{ required: true, message: '标签名是必须的', trigger: 'change' }],
    tagDescribe: [{ required: true, message: '标签形容是必须的', trigger: 'change' }],
  }
  private downloadLoading = false
  private tempTagData = defaultTagData


  created() {
    this.getList()
  }

  private async getList() {
    this.listLoading = true
    let formData = new FormData()
    formData.append('page', this.listQuery.page.toString())
    formData.append('size', this.listQuery.size.toString())
    const { data } = await getTags(formData)
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
    this.tempTagData = cloneDeep(defaultTagData)
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
        let { id, ...Data } = this.tempTagData
        let formData = new FormData()
        formData.append('tagName', Data.tagName)
        formData.append('tagDescribe', Data.tagDescribe)
        let datas = await createTag(formData)
        datas = datas.data
        console.log(datas)
        this.list.unshift(datas)
        this.dialogFormVisible = false
        this.$notify({
          title: '成功',
          message: '创建成功',
          type: 'success'
        })
      }
    })
  }

  private handleUpdate(row: any) {
    this.tempTagData = Object.assign({}, row)
    this.dialogStatus = 'update'
    this.dialogFormVisible = true
    this.$nextTick(() => {
      (this.$refs['dataForm'] as Form).clearValidate()
    })
  }

  private updateData() {
    (this.$refs['dataForm'] as Form).validate(async(valid) => {
      if (valid) {
        let formData = new FormData()
        formData.append('id', this.tempTagData.id.toString())
        formData.append('tagName', this.tempTagData.tagName)
        formData.append('tagDescribe', this.tempTagData.tagDescribe)
        const { data } = await updateTag(formData)

        for (const v of this.list) {
          if (v.id == data.id) {
            const index = this.list.indexOf(v)
            this.list.splice(index, 1, data)
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
      this.tempTagData = Object.assign({}, row)
      let formData = new FormData()
      formData.append('id', this.tempTagData.id.toString())
      const { data } = await deleteTag(formData)
      for (const v of this.list) {
        if (v.id == this.tempTagData.id) {
          const index = this.list.indexOf(v)
          this.list.splice(index, 1)
          break
        }
      }
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
    const tHeader = ['id', 'tagName', 'tagDescribe', 'usageCount']
    const filterVal = ['id', 'tagName', 'tagDescribe', 'usageCount']
    const data = formatJson(filterVal, this.list)
    exportJson2Excel(tHeader, data, 'table-list')
    this.downloadLoading = false
  }
}
</script>

<style lang="scss" scoped>
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
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
