package com.promvn.appDemo.service;

import com.promvn.appDemo.dao.ArticlesRepository;
import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.po.InvertedFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ArticlesService {

    /**
     * 保存一个article
     * @param articles
     */
    void saveArticles(Articles articles);

    /**
     * 更新一个article
     * @param articles
     */
    void updateArticles(Articles articles);

    /**
     * 根据id删除文章
     * @param id
     */
    void deleteArticlesById(ObjectId id);

    /**
     * 查询所有文章
     * @return
     */
    List<Articles> findArticlesList();

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    Articles findArticlesById(ObjectId id);

    /**
     * 按照标题删除文章
     * @param title
     */
    void deleteByTitle(String title);

    /**
     * 更改指定标题文章
     * @param title
     * @param update
     * @return
     */
    Articles updateByTitleOne(String title, Update update);

    /**
     * 找到内容包含keyword的文章
     * @param keyword
     * @return
     */
    List<Articles> searchByContent(String keyword);

    /**
     * 获取倒排索引
     * @param articlesList
     * @return
     */
    HashMap<String, ArrayList> getInvertedIndex(List<Articles> articlesList);

    /**
     * 根据倒排索引搜索关键字
     * @param keyword
     * @return
     */
    List<Articles> searchByIndex(String keyword, HashMap<String, ArrayList> map);

    /**
     * 从数据库中获取倒排索引
     * @return
     */
    HashMap<String, ArrayList> invertedIndex();

    /**
     * 把倒排索引存入数据库
     * @param map
     */
    void saveInvertedIndex(InvertedFile map);

}

















