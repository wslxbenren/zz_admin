### 说明
1. 后端基于 Spring Boot 2.1.0 、 Jpa、 Spring Security
2. 前端基于 vue element

### 环境搭建
1. git clone https://gitee.com/hnxzy/biz_gov_admin.git --depth=1
2. libs 目录下 覆盖本地依赖.zip 解压到本地仓库
    
### 开发须知
1. 改动范围: com.xyz.modules.system 下, 其他包下面勿动
2. 严禁 git push -f 到远程仓库
3. 提交代码必须填写改动记录
4. 开发在 develop 上操作, master 勿动
5. 业务代码开发
   - 所有实体在更新操作的时候必须更新审计字段: 创建人/创建时间/修改人/修改时间/所属单位
   - 查询数据需要上级向下级查询，可查询子单位，子单位的单位…一直到最下级。
   - 数据的检验，后台仅验证空即可。暂时不做过多检验
6. 日志: 本地开发指定日志目录, 服务端部署指定日志目录, 不需要提交日志配置文件的修改
   多用 debug/trace 做
7. 不要对数据库的表结构进行更改, 如需更改, 提给 康佳
8. 提交代码之前做单元测试, 测试通过和测试代码一并提交, 
9. idea 安装 阿里巴巴代码规约插件, 可以开启实时扫描, 尽量规范