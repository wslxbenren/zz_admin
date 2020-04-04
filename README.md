### 说明
1. 后端基于 Spring Boot 2.1.0 、 Jpa、 Spring Security
2. 前端基于 vue element

### 环境搭建
1. git clone https://gitee.com/hnxzy/biz_gov_admin.git --depth=1
2. 将项目根目录下 libs 目录下面的 3个 jar 包 安装到本地 maven 仓库 
    - com/xyz/ccf-common/ccf-common-2.1.jar
    - com/xyz/ccf-logging/ccf-logging-2.1.jar
    - com/xyz/ccf-tools/ccf-tools-2.1.jar
    
### 开发注意事项
1. 改动范围: com.xyz.modules.system 下, 其他包下面勿动
2. 严禁 git push -f 到远程仓库
3. 提交代码必须填写改动记录
4. 开发在 develop 上操作, master 勿动
