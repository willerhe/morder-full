<template>
  <div class="app-container">
    <!--查询  -->
    <el-row>
<!--      <el-input style="width:200px;" v-model="tableQuery.nick" placeholder="商品名"></el-input>-->
<!--      <span style="margin-right: 15px;"></span>-->
<!--      <el-tooltip class="item" content="搜索" placement="top" >-->
<!--        <el-button icon="el-icon-search" circle @click="fetchData(1)" v-perm="'b:user:query'"></el-button>-->
<!--      </el-tooltip>-->
    </el-row>
    <div style="margin-bottom: 30px;"></div>
    <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleCreate" v-perm="'b:user:add'">{{textMap.create}}</el-button>
    <div style="margin-bottom: 30px;"></div>
    <!--列表-->
    <el-table style="width: 100%"
              :data="tableData"
              v-loading.body="tableLoading"
              element-loading-text="加载中"
              border fit highlight-current-row>
      <el-table-column prop="coverUrl" label="图片">
        <template slot-scope="scope">
          <img :src="scope.row.coverUrl" alt="" style="width: 60px;height: 60px">
        </template>

      </el-table-column>
      <el-table-column prop="type" label="类型"></el-table-column>
      <el-table-column prop="title" label="名称"></el-table-column>
      <el-table-column prop="detail" label="描述"></el-table-column>
      <el-table-column prop="tag" label="标签"></el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>

      <el-table-column prop="created" label="创建时间">
        <template slot-scope="scope">
          <span v-text="parseTime(scope.row.created)"></span>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
<!--          <el-tooltip content="编辑" placement="top">-->
<!--            <el-button @click="handleUpdate(scope.$index,scope.row)" size="medium" type="info" icon="el-icon-edit" circle plain></el-button>-->
<!--          </el-tooltip>-->
          <el-tooltip content="删除" placement="top">
            <el-button @click="handleDelete(scope.$index,scope.row)" size="medium" type="danger" icon="el-icon-delete" circle plain></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-bottom: 30px;"></div>
    <!--分页-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="tablePage.current"
      :page-sizes="[10, 20, 30, 40, 50]"
      :page-size="tablePage.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tablePage.total">
    </el-pagination>

  </div>
</template>

<script>

  import optionApi from '@/api/option'
  import goodsApi from '@/api/goods'
  import {parseTime, resetTemp} from '@/utils'
  import {root,confirm,pageParamNames} from '@/utils/constants'
  import debounce from 'lodash/debounce'

  export default {

    name: 'UserManage',

    data() {

      let validateName = (rule, value, callback) => {
        if (this.dialogStatus == 'create' && value === '') {
          callback(new Error('必填'));
        } else {
          callback();
        }
      };

      let validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.temp.pwd2 !== '') {
            this.$refs.dataForm.validateField('pwd2');
          }
          callback();
        }
      };

      let validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value != this.temp.pwd) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      return {
        parseTime: parseTime,
        tableLoading: false,
        tableData: [],
        tableQuery: {
          nick: null
        },
        tablePage: {
          current: null,
          pages: null,
          size: null,
          total: null
        },
        dialogFormVisible: false,
        editRolesDialogVisible: false,
        dialogStatus: '',
        temp: {
          idx: null, //tableData中的下标
          uid: null,
          uname: null,
          nick: null,
          pwd: null,
          pwd2: null,
          created: null,
          updated: null
        },
        textMap: {
          update: '编辑商品',
          create: '新增商品'
        },
        rules: {
          uname: [{validator: validateName, trigger: 'blur'}],
          pwd: [{validator: validatePass, trigger: 'blur'}],
          pwd2: [{validator: validatePass2, trigger: 'change'}]
        },
        checkAll: false,
        isIndeterminate: true,
        //所有角色(管理员除外)
        roleOptions:[],
        roleMap: new Map(),
        // 更新用户的角色的数据
        updateUserRolesData: {
          idx: null,
          uid: null,
          rids: []
        },
      }

    },

    created() {
      this.initData()
      this.fetchData()
    },

    watch: {
      //延时查询
      'tableQuery.nick': debounce(function () {
        this.fetchData()
      }, 500)
    },//watch

    methods: {

      initData(){
        //所有角色选项
        optionApi.listRoleOptions().then(res => {
          res.data.options.forEach(obj => {
            if(obj.val2!=root.rval){//排除管理员
              this.roleOptions.push(obj)
              this.roleMap.set(obj.id, obj.val)
            }
          })
        })
      },

      hasAdminRole(row){
        if(row && row.roleList){
          return row.roleList.some(role=>role.rval==root.rval)
        }
        return false
      },

      //全选
      handleCheckAllChange(val) {
        let allRids = this.roleOptions.map(role => role.id)
        this.updateUserRolesData.rids = val ? allRids : [];
        this.isIndeterminate = false;
      },

      //分页
      handleSizeChange(val) {
        this.tablePage.size = val;
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.tablePage.current = val;
        this.fetchData();
      },

      //查询
      fetchData(current) {
        if(current){
          this.tablePage.current = current
        }
        this.tableLoading = true
        goodsApi.list(this.tableQuery, this.tablePage).then(res => {
          this.tableData = res.data.page.records
          this.tableLoading = false
          pageParamNames.forEach(name => this.$set(this.tablePage, name, res.data.page[name]))
        })
      },

      //更新
      handleUpdate(idx, row) {
        this.temp = Object.assign({}, row) // copy obj
        this.temp.idx = idx
        this.temp.pwd = null
        this.temp.pwd2 = null
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => this.$refs['dataForm'].clearValidate())
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (!valid) return
          const tempData = Object.assign({}, this.temp)//copy obj
          goodsApi.updateUser(tempData).then(res => {
            tempData.updated = res.data.updated
            this.tableData.splice(tempData.idx, 1, tempData)
            this.dialogFormVisible = false
            this.$message.success("更新成功")
          })
        })
      },


      handleUpdateUserRoles(idx, row) {
        this.updateUserRolesData = {
          idx: idx,
          uid: row.uid,
          rids: row.roleList.map(role=>role.rid)
        }
        // 显示弹窗
        this.editRolesDialogVisible = true
      },

      checkUpdateUserRolesData() {
        const noRolesSelected = this.updateUserRolesData && this.updateUserRolesData.rids && this.updateUserRolesData.rids.length ==0;
        if(noRolesSelected){
          this.$confirm('当前没有选中任何角色，会清除该用户已有的角色, 是否继续?', '提示', confirm).then(() => {
            this.invokeUpdateUserRolesApi()
          }).catch(() => {
            this.$message("已取消编辑用户角色");
          });
        }else{
          this.invokeUpdateUserRolesApi()
        }
      },

      invokeUpdateUserRolesApi(){
        goodsApi.updateUserRoles(this.updateUserRolesData).then(res => {
          let newRoles = this.updateUserRolesData.rids.map(rid=>{
            let rname = this.roleMap.get(rid);
            if(rname) return {rid,rname}
          })
          this.tableData[this.updateUserRolesData.idx].roleList = newRoles
          this.editRolesDialogVisible = false
          this.$message.success("更新成功")
        })
      },

      //删除
      handleDelete(idx, row) {
        this.$confirm('您确定要永久删除该用户？', '提示', confirm).then(() => {
          goodsApi.delete([row]).then(res => {
            this.tableData.splice(idx, 1)
            --this.tablePage.total
            this.dialogFormVisible = false
            this.$message.success("删除成功")
          })
        }).catch(() => {
          this.$message.info("已取消删除")
        });

      },

      //新增
      handleCreate() {
        this.$router.push('/goods/add')

      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (!valid) return;
          goodsApi.addUser(this.temp).then((res) => {
            this.temp.uid = res.data.uid;//后台传回来新增记录的id
            this.temp.created = res.data.created;//后台传回来新增记录的时间
            this.temp.roleList = []
            this.tableData.unshift(Object.assign({},this.temp))
            ++this.tablePage.total
            this.dialogFormVisible = false
            this.$message.success("添加成功")
          })
        })
      },

    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .role-checkbox{
    margin-left: 0px;
    margin-right: 15px;
  }
</style>
