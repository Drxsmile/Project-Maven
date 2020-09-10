package com.promvn.appDemo;

import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.service.ArticlesService;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class AppController {
    @Resource
    public ArticlesService articlesService;

    @RequestMapping("")
    public ModelAndView index()
    {
        ModelAndView view = new ModelAndView("/index");
        List<Articles> list = articlesService.findArticlesList();
        view.addObject("all",list);
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
            ,String title)
    {
        ModelAndView view = new ModelAndView("/delete");
        Articles art = new Articles();
        view.addObject("art",art);
        articlesService.deleteByTitle(title);
        ModelAndView succ = new ModelAndView("/success");
        succ.addObject("success", "delete success");
        return succ;
    }



    @RequestMapping("/update_article")
    public ModelAndView upArt(){
        return new ModelAndView("/update");
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public ModelAndView save(HttpServletResponse httpServletResponse
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
            Update update = new Update().set("tilte", newtitle).
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
        return new ModelAndView("/search");
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
            ModelAndView view = new ModelAndView("/search");
            Articles art = new Articles();
            view.addObject("art",art);
            List<Articles> list = articlesService.searchByContent(keyword);
            ModelAndView succ = new ModelAndView("/success");
            succ.addObject("success", "search success");
            succ.addObject("list", list);
            return succ;
        }
    }

}










