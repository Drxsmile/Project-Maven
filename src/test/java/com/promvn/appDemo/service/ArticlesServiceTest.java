package com.promvn.appDemo.service;


import com.promvn.appDemo.AppDemoApplication;
import com.promvn.appDemo.po.Articles;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//测试业务层
//SpringBoot的Junit集成测试
@RunWith(SpringRunner.class)
//SpringBoot的测试环境初始化，参数：启动类
@SpringBootTest(classes = AppDemoApplication.class)

public class ArticlesServiceTest {
    //注入Service
    @Autowired
    private ArticlesService articlesService;
    /**
     * 保存一个文章
     */
    @Test
    public void testSaveArticles(){
        Articles articles = new Articles();
        articles.setLink("www.test.com");
        articles.setContent("测试添加的数据");
        articles.setAuthor("测试作者");
        articles.setDate(new Date());
        articles.setTitle("test");
        articles.setRead(23);
        articles.setWords(233);
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
}
