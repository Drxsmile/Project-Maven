package com.promvn.appDemo.dao;

import com.promvn.appDemo.po.Articles;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticlesRepository extends MongoRepository<Articles, ObjectId> {
}
