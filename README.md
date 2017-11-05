## 演示地址

[BadBlog](http://www.rexunil.com:8888)

## 简介

> `BadBlog` 集成 Editor.md 编辑器的个人博客系统 ,
>
> 用 [Shell 脚本](https://github.com/zhongzhixing/Scripts/blob/master/backup_database.sh)实现将数据库备份至码云私有仓库



##  实现功能

- 发布，编辑，删除文章
- 文章分类，归档
- 文章类别新建，编辑，删除
- 模糊查询
- 登录后台
- 通过邮箱验证码找回密码
- [Shell脚本](https://github.com/zhongzhixing/Scripts/blob/master/backup_database.sh) 备份数据库至码云私有仓库



## 使用技术

- 后端
  - 核心框架:  Spring Framework 4.3.10  + SpringBoot 1.5.6
  - 视图框架:  SpringMVC 4.3.10
  - 持久层框架: MyBatis 3.4.5
  - 模板引擎: Thymeleaf 2.1.5
- 前端
  -  JS库: jQuery 1.12.4
  -  CSS框架: Bootstrap3.3.7
- 其他
  -  Markdown编辑器: Editor.md 1.5.0
  -  粒子效果: particles.js 2.0.2



## 数据库设计

- [SQL 测试数据脚本](https://github.com/zhongzhixing/BadBlog/blob/master/badblog.sql)



![数据库表设计](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%A1%A8%E8%AE%BE%E8%AE%A1.PNG?raw=true)



## 运行效果
- [演示地址](http://rexunil.com:8888) | 内置tomcat端口已修改为:8888

- 首页

  ![首页](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E9%A6%96%E9%A1%B5.PNG?raw=true)

- 文章展示

  ![文章展示](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E6%96%87%E7%AB%A0%E5%B1%95%E7%A4%BA.PNG?raw=true)

- 编辑 || 新建文章

  ![编辑/新建文章](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E7%BC%96%E8%BE%91.PNG?raw=true)

- 分类

  ![分类](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E5%88%86%E7%B1%BB.PNG?raw=true)

- 搜索

  ![搜索](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E6%90%9C%E7%B4%A2.PNG?raw=true)

- 归档

  ![归档](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E5%BD%92%E6%A1%A3.PNG?raw=true)

- 后台首页

  ![后台首页](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E5%90%8E%E5%8F%B0%E9%A6%96%E9%A1%B5.PNG?raw=true)

- 分类管理

  ![分类管理](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E5%88%86%E7%B1%BB%E7%AE%A1%E7%90%86%E9%A6%96%E9%A1%B5.PNG?raw=true)

- 登录

  ![登录](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E7%99%BB%E5%BD%95.PNG?raw=true)

- 忘记密码

  ![忘记密码](https://github.com/zhongzhixing/MarkdownPictures/blob/master/badlog/%E5%BF%98%E8%AE%B0%E5%AF%86%E7%A0%81.PNG?raw=true)





