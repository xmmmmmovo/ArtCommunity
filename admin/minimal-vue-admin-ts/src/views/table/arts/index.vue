<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.title"
        :placeholder="搜索作品名"
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
        label="createTime"
        align="center"
        :formatter="dateFormater"
        prop="createTime"
      >
      </el-table-column>
      <el-table-column
        label="modifiedTime"
        align="center"
        :formatter="dateFormater"
        prop="modifiedTime"
      >
      </el-table-column>
      <el-table-column
        label="artName"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.artName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="userName"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="length"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.length }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="height"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.height }}</span>
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
        label="artLikeNum"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.artLikeNum }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="artCommentNum"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.artCommentNum }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="artContent"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.artContent }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="artPicUrl"
        width="130px"
      >
        <template slot-scope="scope">
          <el-image
            :fit="contain"
            :src='scope.row.artPicUrl'
          >
          </el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="roles"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.roles }}</span>
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
            :model="tempArtData"
            label-position="left"
            label-width="100px"
            style="width: 400px; margin-left:50px;"
          >
            <el-form-item
              label="作品名"
              prop="artName"
            >
              <el-input v-model="tempArtData.artName" />
            </el-form-item>

            <el-form-item
              label="作者名"
              prop="articleAuthor"
            >
              <el-select
                v-model="tempArticleData.articleAuthor"
                class="filter-item"
                placeholder="{{tempArticleData.articleAuthor}}"
              >
                <el-option
                  v-for="item in userList"
                  :key="item.id"
                  :label="item.userName"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>

            <el-form-item
              label="作品类型"
              prop="userName"
            >
              <el-input v-model="tempArtData.userName" />
            </el-form-item>

            <el-form-item
              label="作品尺寸"
              prop="articleAuthor"
            >
              <el-select
                v-model="tempArticleData.articleAuthor"
                class="filter-item"
                placeholder="{{tempArticleData.articleAuthor}}"
              >
                <el-option
                  v-for="item in userList"
                  :key="item.id"
                  :label="item.userName"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>


            <el-form-item
              label="作品图片"
            >
              <el-upload
                class="avatar-uploader"
                action="https://upload-z1.qiniup.com"
                :show-file-list="false"
                :data="postData"
                :on-success="onCropUploadSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="tempArtData.artAvatar" :src="tempArtData.artAvatar" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>

            <el-form-item
              label="作品描述"
              prop="articleContent"
            >
              <el-input
                v-model="tempArtData.artContent"
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
  getArts,
  createArt,
  getArtDetail,
  updateArt,
  deleteArt,
  defaultArtData,
} from '@/api/arts'
import {
  getToken
} from '@/api/token'
import {IArtData, IArticleData, ISizeData, ITagData, IUserData} from '@/api/types'
import { exportJson2Excel } from '@/utils/excel'
import { formatJson } from '@/utils'
import Pagination from '@/components/Pagination/index.vue'
import * as moment from 'moment'

@Component({
  name: 'ArtsTable',
  components: {
    Pagination
  }
})
export default class extends Vue {
  private list: IArtData[] = []
  private userList: IUserData[] = []
  private tagList: ITagData[] = []
  private sizeList: ISizeData[] = []
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
    artName: [{ required: true, message: '姓名是必须的', trigger: 'change' }],
    artEmail: [{ required: true, message: '邮箱是必须的', trigger: 'change' }],
  }
  private downloadLoading = false
  private tempArtData = defaultArtData
  private password = ''
  private postData = {
    key: '',
    token: ''
  }
  private backUrl = 'http://q3lynq058.bkt.clouddn.com/'


  private onCropUploadSuccess(res: any, file: any) {
    this.tempArtData.artPicUrl = this.backUrl + res.key
  }

  private beforeAvatarUpload(file: any) {
    this.postData.key = file.name;
    const isJPG = file.type === "image/jpeg";
    const isPNG = file.type === "image/png";
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isJPG && !isPNG) {
      this.$message.error("图片只能是 JPG/PNG 格式!");
      return false;
    }
    if (!isLt2M) {
      this.$message.error("图片大小不能超过 2MB!");
      return false;
    }
  }


  private dateFormater(row: any, column: any) {
    var date = row[column.property];
    if (date == undefined) {
      return "";
    }
    return moment(date).format("YYYY年MM月DD日 HH:mm:ss");
  }

  created() {
    this.getList()
    this.genToken()
  }

  private async genToken() {
    let data = await getToken()
    this.postData.token = data.data
  }

  private async getList() {
    this.listLoading = true
    let formData = new FormData()
    formData.append('page', this.listQuery.page.toString())
    formData.append('size', this.listQuery.size.toString())
    const { data } = await getArts(formData)
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
    this.tempArtData = cloneDeep(defaultArtData)
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
        let { id, ...Data } = this.tempArtData
        let formData = new FormData()
        formData.append('artName', Data.artName)
        formData.append('artEmail', Data.artEmail)
        formData.append('artPassword', this.password)
        formData.append('artAvatar', Data.artAvatar)
        formData.append('roles', Data.roles)
        let datas = await createArt(formData)
        datas = datas.data
        console.log(datas)
        this.list.unshift(datas)
        this.dialogFormVisible = false
        this.password = ''
        this.$notify({
          title: '成功',
          message: '创建成功',
          type: 'success'
        })
      }
    })
  }

  private handleUpdate(row: any) {
    this.tempArtData = Object.assign({}, row)
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
        formData.append('id', this.tempArtData.id.toString())
        formData.append('artName', this.tempArtData.artName)
        formData.append('artEmail', this.tempArtData.artEmail)
        formData.append('roles', this.tempArtData.roles)
        formData.append('artAvatar', this.tempArtData.artAvatar)
        const { data } = await updateArt(formData)

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
      this.tempArtData = Object.assign({}, row)
      let formData = new FormData()
      formData.append('id', this.tempArtData.id.toString())
      const { data } = await deleteArt(formData)
      for (const v of this.list) {
        if (v.id == this.tempArtData.id) {
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
    const tHeader = ['id', 'artName', 'artEmail', 'registerTime', 'roles']
    const filterVal = ['id', 'artName', 'artEmail', 'registerTime', 'roles']
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
