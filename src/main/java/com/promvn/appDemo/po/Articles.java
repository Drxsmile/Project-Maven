package com.promvn.appDemo.po;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章的实体类
 */
//把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
//@Document(collection="mongodb 对应 collection 名")
// 若未加 @Document ，该 bean save 到 mongo 的 articles collection
// 若添加 @Document ，则 save 到 articles collection
@Document(collection="articles")//可以省略，如果省略，则默认使用类名小写映射集合

public class Articles implements Serializable {
    //主键标识，该属性的值会自动对应mongodb的主键字段"_id"，如果该属性名就叫“id”,则该注解可以省略，否则必须写
    @Id
    private ObjectId id;//主键，也可以是string类型

//该属性对应mongodb的字段的名字，如果一致，则无需该注解
//    @Field（"link"）
    private String link; //文章链接
    private String content;//文章内容
    private Date date;//发布日期
    //添加了一个单字段的索引
    @Indexed //和在命令行里添加索引效果一样
    private String title;//标题
    private String author;//作者
    private Integer words;//字数
    private Integer read;//阅读数

    //复合索引
// @CompoundIndex( def = "{'words': 1, 'read': -1}")

    //getter and setter
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    @Override
    public String toString() {
        String s = "articles{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", words=" + words +
                ", read=" + read +
                '}';
        return s;
    }
}
