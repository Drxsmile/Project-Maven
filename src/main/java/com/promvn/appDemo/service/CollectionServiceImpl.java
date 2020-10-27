package com.promvn.appDemo.service;

import com.promvn.appDemo.dao.PrefixMapRepository;
import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.po.InvertedFile;
import com.promvn.appDemo.po.PrefixMap;
import com.promvn.appDemo.po.TernaryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollectionServiceImpl implements CollectionService{
    @Autowired
    private TernarySearchService ternarySearchService;
    @Autowired
    private PrefixMapRepository prefixMapRepository;
    @Override
    public void prefixMap(List<Articles> articlesList, HashMap<String, ArrayList> map) {
        HashMap<String, Integer> wordmap = ternarySearchService.frequency(articlesList);
        Set<String> wordSet = wordmap.keySet();
        TernaryTree tree = ternarySearchService.getTernaryTree(wordSet);
        for (String word : wordSet) {
            if ("".equals(word)) continue;
            for (int i = 0; i < word.length()-1; i++) {
                String pre = word.substring(0, i + 1);
                if (!map.containsKey(pre)) {
                    List<String> wordlist = ternarySearchService.autocomplete(pre, tree, wordmap);
                    map.put(pre, (ArrayList) wordlist);
                }
            }
        }
    }


    @Override
    public void saveMap(HashMap map) {
        PrefixMap prefixMap = new PrefixMap(map);
        prefixMapRepository.save(prefixMap);
    }

    @Override
    public HashMap<String, ArrayList> getMap() {
        List<PrefixMap> list = prefixMapRepository.findAll();
        PrefixMap prefixMap = list.get(list.size()-1);
        return prefixMap.getMap();
    }
}
