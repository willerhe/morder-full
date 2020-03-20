<template>
  <div class="app-container" style="display: flex;justify-content: center">
    <div style="width: 60%">
      <h2>添加商品</h2>
      <el-upload
        class="mb-normal"
        :action="uploadPath"
        :on-success="uploadSuccess"
        :limit="1"
        list-type="picture-card">
        <i slot="default" class="el-icon-plus"></i>
        <div slot="file" slot-scope="{file}">
          <img
            class="el-upload-list__item-thumbnail"
            :src="file.url" alt=""
          >
          <span class="el-upload-list__item-actions">
        <span
          class="el-upload-list__item-preview"
          @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="handleDownload(file)"
        >
          <i class="el-icon-download"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>
        </div>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>

      <el-select v-model="form.type" placeholder="请选择分类" class="mb-normal">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>


      <el-input v-model="form.title" placeholder="商品标题" class="mb-normal"></el-input>
      <el-input v-model="form.detail" placeholder="描述" class="mb-normal"></el-input>
      <el-input v-model="form.price" placeholder="价格" class="mb-normal"></el-input>
      <el-input v-model="form.tag" placeholder="标签" class="mb-normal"></el-input>
      <el-button type="primary" @click="add">添加</el-button>
    </div>
  </div>
</template>

<script>
  import goods from "../../api/goods";

  export default {
    name: "menu1_add",
    data() {
      return {
        uploadPath: process.env.BASE_API + '/goods/cover',
        form: {},
        dialogImageUrl: '',
        dialogVisible: false,
        disabled: false,
        options: [{
          value: '特色推荐',
          label: '特色推荐'
        }, {
          value: '港式奶茶',
          label: '港式奶茶'
        }, {
          value: '泰式奶茶',
          label: '泰式奶茶'
        }, {
          value: '热销',
          label: '热销'
        }],
        value: ''
      }
    },
    methods: {
      add() {
        console.log(this.form)
        goods.add([this.form]).then(res => {
          this.$message.success('添加成功')
          this.$router.go(-1)
        })
      },
      uploadSuccess: function (response, file, fileList) {
        console.log(response, file, fileList)
        this.form.coverUrl = response.oper
      },
      handleRemove(file) {
        console.log(file);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      handleDownload(file) {
        console.log(file);
      }
    }
  }
</script>

<style scoped>

</style>
