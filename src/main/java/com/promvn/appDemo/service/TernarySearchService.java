package com.promvn.appDemo.service;

import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.po.TernaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface TernarySearchService {

    /**
     * word词频表
     * @param list
     * @return
     */
    HashMap<String, Integer> frequency(List<Articles> list);

    /**
     * 根据数据库中的文章生成TernaryTree
     * @param set
     * @return
     */
    TernaryTree getTernaryTree(Set set);

    /**
     * 获取自动提示word列表
     * @param prefix
     * @param tree
     * @param freq
     * @return
     */
    List<String> autocomplete(String prefix, TernaryTree tree, HashMap freq);
}
