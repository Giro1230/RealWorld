package io.realword.controller;

import io.realword.domain.Article;
import io.realword.security.jwt.Jwt;
import io.realword.service.ArticleServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

  private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
  private final ArticleServiceImp articleService;
  private final Jwt jwt;

  @Autowired
  public ArticleController(ArticleServiceImp articleService, Jwt jwt) {
    this.articleService = articleService;
    this.jwt = jwt;
  }

  @GetMapping("/articles")
  public ResponseEntity<List<ResArticle>> getArticles(
    @RequestParam(required = false) String author,
    @RequestParam(required = false) String favorited,
    @RequestParam(required = false) String tag) {
    try {
      if (author != null) {
        return ResponseEntity.ok(articleService.getArticlesByAuthor(author));
      } else if (favorited != null) {
        return ResponseEntity.ok(articleService.getArticlesByFavorited(favorited));
      } else if (tag != null) {
        return ResponseEntity.ok(articleService.getArticlesByTag(tag));
      } else {
        return ResponseEntity.ok(articleService.getAllArticle());
      }
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping("/articles")
  public ResponseEntity<Article> crestedArticle(@RequestBody ReqArticle data){
    try {
      return ResponseEntity.ok(articleService.createdArticle(Article
        .builder()
        .tile(data.getTile())
          .description(data.getDescription())
          .body(data.getBody())
        .build()
      ));
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
