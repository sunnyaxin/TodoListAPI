# TodoListAPI
---
## todo-api 需求

实现`todo-api.yml`文件中所定义的api（文件符合swagger规范），以支持对于todo item的增删改查工作。
可以将 .yml 文件在 http://editor.swagger.io 打开，查看具体信息。

## 使用技术

使用`spring boot`来搭建rest api，数据库选用`mysql`

## 其他需求

- 需要提供data migration脚本（数据库结构可以自行设计）。可通过本地的数据库管理系统运行 src/main/resources/TodoList_2017-01-23.sql 来构建完成数据库迁移
- 单元测试需要达到100%的覆盖率（可以使用jacoco来生成单元测试覆盖率报告）。单元测试报告位于 build/reports/jacoco/test/html/index.html
- 请使用git来做版本控制，完成之后请提供包括测试代码在内的git repo链接 : git@github.com:hejiangle/TodoListAPI.git
