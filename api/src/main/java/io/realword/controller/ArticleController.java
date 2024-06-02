package io.realword.controller;

import io.realword.controller.dto.req.article.CreatedArticleReq;
import io.realword.controller.dto.res.article.*;
import io.realword.domain.Article;
import io.realword.security.jwt.Jwt;
import io.realword.service.ArticleServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

  private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
  private final ArticleServiceImp articleService;
  private final Jwt jwt;

  @Autowired
  public ArticleController(ArticleServiceImp articleService, Jwt jwt) {
    this.articleService = articleService;
    this.jwt = jwt;
  }

  @GetMapping
  public ResponseEntity<List<AllArticlesRes>> getArticles() {
    try {
      logger.info("Get '/articles' Request");

      List<AllArticlesRes> returnData = articleService.getAllArticle();
      logger.info("Get '/articles' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("?author={username}")
  public ResponseEntity<List<AllArticlesByAuthorRes>> getArticlesByAuthor(@PathVariable String username) {
    try {
      logger.info("Get '/articles?author={username}' Request Data => {}", username);

      List<AllArticlesByAuthorRes> returnData = articleService.getArticlesByAuthor(username);
      logger.info("Get '/articles?author={username}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("?favorited={username}")
  public ResponseEntity<List<AllArticlesByFavoritedByUsernameRes>> getArticlesByFavoritedByAuthor(@PathVariable String username) {
    try {
      logger.info("Get '/articles?favorited={username}' Request Data => {}", username);

      List<AllArticlesByFavoritedByUsernameRes> returnData = articleService.getArticlesByFavorited(username);
      logger.info("Get '/articles?favorited={username}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("?tag={tagname}")
  public ResponseEntity<List<AllArticlesByAuthorByTagRes>> getArticlesByTag(@PathVariable String tagname) {
    try {
      logger.info("Get '/articles?tag={tagname}' Request Data => {}", tagname);

      List<AllArticlesByAuthorByTagRes> returnData = articleService.getArticlesByTag(tagname);
      logger.info("Get '/articles?tag={tagname}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping("/articles")
  public ResponseEntity<CreatedArticleRes> crestedArticle(@RequestBody CreatedArticleReq data, @AuthenticationPrincipal String email){
    try {
      logger.info("Post '/articles' Request Data => {}, {}", data, email);

      CreatedArticleRes returnData = articleService.createdArticle(data, email);
      logger.info("Post '/articles?tag={tagname}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/feed")
  public ResponseEntity<List<FeedArticleRes>> feed(@AuthenticationPrincipal String email) {
    try {
      logger.info("Get '/articles/feed' Request Data => {}",email);

      List<FeedArticleRes> returnData = articleService.getFeed(email);
      logger.info("Get '/articles/feed' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
