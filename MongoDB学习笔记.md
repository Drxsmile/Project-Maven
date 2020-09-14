# MongoDB学习笔记

## mac安装

官方的install guide使用homebrew安装，非常方便

而且homebrew会自动配置环境变量，省事！！

## 连接数据库

先启动数据库（MongoDB Community Edition），再用mongo把shell和数据库连接，就可以进入mongo shell，在命令行直接对数据库进行操作

默认连接到localhost，如果想连接别的，可以看[菜鸟连接数据库更多实例](https://www.runoob.com/mongodb/mongodb-connections.html)

用brew就自动连接配置了，不用再手动mongod

1. Run MongoDB Community Edition

- To run MongoDB (i.e. the [`mongod`](dfile:///Users/s/Library/Application Support/Dash/DocSets/MongoDB/MongoDB.docset/Contents/Resources/Documents/docs.mongodb.com/manual/reference/program/mongod/index.html#bin.mongod) process) **as a macOS service**, issue the following:

  ```
  brew services start mongodb-community@4.4
  ```

  To stop a [`mongod`](dfile:///Users/s/Library/Application Support/Dash/DocSets/MongoDB/MongoDB.docset/Contents/Resources/Documents/docs.mongodb.com/manual/reference/program/mongod/index.html#bin.mongod) running as a macOS service, use the following command as needed:

  ```
  brew services stop mongodb-community@4.4
  ```

  下面是手动的，进行了上面的就不用做下面的了

- To run MongoDB (i.e. the [`mongod`](dfile:///Users/s/Library/Application Support/Dash/DocSets/MongoDB/MongoDB.docset/Contents/Resources/Documents/docs.mongodb.com/manual/reference/program/mongod/index.html#bin.mongod) process) **manually as a background process**, issue the following:

  ```
  mongod --config /usr/local/etc/mongod.conf --fork
  ```

  To stop a [`mongod`](dfile:///Users/s/Library/Application Support/Dash/DocSets/MongoDB/MongoDB.docset/Contents/Resources/Documents/docs.mongodb.com/manual/reference/program/mongod/index.html#bin.mongod) running as a background process, connect to the [`mongod`](dfile:///Users/s/Library/Application Support/Dash/DocSets/MongoDB/MongoDB.docset/Contents/Resources/Documents/docs.mongodb.com/manual/reference/program/mongod/index.html#bin.mongod)from the **mongo** shell, and issue the [`shutdown`](dfile:///Users/s/Library/Application Support/Dash/DocSets/MongoDB/MongoDB.docset/Contents/Resources/Documents/docs.mongodb.com/manual/reference/command/shutdown/index.html#dbcmd.shutdown) command as needed.

查询数据库是否运行中，如果没运行，mongo会报错，如果没报错就是连上了

Both methods use the `/usr/local/etc/mongod.conf` file created during the install. You can add your own MongoDB [configuration options](dfile:///Users/s/Library/Application Support/Dash/DocSets/MongoDB/MongoDB.docset/Contents/Resources/Documents/docs.mongodb.com/manual/reference/configuration-options/index.html) to this file as well.

To verify that MongoDB is running, search for `mongod` in your running processes:

```
ps aux | grep -v grep | grep mongod
```

You can also view the log file to see the current status of your `mongod` process: `/usr/local/var/log/mongodb/mongo.log`.

![报错信息](/Users/s/Library/Application%20Support/typora-user-images/image-20200827114004735.png)

## 基础操作

### **数据库操作**

```mongo
show dbs //查看所有数据库
db //查看当前正在使用的数据库
use db_name // 创建名为db_name的数据库（会自动设为当前使用数据库）
db.dropDatabase() //remove the current database
```

MongoDB 中默认的数据库为 test，如果你没有创建新的数据库，集合将存放在 test 数据库中

### **集合操作**

```mongo
show collections // show all collections in the current database
show tables // show all collections in the current database
db.collection_name.drop() // remove the collection named collection_name
db.createCollection(name, options) //create a collection
db.collection_name.insert() //如果数据库里没有集合，往数据库中插入数据，会自动创建集合
```

参数说明：

- name: 要创建的集合名称
- options: 可选参数, 指定有关内存大小及索引的选项

options 可以是如下参数：

| 字段   | 类型 | 描述                                                         |
| :----- | :--- | :----------------------------------------------------------- |
| capped | 布尔 | （可选）如果为 true，则创建固定集合。固定集合是指有着固定大小的集合，当达到最大值时，它会自动覆盖最早的文档。 **当该值为 true 时，必须指定 size 参数。** |
| size   | 数值 | （可选）为固定集合指定一个最大值，即字节数。 **如果 capped 为 true，也需要指定该字段。** |
| max    | 数值 | （可选）指定固定集合中包含文档的最大数量。                   |

在插入文档时，MongoDB 首先检查固定集合的 size 字段，然后检查 max 字段。

例子：创建固定集合 mycol，整个集合空间大小 6142800 KB, 文档最大个数为 10000 个。

```
db.createCollection("col", {capped : true, size : 6142800, max : 10000})
```

to learn more, [click here](https://docs.mongodb.com/manual/mongo/#working-with-the-mongo-shell)

### **文档操作**

1. 插入文档

- 向名为collection_name的集合中插入一个文档

```mongo
db.collection_name.insertOne(
		<document>,
   {
      writeConcern: <document>
   }
)
```

- 插入多个文档

```mongo
db.collection_name.insertMany(
   [ <document 1> , <document 2>, ... ],
   {
      writeConcern: <document>,
      ordered: <boolean>
   }
)
```

参数说明：

- document：要写入的文档。
- writeConcern：写入策略，默认为 1，即要求确认写操作，0 是不要求。
- ordered：指定是否按顺序写入，默认 true，按顺序写入。

```mongo
db.docrefiner.insertMany(
 [{ "title":"article1", "author" : "Alice", "length" : 452, "published" :false},
{ "title":"article2", "author" : "Bob", "length" : 561, "published" : true},
{ "title":"article3", "author" : "Skylar", "length" : 374, "published" :false},
{ "title":"article4", "author" : "Alex", "length" : 986, "published" : true},
{ "title":"article5", "author" : "Lucy", "length" : 249, "published" :false}]
)
```

2. 更新文档

**基础语法：** db.集合名.update（条件， 新数据  [,是否新增，是否修改多条]）

3. 删除文档

语法：db.集合名.remove(条件 [, 是否删除一条] )

注意：是否删除一条 true是,false否 默认

4. 查询文档

```mongo
db.collection_name.find() //显示当前集合所有文档
db.collection_name.find().pretty() //比上面那个格式好看，分行呈现
db.collection_name.findOne() //只返回一个文档
```



## 项目实战

### 1. 抓取数据存入excel，并导入mongodb

步骤一、将docs.xlsx 另存为 docs.csv

借助https://cloudconvert.com/xlsx-to-csv

步骤二、执行 imongoimport命令

mongoimport  -d docs -c articles --type csv --headerline  --file docs.csv

参数说明：

d:数据库名

c:collection名

type:文件类型，指明是csv文件

headline:指明第一行是列名，不需要导入

file:csv文件路径及名字

### 2. 修改mongodb中数据类型

```
db.getCollection('collection_name').find({'field_name' : { $type : 2 }}).forEach(function(x) {
    x.field_name = NumberInt(x.field_name);
    db.getCollection('collection_name').save(x);
})
```

>字段类型编号:
>
>1 Double 浮点型 
>2 String UTF-8字符串都可表示为字符串类型的数据 
>3 Object 对象，嵌套另外的文档 
>4 Array 值的集合或者列表可以表示成数组 
>5 Binary data 二进制 
>7 Object id 对象id是文档的12字节的唯一 ID 系统默认会自动生成 
>8 Boolean 布尔类型有两个值TRUE和FALSE 
>9 Date 日期类型存储的是从标准纪元开始的毫秒数。不存储时区 
>10 Null 用于表示空值或者不存在的字段 
>11 Regular expression 采用js 的正则表达式语法 
>13 JavaScript code 可以存放Javasript 代码 
>14 Symbol 符号 
>15 JavaScript code with scope 
>16 32-bit integer 32位整数类型 
>17 Timestamp 特殊语义的时间戳数据类型 
>18 64-bit integer 64位整数类型

```
db.getCollection('articles').find({'words' : { $type : 2 }}).forEach(function(x) {
    x.words = parseInt(x.words);
    db.getCollection('articles').save(x);
})
```



