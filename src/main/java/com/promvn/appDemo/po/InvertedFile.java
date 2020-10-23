package com.promvn.appDemo.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Document(collection="invertedFile")
@Data
@NoArgsConstructor
public class InvertedFile implements Serializable {
    @Id
    private ObjectId id;

    private HashMap map;

    public InvertedFile(HashMap<String, ArrayList> map) {
        this.map = map;
    }
}
