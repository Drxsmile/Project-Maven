package com.promvn.appDemo.service;

import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.po.TernaryTree;
import org.bson.types.ObjectId;
import org.springframework.expression.spel.ast.Ternary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TernarySearchServiceImpl implements TernarySearchService{

    @Override
    public HashMap<String, Integer> frequency(List<Articles> list){
        HashMap<String, Integer> map  = new HashMap<>();
        for(int i = 0; i < list.size(); i++) {
            // 获取文章分词列表
            Articles article = list.get(i);
            ObjectId id = article.getId();
            String text = article.getTitle() + article.getContent();
            String regEx = "[\n`~!@#$%^&*()+=|{}:;,\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
            text = text.toLowerCase().replaceAll(regEx, " ");
            String[] wordlist = text.split(" ");
            for (String word: wordlist) {
                if("".equals(word)){
                    continue;
                }
                if(map.containsKey(word)){
                    map.put(word, map.get(word)+1);
                }else{
                    map.put(word, 1);
                }
            }
        }
        return map;
    }

    @Override
    public TernaryTree getTernaryTree(Set set){
        TernaryTree tree = new TernaryTree();
        List<String> list = new ArrayList<>(set);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // get balanced tree
        balance(tree, list, 0, list.size()-1);

        return tree;
    }

    private void balance(TernaryTree tree, List<String> list, int start, int end) {
        // base case
        if(start > end){
            return;
        }

        // recursion
        int mid = (start+end)/2;
        tree.insert(list.get(mid));
        balance(tree, list, start, mid-1);
        balance(tree, list, mid+1, end);

    }

    @Override
    public List<String> autocomplete(String prefix, TernaryTree tree, HashMap freq){
        List<String> list = new ArrayList<>();
        HashSet<String> set = tree.preSearch(prefix);
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : set){
            map.put(word, (Integer) freq.get(word));
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int size = entryList.size()>10 ? 10 : entryList.size();
        for (int i = 0; i < size; i++) {
            list.add(entryList.get(i).getKey());
        }
        return list;
    }
}
