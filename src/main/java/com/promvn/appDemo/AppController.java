package com.promvn.appDemo;

import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.po.InvertedFile;
import com.promvn.appDemo.po.TernaryTree;
import com.promvn.appDemo.service.ArticlesService;
import com.promvn.appDemo.service.TernarySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class AppController {
    @Resource
    public ArticlesService articlesService;
    @Resource
    public TernarySearchService ternarySearchService;

//    HashMap<String, ArrayList> map = new HashMap<>();

    @RequestMapping("")
    public ModelAndView index()
    {
        ModelAndView view = new ModelAndView("/index");
        List<Articles> list = articlesService.findArticlesList();
        view.addObject("all",list);
//        map = articlesService.getInvertedIndex(list);
//        InvertedFile invertedFile = new InvertedFile(map);
//        articlesService.saveInvertedIndex(invertedFile);
        return view;
    }

    @RequestMapping("/save_article")
    public ModelAndView saveArt(){
        return new ModelAndView("/save");
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
   public ModelAndView save(HttpServletResponse httpServletResponse
                            ,String title, String author, String content) {
        if ("".equals(title) || "".equals(author)) {
            ModelAndView v_error = new ModelAndView("/error");
            v_error.addObject("error", "please input title and author");
            return v_error;
        }
        else
        {
            ModelAndView view = new ModelAndView("/save");
            Articles art = new Articles();
            view.addObject("art", art);
            art.setTitle(title);
            art.setAuthor(author);
            art.setContent(content);
            art.setDate(new Date());
            articlesService.saveArticles(art);
            ModelAndView succ = new ModelAndView("/success");
            succ.addObject("success", "save success");
            return succ;

        }
    }

    @RequestMapping("/delete_article")
    public ModelAndView delArt(){
        return new ModelAndView("/delete");
    }
    
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @ResponseBody
    public ModelAndView delete(HttpServletResponse httpServletResponse
            ,String title) {
        if ("".equals(title)) {
            ModelAndView v_error = new ModelAndView("/error");
            v_error.addObject("error", "please input title");
            return v_error;
        } else {
            ModelAndView view = new ModelAndView("/delete");
            Articles art = new Articles();
            view.addObject("art", art);
            articlesService.deleteByTitle(title);
            ModelAndView succ = new ModelAndView("/success");
            succ.addObject("success", "delete success");
            return succ;
        }
    }



    @RequestMapping("/update_article")
    public ModelAndView upArt(){
        return new ModelAndView("/update");
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public ModelAndView update(HttpServletResponse httpServletResponse
            ,String title, String newtitle, String author, String content) {
        if ("".equals(title) || "".equals(author) || "".equals(newtitle))
        {
            ModelAndView v_error = new ModelAndView("/error");
            v_error.addObject("error", "please input all");
            return v_error;
        }
        else
        {
            ModelAndView view = new ModelAndView("/update");
            Articles art = new Articles();
            view.addObject("art", art);
            Update update = new Update().set("title", newtitle).
                                         set("author", author).
                                         set("date", new Date()).
                                         set("content", content);
            articlesService.updateByTitleOne(title, update);
            ModelAndView succ = new ModelAndView("/success");
            succ.addObject("success", "update success");
            return succ;

        }
    }


    @RequestMapping("/search_article")
    public ModelAndView seArt(){
//        return new ModelAndView("/search");
        return new ModelAndView("/autocomplete");
    }

    @RequestMapping(value = "/search", method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView search(HttpServletResponse httpServletResponse
            ,String keyword){
        if ("".equals(keyword)) {
            ModelAndView v_error = new ModelAndView("/error");
            v_error.addObject("error", "please input keyword");
            return v_error;
        }
        else
        {
            long startTime=System.nanoTime();
            ModelAndView view = new ModelAndView("/search");
            Articles art = new Articles();
            view.addObject("art",art);
            List<Articles> list = articlesService.searchByContent(keyword);
            ModelAndView succ = new ModelAndView("/success");
            succ.addObject("success", "search success");
            succ.addObject("list", list);
            long endTime=System.nanoTime();
            System.out.println("程序运行时间： "+(endTime-startTime)/ 1000000.0 +" ms");
            return succ;
        }
    }


    @RequestMapping(value = "/search2", method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView search2(HttpServletResponse httpServletResponse
            ,String keyword){
        if ("".equals(keyword)) {
            ModelAndView v_error = new ModelAndView("/error");
            v_error.addObject("error", "please input keyword");
            return v_error;
        }
        else
        {
            List<Articles> all = articlesService.findArticlesList();
            HashMap<String, ArrayList> map = articlesService.getInvertedIndex(all);
            long startTime=System.nanoTime();
            ModelAndView view = new ModelAndView("/search");
//            Articles art = new Articles();
//            view.addObject("art",art);
//            HashMap<String, ArrayList> map = articlesService.invertedIndex();
            List<Articles> list = articlesService.searchByIndex(keyword, map);
            ModelAndView succ = new ModelAndView("/success");
            succ.addObject("success", "search success");
            succ.addObject("list", list);
            long endTime=System.nanoTime();
            System.out.println("程序运行时间： "+(endTime-startTime)/ 1000000.0 +" ms");
            return succ;
        }
    }

    @RequestMapping(value = "/autocomplete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView complete(HttpServletResponse httpServletResponse
            ,String keyword){
        if ("".equals(keyword)) {
            ModelAndView v_error = new ModelAndView("/error");
            v_error.addObject("error", "please input keyword");
            return v_error;
        }
        else{
            ModelAndView view = new ModelAndView("/autocomplete");
            HashMap<String, Integer> wordmap = ternarySearchService.frequency(articlesService.findArticlesList());
            TernaryTree tree = ternarySearchService.getTernaryTree(wordmap.keySet());
            List<String> wordlist = ternarySearchService.autocomplete(keyword, tree, wordmap);
            view.addObject("wordlist", wordlist);

            return view;
        }
    }

}










