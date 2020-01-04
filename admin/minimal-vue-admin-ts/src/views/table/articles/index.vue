<!--<template>-->
<!--  <div class="app-container">-->
<!--    <div class="filter-container">-->
<!--      <el-input-->
<!--        v-model="listQuery.title"-->
<!--        :placeholder="搜索管理员名"-->
<!--        style="width: 200px;"-->
<!--        class="filter-item"-->
<!--        @keyup.enter.native="handleFilter"-->
<!--      />-->
<!--      <el-button-->
<!--        v-waves-->
<!--        class="filter-item"-->
<!--        type="primary"-->
<!--        icon="el-icon-search"-->
<!--        @click="handleFilter"-->
<!--      >-->
<!--        搜索-->
<!--      </el-button>-->
<!--      <el-button-->
<!--        class="filter-item"-->
<!--        style="margin-left: 10px;"-->
<!--        type="primary"-->
<!--        icon="el-icon-edit"-->
<!--        @click="handleCreate"-->
<!--      >-->
<!--        添加-->
<!--      </el-button>-->
<!--      <el-button-->
<!--        v-waves-->
<!--        :loading="downloadLoading"-->
<!--        class="filter-item"-->
<!--        type="primary"-->
<!--        icon="el-icon-download"-->
<!--        @click="handleDownload"-->
<!--      >-->
<!--        导出-->
<!--      </el-button>-->
<!--    </div>-->

<!--    <el-table-->
<!--      :key="tableKey"-->
<!--      v-loading="listLoading"-->
<!--      :data="list"-->
<!--      border-->
<!--      fit-->
<!--      highlight-current-row-->
<!--      style="width: 100%;"-->
<!--    >-->
<!--      <el-table-column-->
<!--        :label="id"-->
<!--        prop="id"-->
<!--        sortable="custom"-->
<!--        align="center"-->
<!--        width="80"-->
<!--      >-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.id }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--      <el-table-column-->
<!--        :label="registerTime"-->
<!--        width="180px"-->
<!--        align="center"-->
<!--      >-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.registerTime }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        :label="adminName"-->
<!--        min-width="150px"-->
<!--      >-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.adminName }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        :label="adminEmail"-->
<!--        width="180px"-->
<!--        align="center"-->
<!--      >-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.adminEmail }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        :label="adminAvatar"-->
<!--        width="105px"-->
<!--      >-->
<!--        <template slot-scope="scope">-->
<!--          <el-image-->
<!--            :fit="contain"-->
<!--            :src='scope.row.adminAvatar'-->
<!--          >-->
<!--          </el-image>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        :label="roles"-->
<!--        width="105px"-->
<!--      >-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.roles }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column-->
<!--        :label="操作"-->
<!--        align="center"-->
<!--        width="230"-->
<!--        class-name="fixed-width"-->
<!--      >-->
<!--        <template slot-scope="{row}">-->
<!--          <el-button-->
<!--            type="primary"-->
<!--            size="mini"-->
<!--            @click="handleUpdate(row)"-->
<!--          >-->
<!--            编辑-->
<!--          </el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="danger"-->
<!--            @click="handleDelete(row,'deleted')"-->
<!--          >-->
<!--            删除-->
<!--          </el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

<!--&lt;!&ndash;    分页相关&ndash;&gt;-->
<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="listQuery.page"-->
<!--      :limit.sync="listQuery.limit"-->
<!--      @pagination="getList"-->
<!--    />-->

<!--&lt;!&ndash;    修改用的dialog&ndash;&gt;-->
<!--    <el-dialog-->
<!--      :title="textMap[dialogStatus]"-->
<!--      :visible.sync="dialogFormVisible"-->
<!--    >-->
<!--      <el-form-->
<!--        ref="dataForm"-->
<!--        :rules="rules"-->
<!--        :model="tempAdminData"-->
<!--        label-position="left"-->
<!--        label-width="100px"-->
<!--        style="width: 400px; margin-left:50px;"-->
<!--      >-->
<!--        <el-form-item-->
<!--          :label="管理员姓名"-->
<!--        >-->
<!--          <el-input v-model="tempArticleData.adminName" />-->
<!--        </el-form-item>-->
<!--        <el-form-item-->
<!--          :label="管理员邮箱"-->
<!--        >-->
<!--          <el-input v-model="tempArticleData.adminEmail" />-->
<!--        </el-form-item>-->
<!--        <el-form-item-->
<!--          :label="权限"-->
<!--          prop="type"-->
<!--        >-->
<!--          <el-select-->
<!--            v-model="tempArticleData.type"-->
<!--            class="filter-item"-->
<!--            placeholder="Please select"-->
<!--          >-->
<!--            <el-option-->
<!--              :key="admin"-->
<!--              :label="admin"-->
<!--              :value="admin"-->
<!--            />-->
<!--            <el-option-->
<!--              :key="editor"-->
<!--              :label="editor"-->
<!--              :value="editor"-->
<!--            >-->
<!--            </el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item-->
<!--          :label="日期"-->
<!--          prop="timestamp"-->
<!--        >-->
<!--          <el-date-picker-->
<!--            v-model="tempArticleData.registerTime"-->
<!--            type="datetime"-->
<!--            placeholder="Please pick a date"-->
<!--          />-->
<!--        </el-form-item>-->
<!--        <el-form-item-->
<!--          :label="管理员头像"-->
<!--        >-->
<!--          <el-image-->
<!--            :src="tempAdminData.adminAvatar"-->
<!--            :fit="contain"-->
<!--          />-->
<!--        </el-form-item>-->
<!--        <el-form-item :label="">-->
<!--          <el-input-->
<!--            v-model="tempArticleData.abstractContent"-->
<!--            :autosize="{minRows: 2, maxRows: 4}"-->
<!--            type="textarea"-->
<!--            placeholder="Please input"-->
<!--          />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div-->
<!--        slot="footer"-->
<!--        class="dialog-footer"-->
<!--      >-->
<!--        <el-button @click="dialogFormVisible = false">-->
<!--          取消-->
<!--        </el-button>-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          @click="dialogStatus==='create'?createData():updateData()"-->
<!--        >-->
<!--          确定-->
<!--        </el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

<!--    <el-dialog-->
<!--      :visible.sync="dialogPageviewsVisible"-->
<!--      title="Reading statistics"-->
<!--    >-->
<!--      <el-table-->
<!--        :data="pageviewsData"-->
<!--        border-->
<!--        fit-->
<!--        highlight-current-row-->
<!--        style="width: 100%"-->
<!--      >-->
<!--        <el-table-column-->
<!--          prop="key"-->
<!--          label="Channel"-->
<!--        />-->
<!--        <el-table-column-->
<!--          prop="pageviews"-->
<!--          label="Pageviews"-->
<!--        />-->
<!--      </el-table>-->
<!--      <span-->
<!--        slot="footer"-->
<!--        class="dialog-footer"-->
<!--      >-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          @click="dialogPageviewsVisible = false"-->
<!--        >{{ $t('table.confirm') }}</el-button>-->
<!--      </span>-->
<!--    </el-dialog>-->
<!--  </div>-->
<!--</template>-->

<!--<script lang="ts">-->
<!--import { Component, Vue } from 'vue-property-decorator'-->
<!--import { Form } from 'element-ui'-->
<!--import { cloneDeep } from 'lodash'-->
<!--import {getAdmins, getAdminPageviews, createAdmin, updateAdmin, deleteAdmin, defaultAdminData} from '@/api/admins'-->
<!--import { IArticleData } from '@/api/types'-->
<!--import { exportJson2Excel } from '@/utils/excel'-->
<!--import { formatJson } from '@/utils'-->
<!--import Pagination from '@/components/Pagination/index.vue'-->

<!--@Component({-->
<!--  name: 'AdminsTable',-->
<!--  components: {-->
<!--    Pagination-->
<!--  }-->
<!--})-->
<!--export default class extends Vue {-->
<!--  private tableKey = 0-->
<!--  private list: IArticleData[] = []-->
<!--  private total = 0-->
<!--  private listLoading = true-->
<!--  private listQuery = {-->
<!--    page: 1,-->
<!--    size: 20-->
<!--  }-->
<!--  private textMap = {-->
<!--    update: 'Edit',-->
<!--    create: 'Create'-->
<!--  }-->
<!--  private dialogFormVisible = false-->
<!--  private dialogStatus = ''-->
<!--  private dialogPageviewsVisible = false-->
<!--  private pageviewsData = []-->
<!--  private rules = {-->
<!--    type: [{ required: true, message: 'type is required', trigger: 'change' }],-->
<!--    timestamp: [{ required: true, message: 'timestamp is required', trigger: 'change' }],-->
<!--    title: [{ required: true, message: 'title is required', trigger: 'blur' }]-->
<!--  }-->
<!--  private downloadLoading = false-->
<!--  private tempAdminData = defaultAdminData-->

<!--  created() {-->
<!--    this.getList()-->
<!--  }-->

<!--  private async getList() {-->
<!--    this.listLoading = true-->
<!--    let formData = new FormData()-->
<!--    formData.append('page', this.listQuery.page.toString())-->
<!--    formData.append('size', this.listQuery.size.toString())-->
<!--    const { data } = await getAdmins(formData)-->
<!--    this.list = data.list-->
<!--    this.total = data.total-->
<!--    // Just to simulate the time of the request-->
<!--    setTimeout(() => {-->
<!--      this.listLoading = false-->
<!--    }, 0.5 * 1000)-->
<!--  }-->

<!--  private handleFilter() {-->
<!--    this.listQuery.page = 1-->
<!--    this.getList()-->
<!--  }-->

<!--  private handleModifyStatus(row: any, status: string) {-->
<!--    this.$message({-->
<!--      message: '操作成功',-->
<!--      type: 'success'-->
<!--    })-->
<!--    row.status = status-->
<!--  }-->

<!--  private resetTempArticleData() {-->
<!--    this.tempArticleData = cloneDeep(defaultAdminData)-->
<!--  }-->

<!--  private handleCreate() {-->
<!--    this.resetTempArticleData()-->
<!--    this.dialogStatus = 'create'-->
<!--    this.dialogFormVisible = true-->
<!--    this.$nextTick(() => {-->
<!--      (this.$refs['dataForm'] as Form).clearValidate()-->
<!--    })-->
<!--  }-->

<!--  private handleDelete() {-->
<!--    this.resetTempArticleData()-->
<!--    this.dialogStatus = 'create'-->
<!--    this.dialogFormVisible = true-->
<!--    this.$nextTick(() => {-->
<!--      (this.$refs['dataForm'] as Form).clearValidate()-->
<!--    })-->
<!--  }-->

<!--  // 创建新用户-->
<!--  private createData() {-->
<!--    (this.$refs['dataForm'] as Form).validate(async(valid) => {-->
<!--      if (valid) {-->
<!--        let { id, ...articleData } = this.tempArticleData-->
<!--        articleData.author = 'vue-typescript-admin'-->
<!--        const { data } = await createArticle({ article: articleData })-->
<!--        this.list.unshift(data.article)-->
<!--        this.dialogFormVisible = false-->
<!--        this.$notify({-->
<!--          title: '成功',-->
<!--          message: '创建成功',-->
<!--          type: 'success',-->
<!--          duration: 2000-->
<!--        })-->
<!--      }-->
<!--    })-->
<!--  }-->

<!--  private handleUpdate(row: any) {-->
<!--    this.tempArticleData = Object.assign({}, row)-->
<!--    this.dialogStatus = 'update'-->
<!--    this.dialogFormVisible = true-->
<!--    this.$nextTick(() => {-->
<!--      (this.$refs['dataForm'] as Form).clearValidate()-->
<!--    })-->
<!--  }-->

<!--  private updateData() {-->
<!--    (this.$refs['dataForm'] as Form).validate(async(valid) => {-->
<!--      if (valid) {-->
<!--        // const { data } = await updateAdmin(tempData.id, { article: tempData })-->
<!--        // for (const v of this.list) {-->
<!--        //   if (v.id === data.article.id) {-->
<!--        //     const index = this.list.indexOf(v)-->
<!--        //     this.list.splice(index, 1, data.article)-->
<!--        //     break-->
<!--        //   }-->
<!--        // }-->
<!--        this.dialogFormVisible = false-->
<!--        this.$notify({-->
<!--          title: '成功',-->
<!--          message: '更新成功',-->
<!--          type: 'success',-->
<!--          duration: 2000-->
<!--        })-->
<!--      }-->
<!--    })-->
<!--  }-->

<!--  private async handleGetPageviews(pageviews: string) {-->
<!--    const { data } = await getAdminPageviews({ /* Your params here */ })-->
<!--    this.pageviewsData = data.pageviews-->
<!--    this.dialogPageviewsVisible = true-->
<!--  }-->

<!--  private handleDownload() {-->
<!--    this.downloadLoading = true-->
<!--    const tHeader = ['id', 'adminName', 'adminEmail', 'registerTime', 'roles']-->
<!--    const filterVal = ['id', 'adminName', 'adminEmail', 'registerTime', 'roles']-->
<!--    const data = formatJson(filterVal, this.list)-->
<!--    exportJson2Excel(tHeader, data, 'table-list')-->
<!--    this.downloadLoading = false-->
<!--  }-->
<!--}-->
<!--</script>-->
