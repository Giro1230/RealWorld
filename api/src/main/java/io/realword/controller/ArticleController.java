package io.realword.controller;

import io.realword.controller.dto.req.ReqUser;
import io.realword.controller.dto.res.ResUser;
import io.realword.security.jwt.Jwt;
import io.realword.service.ArticleServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<ResUser> getAllArticle(){
    try {
      return ResponseEntity.ok();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/articles")
  public ResponseEntity<ResUser> getArticlesByAuthor(@RequestParam String author){
    try {
      return ResponseEntity.ok();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/articles")
  public ResponseEntity<ResUser> getArticlesByFavorites(@RequestParam String favorited){
    try {
      return ResponseEntity.ok();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/articles")
  public ResponseEntity<ResUser> getArticlesByTags(@RequestParam String tag){
    try {
      return ResponseEntity.ok();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
