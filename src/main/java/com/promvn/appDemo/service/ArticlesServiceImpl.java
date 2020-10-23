package com.promvn.appDemo.service;

import com.mongodb.client.MongoClient;
import com.promvn.appDemo.dao.ArticlesRepository;
import com.promvn.appDemo.dao.InvertedFileRepository;
import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.po.InvertedFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service //业务层
public class ArticlesServiceImpl implements ArticlesService{

    @Autowired //注入dao包，增删改查的功能
    private ArticlesRepository articlesRepository;//接口实现
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private InvertedFileRepository invertedFileRepository;


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
        Query textQuery = TextQuery.queryText(textCriteria);
        return mongoTemplate.find(textQuery, Articles.class);
    }

    public HashMap<ObjectId, ArrayList> document(int j, ObjectId id){
        HashMap<ObjectId, ArrayList> doc = new HashMap<>();
        ArrayList<Integer> position = new ArrayList<>();
        position.add(1); // tf
        position.add(j);
        doc.put(id, position);
        return doc;
    }

    @Override
    public HashMap<String, ArrayList> getInvertedIndex(List<Articles> articlesList) {
        HashMap<String, ArrayList> map = new HashMap<>();
        for(int i = 0; i < articlesList.size(); i++){
            // 获取文章分词列表
            Articles article = articlesList.get(i);
            ObjectId id = article.getId();
            String text = article.getTitle() + article.getContent();
            String regEx="[\n`~!@#$%^&*()+=|{}:;,\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
            text = text.toLowerCase().replaceAll(regEx, " ");
            String[] wordlist = text.split(" ");

            // 将列表中词频更新到map中
            for(int j = 0; j < wordlist.length; j++){
                String word = wordlist[j];
                if(map.containsKey(word)){
                    ArrayList<HashMap> docs = map.get(word);
                    boolean add = false;
                    for(int k = 0; k < docs.size(); k++){
                        HashMap<ObjectId, ArrayList> doc = docs.get(k);
                        if(doc.containsKey(id)){
                            ArrayList<Integer> position = doc.get(id);
                            position.add(j);
                            position.set(0, position.size()-1);
                            doc.put(id, position);
                            break;
                        }
                        if(k == docs.size()-1 && !doc.containsKey(id)){
                            add = true;
                        }
                    }
                    if(add){
                        HashMap<ObjectId, ArrayList> doc = document(j, id);
                        docs.add(doc);
                    }
                    map.put(word, docs);
                }else{
                    ArrayList<HashMap> docs = new ArrayList<>();
                    HashMap<ObjectId, ArrayList> doc = document(j, id);
                    docs.add(doc);
                    map.put(word, docs);
                }
            }

        }
        return map;
    }

    @Override
    public List<Articles> searchByIndex(String keyword, HashMap<String, ArrayList> map) {
        List<Articles> list = new ArrayList<>();
        List<HashMap> docs = map.get(keyword);
        for(HashMap<ObjectId, ArrayList> doc : docs){
            ObjectId id = (ObjectId) doc.keySet().toArray()[0];
            list.add(findArticlesById(id));
        }
        return list;
    }

    @Override
    public HashMap<String, ArrayList> invertedIndex() {
        List<InvertedFile> list = invertedFileRepository.findAll();
        InvertedFile invertedFile = list.get(list.size()-1);
        return invertedFile.getMap();
    }

    @Override
    public void saveInvertedIndex(InvertedFile invertedFile) {
        invertedFileRepository.save(invertedFile);
//        Query query = new Query();
//        Update update = new Update().set("map", invertedFile.getMap());
//        mongoTemplate.findAndModify(query, update, InvertedFile.class);
    }
}
