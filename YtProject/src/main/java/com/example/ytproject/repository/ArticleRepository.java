package com.example.ytproject.repository;

import com.example.ytproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends CrudRepository <Article,Long> {
    @Override
    ArrayList<Article> findAll();

    List<Article> index();
}
