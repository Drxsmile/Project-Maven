package com.promvn.appDemo.dao;

import com.promvn.appDemo.po.InvertedFile;
import com.promvn.appDemo.po.PrefixMap;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//@RepositoryRestResource(collectionResourceRel = "", path = "") 需要改mvc的内容时用
public interface PrefixMapRepository extends MongoRepository<PrefixMap, ObjectId> {

}
