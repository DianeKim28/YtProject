package com.example.ytproject.service;

import com.example.ytproject.dto.ArticleForm;
import com.example.ytproject.entity.Article;
import com.example.ytproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service /*해당 클래스를 서비스로 인식하여 스프링 부트에 객체를 생성(등록)*/
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
       return articleRepository.findAll();

    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }
    public Article update(Long id, ArticleForm dto) {
        //1. DTO -> 엔티티
        Article article = dto.toEntity();
        log.info ("id:{}, article:{}", id, article.toString());

        //2. 타겟조회
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리
            if (target == null || id != article.getId()) {
                // 400, 잘못된 요청 응답!
                log.info("잘못된 요청! id {}, article:{}", id, article.toString());
                return null;
            }
        //4. 업데이트
        target.patch(article);
            Article updated = articleRepository.save(target);
            return updated;
    }
    public Article delete(Long id){
        // 대상찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if(target == null){
            return null;
        }
        //대상 삭제
        articleRepository.delete(target);
        return  target;
    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        //dto 묶음을 entity  묶음으로 변환
        List<Article> articlesList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //entity 묶음을 DB로 저장
        articlesList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제실패!")
        );
        // 결과값 반환
        return articlesList;
    }
}
