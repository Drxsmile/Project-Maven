package com.promvn.appDemo.dao;

import com.promvn.appDemo.po.Articles;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "", path = "") 需要改mvc的内容时用
public interface ArticlesRepository extends MongoRepository<Articles, ObjectId> {

}
