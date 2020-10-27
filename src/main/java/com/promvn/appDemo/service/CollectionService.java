package com.promvn.appDemo.service;

import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.po.TernaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CollectionService {
    /**
     * 根据Ternary Search Tree获取prefix, wordlist的map
     * @param articlesList
     * @param map
     */
     void prefixMap(List<Articles> articlesList, HashMap<String, ArrayList> map);

    /**
     * save
     * @param map
     */
    void saveMap(HashMap map);

    /**
     * get
     * @return
     */
    HashMap<String, ArrayList> getMap();
}
