package com.promvn.appDemo;

import com.promvn.appDemo.po.Articles;
import com.promvn.appDemo.service.ArticlesService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
   public ModelAndView save(HttpServletResponse httpServletResponse
                            ,String title, String author, String content) {
        ModelAndView view = new ModelAndView("/save");
        if (title == "" || author == "") {
            ModelAndView v_error = new ModelAndView("/error");
            v_error.addObject("error", "please input title and author");
            return v_error;
        }
        else {
            Articles art = new Articles();
            view.addObject("art", art);
            art.setTitle(title);
            art.setAuthor(author);
            art.setContent(content);
            art.setDate(new Date());
            articlesService.saveArticles(art);
            return view;//.addObject("success", "save success");

        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
    @ResponseBody
    public ModelAndView delete(HttpServletResponse httpServletResponse
            ,String title)
    {
        ModelAndView view = new ModelAndView("/delete");
        Articles art = new Articles();
        view.addObject("art",art);
        articlesService.deleteByTitle(title);
        return view;
    }

}













