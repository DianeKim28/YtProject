package com.example.ytproject.controller;

import com.example.ytproject.dto.ArticleForm;
import com.example.ytproject.entity.Article;
import com.example.ytproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j //로깅을 위한 어노테이션.
public class ArticleController {
    @Autowired  //스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;  //원래 객체를 만들어야 하지만 스프링부트는 자체적으로 알아서 해줌.

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        /*System.out.println(form.toString()); 로깅 기능으로 대체*/
        log.info(form.toString());

        //1,Dto를 entity로 변환
        Article article = form.toEntity();
        /*System.out.println(article.toString());*/
        log.info(article.toString());

        //repository에게 entity를 DB에 저장하게 함.
        Article saved = articleRepository.save(article); //save는 CRUD에서 제공함
        // /*        System.out.println(saved.toString());*/
        log.info(saved.toString());

        return "redirect:/articles"+saved.getId();
    }

    @GetMapping("/articles/{id}")  /*id 값을 controller에서 받아와야함*/
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        //1.id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        //3. 보여줄 페이지를 설정.
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든  Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();

        //2. 가져온 Article 묶음을 뷰로 전달.
        model.addAttribute("articleList",articleEntityList);

        //3. 뷰 페이지 설정.
        return "articles/index"; //articles/index.mustache
    }
}