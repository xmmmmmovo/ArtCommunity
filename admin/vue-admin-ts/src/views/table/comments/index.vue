<template>
  <div class="app-container">
    <div class="filter-container">
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
        label="commentTime"
        align="center"
        :formatter="dateFormater"
        prop="commentTime"
      >
      </el-table-column>
      <el-table-column
        label="userName"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="commentContent"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.commentContent }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="artId"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.artId }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="artName"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.artName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="commentLikeCount"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.commentLikeCount }}</span>
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
            Edit
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
        :model="tempCommentData"
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item
          label="内容"
          prop="commentContent"
        >
          <el-input v-model="tempCommentData.commentContent" />
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
  getComments,
  createComment,
  getCommentDetail,
  updateComment,
  deleteComment,
  defaultCommentData,
} from '@/api/comments'
import {
  getToken
} from '@/api/token'
import {ICommentData, IArticleData} from '@/api/types'
import { exportJson2Excel } from '@/utils/excel'
import { formatJson } from '@/utils'
import Pagination from '@/components/Pagination/index.vue'
import * as moment from 'moment'
import da from "element-ui/src/locale/lang/da";
import {updateArticle} from "@/api/articles";

@Component({
  name: 'CommentsTable',
  components: {
    Pagination
  }
})
export default class extends Vue {
  private list: ICommentData[] = []
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
    commentContent: [{ required: true, message: '这个必须的', trigger: 'change' }],
  }
  private downloadLoading = false
  private tempCommentData = defaultCommentData


  private dateFormater(row: any, column: any) {
    var date = row[column.property];
    if (date == undefined) {
      return "";
    }
    return moment(date).format("YYYY年MM月DD日 HH:mm:ss");
  }

  created() {
    this.getList()
  }

  private async getList() {
    this.listLoading = true
    let formData = new FormData()
    formData.append('page', this.listQuery.page.toString())
    formData.append('size', this.listQuery.size.toString())
    const { data } = await getComments(formData)
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

  private handleUpdate(row: any) {
    this.tempCommentData = Object.assign({}, row)
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
        formData.append('id', this.tempCommentData.id.toString())
        formData.append('commentContent', this.tempCommentData.commentContent)
        const { data } = await updateComment(formData)

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
      this.tempCommentData = Object.assign({}, row)
      let formData = new FormData()
      formData.append('id', this.tempCommentData.id.toString())
      const { data } = await deleteComment(formData)
      for (const v of this.list) {
        if (v.id == this.tempCommentData.id) {
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
    const tHeader = ['id', 'commentName', 'commentEmail', 'registerTime', 'roles']
    const filterVal = ['id', 'commentName', 'commentEmail', 'registerTime', 'roles']
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
