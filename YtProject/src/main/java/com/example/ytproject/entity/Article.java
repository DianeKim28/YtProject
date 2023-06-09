package com.example.ytproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;

@Entity  //DB가 해당객체를 인식가능
@AllArgsConstructor
@NoArgsConstructor  //디폴트 생성자 추가.
@ToString
@Getter //롬복으로 게터추가
public class Article {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) //DB가 id를 자동생성/ 자동생성 어노테이션
    private Long id;  //기본적으로 entity에 기본값을 넣어줘야함
    @Column
    private String title;

    @Column
    private String content;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content !=null)
            this.content = article.content;
    }
}



