package com.promvn.appDemo.service;


import com.promvn.appDemo.AppDemoApplication;
import com.promvn.appDemo.po.Articles;
import lombok.Data;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//测试业务层
//SpringBoot的Junit集成测试
@RunWith(SpringRunner.class)
//SpringBoot的测试环境初始化，参数：启动类
@SpringBootTest(classes = AppDemoApplication.class)

public class ArticlesServiceImplTest {
    //注入Service
    @Autowired
    private ArticlesServiceImpl articlesService;
    /**
     * 保存一个文章
     */
    @Test

    public void testSaveArticles(){
        Articles articles = new Articles();
//        articles.setLink("www.test.com");
        articles.setContent("测试添加的数据");
        articles.setAuthor("测试作者");
        articles.setDate(new Date());
        articles.setTitle("test");
//        articles.setRead(23);
//        articles.setWords(233);
        articlesService.saveArticles(articles);
    }
    /**
     * 查询所有数据
     */
    @Test
    public void testFindAll(){
        List<Articles> list = articlesService.findArticlesList();
        System.out.println(list);
    }
    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById(){
        Articles articles = articlesService.findArticlesById(new ObjectId("5f4c988a6f84aee4cdad4774"));
        System.out.println(articles);
    }

    /**
     * 测试根据id删除文章
     */
    @Test
    public void testDeleteArticlesById(){
        articlesService.deleteArticlesById(new ObjectId("5f4c9a804828a832cb701f83"));
        System.out.println("数据库已删除该test数据");
    }

    /**
     * 根据文章title删除
     */
    @Test
    public void testDeleteByTitle(){
        articlesService.deleteByTitle("阿里");
        System.out.println("del success");
    }

    /**
     * 根据文章title更新
     */
    @Test
    public void testUpdateByTitleOne(){
        Update update = new Update().set("title", "newtitle").
                set("author", "author88").
                set("date", new Date()).
                set("content", "content");
        articlesService.updateByTitleOne("三六九等", update);
        System.out.println("update success");
    }

    /**
     * 搜索文章
     */
    @Test
    public void testSearchByContent(){
        articlesService.searchByContent("string");
        System.out.println("search success");
    }

    /**
     * 倒排索引
     */
    @Test
    public void testGetInvertedIndex(){
//        List<Articles> articlesList = new ArrayList<>();
//        Articles article = new Articles();
//        article.setTitle("It's a rainy day!");
//        //article.setId(new ObjectId("slweid43565y"));
//        article.setContent("This is a test. I want to test if I can get the right inverted file");
//        articlesList.add(article);
        List<Articles> articlesList = articlesService.findArticlesList();
        HashMap map = articlesService.getInvertedIndex(articlesList);
        System.out.println(map);
    }

    @Test
    public void testInvertedIndex(){
        HashMap map = articlesService.invertedIndex();
        System.out.println(map);
    }
}















