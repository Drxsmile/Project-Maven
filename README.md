# Project-Maven
based on spring-boot

## 分层逻辑

### dao层

唯一作用：让service层和database互动

接口定义了标准，需要在实现类定义自己想要的功能

使用MongoRepository接口子类实现增删改查功能

MongoTemplate提供了更复杂的query和更多功能



参考文档：

- [MongoTemplate官方文档](https://docs.spring.io/spring-data/mongodb/docs/3.0.0.RELEASE/reference/html/#mongo-template)
- [MongoRepository官方文档](https://spring.io/guides/gs/accessing-data-mongodb/)

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



## 连接Spring boot和MongoDB

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

## Controller

与前端页面进行交互

frontend ---> controller ---> service ---> dao ---> repository ---> mongoldb

Controller调用service的功能



参考文档：

- [springmvc中@RequestMapping/@GetMapping/@PostMapping三者的区别](https://blog.csdn.net/weixin_39220472/article/details/80813111?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)
- [Spring MVC各种提交形式以及参数接收（form表单提交以及Json提交）](https://blog.csdn.net/u014534808/article/details/84667859)

