package com.promvn.appDemo.service;

import com.mongodb.client.MongoClient;
import com.promvn.appDemo.dao.ArticlesRepository;
import com.promvn.appDemo.po.Articles;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //业务层
public class ArticlesServiceImpl implements ArticlesService{

    @Autowired //注入dao包，增删改查的功能
    private ArticlesRepository articlesRepository;//接口实现
    private MongoTemplate mongoTemplate;


    public void saveArticles(Articles articles){
//如果需要自定义主键，可以在这里指定主键；如果不指定主键，MongoDB会自动生成主键
//设置一些默认初始值。。。
//调用dao
        articlesRepository.save(articles);
    }

    public void updateArticles(Articles articles){
//调用dao
        articlesRepository.save(articles);
    }

    public void deleteArticlesById(ObjectId id){
//调用dao
        articlesRepository.deleteById(id);
    }

    public List<Articles> findArticlesList(){
//调用dao
        return articlesRepository.findAll();
    }

    public Articles findArticlesById(ObjectId id){
//调用dao
        return articlesRepository.findById(id).get();
    }

    @Override
    public void deleteByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        if(mongoTemplate.exists(query,Articles.class)){
            mongoTemplate.remove(query, Articles.class);
            System.out.println("delete success");
        }
        else System.out.println("There is no such an article!");
    }

    @Override
    public Articles updateByTitleOne(String title, Update update) {
        Query query = new Query(Criteria.where("title").is(title));
        return mongoTemplate.findAndModify(query,update, Articles.class);

    }

    @Override
    public List<Articles> searchByContent(String keyword) {
        TextCriteria textCriteria = new TextCriteria().matching(keyword);
        Query query = TextQuery.queryText(textCriteria);
        return mongoTemplate.find(query, Articles.class);
    }
}
