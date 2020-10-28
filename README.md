

# Project-Maven

a web application based on spring-boot, mongodb, mvc, jsp

[quick start guide](https://spring.io/guides/gs/spring-boot/)

## 1. 创建基于maven的spring boot 框架

有两种方式，可以创建

1. 使用spring boot官网提供的Spring Initializr

   https://start.spring.io/ 

   ![Spring initializr](/Users/s/Library/Application%2520Support/typora-user-images/image-20200826213233602.png)

2. 在Intcellij IDEA中new project 然后选择spring initializer

![image-20200914100133834](/Users/s/Library/Application%2520Support/typora-user-images/image-20200914100133834.png)

## 2. 根据需要注入相关依赖到pom.xml

- web：引入web开发相关包
- mongodb：操作数据库
- lombok：简化实体类书写
- devtools：热部署

参考文档：[idea配置springboot热部署终极解决办法，解决热部署失效问题](https://blog.csdn.net/u012799495/article/details/104804295/)

- jsp：解析前端jsp页面

## 3. 创建AppController

连接前端和后端，与前端页面进行交互

frontend ---> controller ---> service ---> dao ---> repository ---> mongoldb

Controller调用service的功能

参考文档：

- [springmvc中@RequestMapping/@GetMapping/@PostMapping三者的区别](https://blog.csdn.net/weixin_39220472/article/details/80813111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)
- [Spring MVC各种提交形式以及参数接收（form表单提交以及Json提交）](https://blog.csdn.net/u014534808/article/details/84667859)
- [SpringMVC Controller与页面 用Form表单传值方式总结](https://blog.csdn.net/lizhengze1117/article/details/107784342?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param)



## 4. 创建webapp包和jsp包

在jsp包里放前端页面jsp

jsp如何简单书写？

- 学习html
- 学习jstl

## 5. 在resource里添加配置

- application.properties（jsp相关配置）
- application.yaml（spring date mongodb的配置数据源方式）

## 6. 分层逻辑

### dao层

唯一作用：让service层和database互动

接口定义了标准，需要在实现类定义自己想要的功能

使用MongoRepository接口子类实现增删改查功能

MongoTemplate提供了更复杂的query和更多功能



参考文档：

- [MongoTemplate官方文档](https://docs.spring.io/spring-data/mongodb/docs/3.0.0.RELEASE/reference/html/#mongo-template)
- [MongoRepository官方文档](https://spring.io/guides/gs/accessing-data-mongodb/)
- [MongoDB学习笔记](https://github.com/Drxsmile/Project-Maven/blob/master/MongoDB学习笔记.md)

### object/type层

在关系型数据库中，每一个table对应一个object，因为table比较多，所以object也比较多；但是在mongodb中比较少，因为

1. 注入lombok相关依赖，使用@Data注解简化代码

> [`@Data`](https://projectlombok.org/features/Data) 
>
> All together now: A shortcut for `@ToString`, `@EqualsAndHashCode`, `@Getter` on all fields, and `@Setter` on all non-final fields, and `@RequiredArgsConstructor`!

参考文档:https://projectlombok.org/features/all

2. MongoDB相关注解

这里可以先不写，在读写操作时再写注解

### service层

需要实现的业务逻辑api

接口定义标准和注释，实现类中写方法的具体内容



### business层

和其他service互动的api



## 7. 连接Spring boot和MongoDB

### 注入相关依赖&配置

1. Spring Data MongoDB

参考文档：

- [官方文档](https://docs.spring.io/spring-data/mongodb/docs/3.0.0.RELEASE/reference/html/#mongo.core)
- [Which's the difference between](https://stackoverflow.com/questions/52425966/spring-spring-data-mongodb-or-spring-boot-starter-data-mongodb)

```
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-mongodb</artifactId>
</dependency>
```

and,

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

`spring-boot-starter-data-mongodb` is a spring boot starter pom. It contains configuration classes for Spring Boot. It also includes the `spring-data-mongodb` library. For more information on starters: [spring-boot-starters](https://www.baeldung.com/spring-boot-starters)

2. MongoDB Java Driver

参考文档

- [官方文档](https://docs.mongodb.com/drivers/java)
- [菜鸟教程java连接mongodb数据库](https://www.runoob.com/mongodb/mongodb-java.html)
- [java 连接 mongodb 及使用](https://www.cnblogs.com/ooo0/p/11362933.html)

### Spring boot中mongodb相关注解

>@Document
>
>标注在实体类上，声明为mongodb的文档，可以通过collection参数指定这个类对应的集合

>@Indexed 索引
>
>@Indexed(unique = true) 唯一索引
>
>@CompoundIndex 复合索引
>
>```
>@CompoundIndexes({
>@CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
>})
>```

>@TextIndexed
>
>text indexes can include any field whose value is a string or an array of string elements.
>
>

>@Transient
>
>被该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性。

参考文档：

- [SpringBoot中MongoDB注解概念及使用](https://blog.csdn.net/tianyaleixiaowu/article/details/73530679)
- [Text index官方文档](https://docs.mongodb.com/manual/core/index-text/)
- [text search官方文档](https://docs.mongodb.com/manual/text-search/)
- [Full-text Queries](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.textsearch)

## 8. test

1. Unit Test (测试自己写的方法代码逻辑)
   - Mockito    org.mockito
2. Integration (测试自己与其他service互动)
3. Scenario (自身service API测试)

## 9. 运行项目

terminal里面跑“./mvnw spring-boot:run”

看localhost:8080及以下url



## 搜索算法

### inverted files

倒排索引记录每个词的文章数量，文章id，位置



### crawler

- 爬取文章，update倒排索引，找的时候快

- href link可能是加密过的，也可能没加密，可以实现页面的跳转

- 存html

- 广度优先bfs用的多

  好处：只需要维护一个queue和set

  坏处：

- 深度优先dfs

  好处：

  坏处：页面可能会循环跳转；

- Page rank algorithm

  google

  被更多文章指向的文章，被更高权重的文章指向的文章，权重越高

- 一般从新闻类网站开始爬 ，搜集到的东西比较广

- server以树的形式存在，我的请求在ip和server之间走一个最短路径

- 如果有网站更新或者挂了，指数形式请求或者放在queue最后面

- ternary trie 没有数据库能存这样的数据结构

### TernarySearchTrie

object层添加实体类

service层添加service接口和实现类



**生成平衡树**：

wordlist排序，从中间值开始将每个字符放到树里

inverted index的keyset就是所有word



**查询**：

从前端返回要查询的string，然后遍历树找到可能的word的list，根据词频排序，排序后的list返回到前端



词频放在map里，查询到的词列表，再



点击search按钮，跳到autocomplete.jsp页面

搜索框里打字，出现提示列表

点击列表中的word，返回搜索结果的success.jsp页面









参考文档：

1. 前端

- [如何实现简单的自动提示(autocomplete)填充搜索功能java代码。](https://blog.csdn.net/happyboy214117/article/details/47720815)
- [js 表单操作form](https://www.cnblogs.com/cqming/p/10821527.html)



2. 后端

- [Trie和Ternary Search Tree介绍](https://www.cnblogs.com/edwinchen/p/4580190.html)
- [Ternary Search Tree Java实现](https://www.cnblogs.com/edwinchen/p/4580199.html)
- [三叉搜索树（Ternary Search Trie）和中文分词原理分析](http://ddrv.cn/a/44546)
- [java Trie实现英文单词查找树 搜索自动提示](https://blog.csdn.net/dreamzuora/article/details/85024052)

  

