<#--noinspection ALL-->
<template>
  <div class="app-container">
    <!--工具栏-->
    <div class="head-container">
      <#if hasQuery>
        <!-- 搜索 -->
        <el-form ref="queryForm" :model="query" label-width="120px"  :inline="true">
          <el-input v-model="query.value" clearable placeholder="输入搜索内容" style="width: 200px;" class="filter-item" @keyup.enter.native="toQuery"/>
          <el-select v-model="query.type" clearable placeholder="类型" class="filter-item">
            <el-option v-for="item in queryTypeOptions" :key="item.key" :label="item.display_name" :value="item.key"/>
          </el-select>
        </el-form>
        <el-row :gutter="15">
          <el-col :span="18">
            <el-button
                    v-permission="['ADMIN','BIZACTUALFLOATPEOPLE_ALL','BIZACTUALFLOATPEOPLE_CREATE']"
                    class="filter-item"
                    size="mini"
                    type="primary"
                    icon="el-icon-plus"
                    @click="add">新增
            </el-button>
            <el-button class="filter-item" size="mini" type="success" icon="el-icon-upload">导入</el-button>
            <el-button class="filter-item" size="mini" type="success" icon="el-icon-download">导出</el-button>
          </el-col>
          <el-col :span="6"  style="text-align:right">
            <el-button class="filter-item" size="mini" type="success" icon="el-icon-search" @click="toQuery">搜索</el-button>
          </el-col>
        </el-row>
      </#if>
    </div>
    <!--表单组件-->
    <eForm ref="form" :is-add="isAdd" :display="display"/>
    <!--表格渲染-->
    <el-table v-loading="loading" :data="data" size="small" style="width: 100%;">
      <#if columns??>
        <#list columns as column>
          <#if column.columnShow = 'true'>
            <#if column.columnType != 'Timestamp'>
              <el-table-column prop="${column.changeColumnName}" label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>"/>
            <#else>
              <el-table-column prop="${column.changeColumnName}" label="<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.${column.changeColumnName}) }}</span>
                </template>
              </el-table-column>
            </#if>
          </#if>
        </#list>
      </#if>
      <el-table-column v-if="checkPermission(['ADMIN','${upperCaseClassName}_ALL','${upperCaseClassName}_EDIT','${upperCaseClassName}_DELETE'])" label="操作" width="150px" align="center">
        <template slot-scope="scope">
          <el-button v-permission="['ADMIN','${upperCaseClassName}_ALL','${upperCaseClassName}_EDIT']" size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.row)"/>
          <el-popover
                  v-permission="['ADMIN','${upperCaseClassName}_ALL','${upperCaseClassName}_DELETE']"
                  :ref="scope.row.${pkChangeColName}"
                  placement="top"
                  width="180">
            <p>确定删除本条数据吗？</p>
            <div style="text-align: right; margin: 0">
              <el-button size="mini" type="text" @click="$refs[scope.row.${pkChangeColName}].doClose()">取消</el-button>
              <el-button :loading="delLoading" type="primary" size="mini" @click="subDelete(scope.row.${pkChangeColName})">确定</el-button>
            </div>
            <el-button slot="reference" type="danger" icon="el-icon-delete" size="mini"/>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <el-pagination
            :total="total"
            :current-page="page + 1"
            style="margin-top: 8px;"
            layout="total, prev, pager, next, sizes"
            @size-change="sizeChange"
            @current-change="pageChange"/>
  </div>
</template>

<script>
  import checkPermission from '@/utils/permission'
  import initData from '@/mixins/initData'
  import { del } from '@/api/${changeClassName}'
  <#if hasTimestamp>
  import { parseTime } from '@/utils/index'
  </#if>
  import eForm from './form'
  export default {
    components: { eForm },
    mixins: [initData],
    data() {
      return {
        delLoading: false,
        isAdd:false,
        display:false,
        createTimeTem:null,
        <#if hasQuery>
        queryTypeOptions: [
          <#if queryColumns??>
          <#list queryColumns as column>
          { key: '${column.changeColumnName}', display_name: '<#if column.columnComment != ''>${column.columnComment}<#else>${column.changeColumnName}</#if>' }<#if column_has_next>,</#if>
          </#list>
          </#if>
        ]
        </#if>
      }
    },
    created() {
      this.$nextTick(() => {
        this.init()
      })
    },
    methods: {
      <#if hasTimestamp>
      parseTime,
      </#if>
      checkPermission,
      beforeInit() {
        this.url = 'api/${changeClassName}'
        const sort = 'createTime,desc'
        /*if (this.createTimeTem != null && this.createTimeTem.length > 0) {
          if(this.createTimeTem[0] === this.createTimeTem[1]){
            this.createTimeTem[1] = this.createTimeTem[1].substr(0,11) + "23:59:59";
          }
          this.query.createTime = this.createTimeTem[0] + ',' + this.createTimeTem[1]
        } else {
          this.query.createTime = null;
        }*/

        this.params = { page: this.page, size: this.size, sort: sort,...this.query}
        /*
<#if hasQuery>
      </#if>*/
        return true
      },
      subDelete(${pkChangeColName}) {
        this.delLoading = true
        del(${pkChangeColName}).then(res => {
          this.delLoading = false
          this.$refs[${pkChangeColName}].doClose()
          this.dleChangePage()
          this.init()
          this.$notify({
            title: '删除成功',
            type: 'success',
            duration: 2500
          })
        }).catch(err => {
          this.delLoading = false
          this.$refs[${pkChangeColName}].doClose()
          console.log(err.response.data.message)
        })
      },
      add() {
        this.isAdd = true
        this.display = false
        this.$refs.form.dialog = true
        this.$refs.form.resetForm()
      },
      edit(data) {
        this.isAdd = false
        this.display = true
        const _this = this.$refs.form
        _this.form = {
        <#if columns??>
        <#list columns as column>
        ${column.changeColumnName}: data.${column.changeColumnName}<#if column_has_next>,</#if>
        </#list>
        </#if>
      }
        _this.dialog = true
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
