package com.promvn.appDemo.service;

import com.promvn.appDemo.dao.ArticlesRepository;
import com.promvn.appDemo.po.Articles;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //业务层
public class ArticlesService {

    @Autowired //注入dao包，增删改查的功能
    private ArticlesRepository articlesRepository;//接口实体

    /**
     * 保存一个article
     * @param articles
     */
    public void saveArticles(Articles articles){
//如果需要自定义主键，可以在这里指定主键；如果不指定主键，MongoDB会自动生成主键
//设置一些默认初始值。。。
//调用dao
        articlesRepository.save(articles);
    }
    /**
     * 更新
     * @param articles
     */
    public void updateArticles(Articles articles){
//调用dao
        articlesRepository.save(articles);
    }
    /**
     * 根据id删除文章
     * @param id
     */
    public void deleteArticlesById(ObjectId id){
//调用dao
        articlesRepository.deleteById(id);
    }
    /**
     * 查询所有文章
     * @return
     */
    public List<Articles> findArticlesList(){
//调用dao
        return articlesRepository.findAll();
    }
    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    public Articles findArticlesById(ObjectId id){
//调用dao
        return articlesRepository.findById(id).get();
    }

}
