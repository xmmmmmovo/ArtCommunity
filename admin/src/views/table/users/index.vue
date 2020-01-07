<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input
        v-model="listQuery.title"
        :placeholder="搜索用户名"
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
        label="registerTime"
        align="center"
        :formatter="dateFormater"
        prop="registerTime"
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
        label="userEmail"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.userEmail }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="userBio"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.userBio }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="userSex"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.userSex }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="userAvatar"
        width="130px"
      >
        <template slot-scope="scope">
          <el-image
            :fit="contain"
            :src='scope.row.userAvatar'
          >
          </el-image>
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
            :model="tempUserData"
            label-position="left"
            label-width="100px"
            style="width: 400px; margin-left:50px;"
          >
            <el-form-item
              label="用户姓名"
              prop="userName"
            >
              <el-input v-model="tempUserData.userName" />
            </el-form-item>
            <el-form-item
              label="用户邮箱"
              prop="userEmail"
            >
              <el-input v-model="tempUserData.userEmail" />
            </el-form-item>
            <el-form-item
              label="用户简介"
              prop="userBio"
            >
              <el-input v-model="tempUserData.userBio" />
            </el-form-item>
            <el-form-item
              v-if="dialogStatus==='create'"
              label="用户密码"
            >
              <el-input v-model="password" />
            </el-form-item>
            <el-form-item
              label="性别"
              prop="sex"
            >
              <el-select
                v-model="tempUserData.userSex"
                class="filter-item"
                placeholder="男"
              >
                <el-option
                  key="男"
                  label="男"
                  value="男"
                />
                <el-option
                  key="女"
                  label="女"
                  value="女"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item
              label="用户头像"
            >
              <el-upload
                class="avatar-uploader"
                action="https://upload-z1.qiniup.com"
                :show-file-list="false"
                :data="postData"
                :on-success="onCropUploadSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="tempUserData.userAvatar" :src="tempUserData.userAvatar" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
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
  getUsers,
  createUser,
  getUserDetail,
  updateUser,
  deleteUser,
  defaultUserData
} from '@/api/users'
import {
  getToken
} from '@/api/token'
import {IUserData, IArticleData} from '@/api/types'
import { exportJson2Excel } from '@/utils/excel'
import { formatJson } from '@/utils'
import Pagination from '@/components/Pagination/index.vue'
import * as moment from 'moment'
import {genUpToken} from "@/utils/token";

@Component({
  name: 'UsersTable',
  components: {
    Pagination
  }
})
export default class extends Vue {
  private list: IUserData[] = []
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
    userName: [{ required: true, message: '姓名是必须的', trigger: 'change' }],
    userEmail: [{ required: true, message: '邮箱是必须的', trigger: 'change' }],
  }
  private downloadLoading = false
  private tempUserData = defaultUserData
  private password = ''
  private postData = {
    key: '',
    token: ''
  }
  private backUrl = 'http://q3lynq058.bkt.clouddn.com/'


  private onCropUploadSuccess(res: any, file: any) {
    this.tempUserData.userAvatar = this.backUrl + res.key
  }

  private beforeAvatarUpload(file: any) {
    this.postData.key = file.uid.toString() + file.name;
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
    const { data } = await getUsers(formData)
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
    this.tempUserData = cloneDeep(defaultUserData)
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
        let { id, ...Data } = this.tempUserData
        let formData = new FormData()
        formData.append('userName', Data.userName)
        formData.append('userEmail', Data.userEmail)
        formData.append('userPassword', this.password)
        formData.append('userAvatar', Data.userAvatar)
        formData.append('userBio', Data.userBio)
        formData.append('userSex', Data.userSex)
        let datas = await createUser(formData)
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
    this.tempUserData = Object.assign({}, row)
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
        formData.append('id', this.tempUserData.id.toString())
        formData.append('userName', this.tempUserData.userName)
        formData.append('userEmail', this.tempUserData.userEmail)
        formData.append('userAvatar', this.tempUserData.userAvatar)
        formData.append('userBio', this.tempUserData.userBio)
        formData.append('userSex', this.tempUserData.userSex)
        const { data } = await updateUser(formData)

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
      this.tempUserData = Object.assign({}, row)
      let formData = new FormData()
      formData.append('id', this.tempUserData.id.toString())
      const { data } = await deleteUser(formData)
      for (const v of this.list) {
        if (v.id == this.tempUserData.id) {
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
    const tHeader = ['id', 'userName', 'userEmail', 'registerTime']
    const filterVal = ['id', 'userName', 'userEmail', 'registerTime']
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
