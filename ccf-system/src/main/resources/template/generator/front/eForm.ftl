<template>
  <el-dialog :append-to-body="true" :visible.sync="dialog" :title="isAdd ? '新增' : '编辑'"
             :width="width"
             :fullscreen="isfullscreen"
             customClass="customDialog">
    <div class="dialog-header-group">
      <span @click="IsFullscreen"><i class="el-icon-copy-document"></i></span>
    </div>
    <el-row :gutter="15" class="formBG">
      <el-form ref="form" :model`="form" :rules="rules" size="small" label-width="120px">
        <#if columns??>
          <#list columns as column>
            <#if column.changeColumnName != '${pkChangeColName}'>
              <el-col :span="8">
                <el-form-item label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>" prop="${column.changeColumnName}">
                  <el-input v-model="form.${column.changeColumnName}"  clearable placeholder=""/>
                </el-form-item>
              </el-col>
            </#if>
          <#--    <#if column.changeColumnName != '${pkChangeColName}'>
                <el-form-item label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>" <#if column.columnKey = 'UNI'>prop="${column.changeColumnName}"</#if>>
                  <el-input v-model="form.${column.changeColumnName}"  clearable placeholder=""/>
                </el-form-item>
              </#if>-->

          </#list>
        </#if>
      </el-form>
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button type="text" @click="cancel">取消</el-button>
      <el-button :loading="loading" type="primary" @click="doSubmit">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import { add, edit } from '@/api/${changeClassName}'
  export default {
    props: {
      isAdd: {
        type: Boolean,
        required: true
      },
      display: {
        type: Boolean,
        required: false
      }
    },
    data() {
      return {
        loading: false, dialog: false,
        isfullscreen: false, // 全屏
        width: "70%",
        form: {
      <#if columns??>
      <#list columns as column>
      ${column.changeColumnName}: ''<#if column_has_next>,</#if>
      </#list>
      </#if>
    },
      rules: {
        <#list columns as column>
          ${column.changeColumnName}: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]<#if (column_has_next)>,</#if>
        </#list>
      }
    }
    },
    methods: {
      cancel() {
        this.dialog = false
      },
      doSubmit() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.loading = true
            if (this.isAdd) {
              this.doAdd()
            } else {
              this.doEdit()
            }
          } else {
            return false;
          }
        });
      },
      doAdd() {
        add(this.form).then(res => {
          this.dialog = false
          this.$notify({
            title: '添加成功',
            type: 'success',
            duration: 2500
          })
          this.loading = false
          this.$parent.init()
        }).catch(err => {
          this.loading = false
          console.log(err.response.data.message)
        })
      },
      doEdit() {
        edit(this.form).then(res => {
          this.dialog = false;
          this.$notify({
            title: '修改成功',
            type: 'success',
            duration: 2500
          })
          this.loading = false
          this.$parent.init()
        }).catch(err => {
          this.loading = false
          console.log(err.response.data.message)
        })
      },
      resetForm() {
        // this.$refs['form'].resetFields()
        this.form = {
        <#if columns??>
        <#list columns as column>
        ${column.changeColumnName}: ''<#if column_has_next>,</#if>
        </#list>
        </#if>
      }
      },
      // 全屏
      IsFullscreen() {
        this.isfullscreen = !this.isfullscreen
      }
    },
    computed: {
      storeOptions: function () {
        return this.$store.getters.gettersOptions;
      }
    }
  }
</script>

<style scoped>

</style>
