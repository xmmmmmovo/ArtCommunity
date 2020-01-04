<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >
        {{ $t('table.add') }}
      </el-button>
      <el-button
        v-waves
        :loading="downloadLoading"
        class="filter-item"
        type="primary"
        icon="el-icon-download"
        @click="handleDownload"
      >
        {{ $t('table.export') }}
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
        :label="$t('table.id')"
        prop="id"
        sortable="custom"
        align="center"
        width="80"
        :class-name="getSortClass('id')"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column
        :label="$t('table.date')"
        width="180px"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.timestamp | parseTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        :label="$t('table.title')"
        min-width="150px"
      >
        <template slot-scope="{row}">
          <span
            class="link-type"
            @click="handleUpdate(row)"
          >{{ row.title }}</span>
          <el-tag>{{ row.type | typeFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        :label="$t('table.author')"
        width="180px"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column
        :label="$t('table.importance')"
        width="105px"
      >
        <template slot-scope="scope">
          <svg-icon
            v-for="n in +scope.row.importance"
            :key="n"
            name="star"
            class="meta-item__icon"
          />
        </template>
      </el-table-column>

      <el-table-column
        :label="$t('table.readings')"
        align="center"
        width="95"
      >
        <template slot-scope="{row}">
          <span
            v-if="row.pageviews"
            class="link-type"
            @click="handleGetPageviews(row.pageviews)"
          >{{ row.pageviews }}</span>
          <span v-else>0</span>
        </template>
      </el-table-column>

      <el-table-column
        :label="$t('table.status')"
        class-name="status-col"
        width="100"
      >
        <template slot-scope="{row}">
          <el-tag :type="row.status | articleStatusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        :label="$t('table.actions')"
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
            {{ $t('table.edit') }}
          </el-button>
          <el-button
            v-if="row.status!=='published'"
            size="mini"
            type="success"
            @click="handleModifyStatus(row,'published')"
          >
            {{ $t('table.publish') }}
          </el-button>
          <el-button
            v-if="row.status!=='draft'"
            size="mini"
            @click="handleModifyStatus(row,'draft')"
          >
            {{ $t('table.draft') }}
          </el-button>
          <el-button
            v-if="row.status!=='deleted'"
            size="mini"
            type="danger"
            @click="handleModifyStatus(row,'deleted')"
          >
            {{ $t('table.delete') }}
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
        :model="tempArticleData"
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item
          :label="$t('table.type')"
          prop="type"
        >
          <el-select
            v-model="tempArticleData.type"
            class="filter-item"
            placeholder="Please select"
          >
            <el-option
              v-for="item in calendarTypeOptions"
              :key="item.key"
              :label="item.display_name"
              :value="item.key"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          :label="$t('table.date')"
          prop="timestamp"
        >
          <el-date-picker
            v-model="tempArticleData.timestamp"
            type="datetime"
            placeholder="Please pick a date"
          />
        </el-form-item>
        <el-form-item
          :label="$t('table.title')"
          prop="title"
        >
          <el-input v-model="tempArticleData.title" />
        </el-form-item>
        <el-form-item :label="$t('table.status')">
          <el-select
            v-model="tempArticleData.status"
            class="filter-item"
            placeholder="Please select"
          >
            <el-option
              v-for="item in statusOptions"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('table.importance')">
          <el-rate
            v-model="tempArticleData.importance"
            :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
            :max="3"
            style="margin-top:8px;"
          />
        </el-form-item>
        <el-form-item :label="$t('table.remark')">
          <el-input
            v-model="tempArticleData.abstractContent"
            :autosize="{minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="Please input"
          />
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogFormVisible = false">
          {{ $t('table.cancel') }}
        </el-button>
        <el-button
          type="primary"
          @click="dialogStatus==='create'?createData():updateData()"
        >
          {{ $t('table.confirm') }}
        </el-button>
      </div>
    </el-dialog>

    <el-dialog
      :visible.sync="dialogPageviewsVisible"
      title="Reading statistics"
    >
      <el-table
        :data="pageviewsData"
        border
        fit
        highlight-current-row
        style="width: 100%"
      >
        <el-table-column
          prop="key"
          label="Channel"
        />
        <el-table-column
          prop="pageviews"
          label="Pageviews"
        />
      </el-table>
      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="dialogPageviewsVisible = false"
        >{{ $t('table.confirm') }}</el-button>
      </span>
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
  private showReviewer = false
  private dialogFormVisible = false
  private dialogStatus = ''
  private textMap = {
    update: 'Edit',
    create: 'Create'
  }
  private dialogPageviewsVisible = false
  private pageviewsData = []
  private rules = {
    type: [{ required: true, message: 'type is required', trigger: 'change' }],
    timestamp: [{ required: true, message: 'timestamp is required', trigger: 'change' }],
    title: [{ required: true, message: 'title is required', trigger: 'blur' }]
  }
  private downloadLoading = false
  private tempArticleData = defaultAdminData

  created() {
    this.getList()
  }

  private async getList() {
    this.listLoading = true
    const { data } = await getAdmins(this.listQuery)
    this.list = data.items
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

  private resetTempArticleData() {
    this.tempArticleData = cloneDeep(defaultAdminData)
  }

  private handleCreate() {
    this.resetTempArticleData()
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
        let { id, ...articleData } = this.tempArticleData
        articleData.author = 'vue-typescript-admin'
        const { data } = await createArticle({ article: articleData })
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
    this.tempArticleData = Object.assign({}, row)
    this.tempArticleData.timestamp = +new Date(this.tempArticleData.timestamp)
    this.dialogStatus = 'update'
    this.dialogFormVisible = true
    this.$nextTick(() => {
      (this.$refs['dataForm'] as Form).clearValidate()
    })
  }

  private updateData() {
    (this.$refs['dataForm'] as Form).validate(async(valid) => {
      if (valid) {
        const tempData = Object.assign({}, this.tempArticleData)
        tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
        const { data } = await updateArticle(tempData.id, { article: tempData })
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
          type: 'success',
          duration: 2000
        })
      }
    })
  }

  private async handleGetPageviews(pageviews: string) {
    const { data } = await getAdminPageviews({ /* Your params here */ })
    this.pageviewsData = data.pageviews
    this.dialogPageviewsVisible = true
  }

  private handleDownload() {
    this.downloadLoading = true
    const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
    const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
    const data = formatJson(filterVal, this.list)
    exportJson2Excel(tHeader, data, 'table-list')
    this.downloadLoading = false
  }
}
</script>
