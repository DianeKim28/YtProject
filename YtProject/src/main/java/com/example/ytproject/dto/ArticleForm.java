package com.example.ytproject.dto;

import com.example.ytproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString

public class ArticleForm {

    private Long id; // id필드 추가
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);

    }
}