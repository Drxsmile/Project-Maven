package com.promvn.appDemo.dao;

import com.promvn.appDemo.po.InvertedFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//@RepositoryRestResource(collectionResourceRel = "", path = "") 需要改mvc的内容时用
public interface InvertedFileRepository extends MongoRepository<InvertedFile, ObjectId> {

}
