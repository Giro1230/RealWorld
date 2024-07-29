package io.realword.controller;

import io.realword.controller.dto.req.article.CreatedArticleReq;
import io.realword.controller.dto.req.article.CreatedCommentReq;
import io.realword.controller.dto.req.article.UpdatedArticleReq;
import io.realword.controller.dto.res.article.*;
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

  @PostMapping
  public ResponseEntity<CreatedArticleRes> crestedArticle(@RequestBody CreatedArticleReq data, @AuthenticationPrincipal String email){
    try {
      logger.info("Post '/articles' Request Data => {}, {}", data, email);

      CreatedArticleRes returnData = articleService.createdArticle(data, email);
      logger.info("Post '/articles' Responses Data => {}",returnData);

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

  @GetMapping("/all")
  public ResponseEntity<List<AllArticlesRes>> getAllArticlesByUser(@AuthenticationPrincipal String email) {
    try {
      logger.info("Get '/articles' Request Data => {}",email);

      List<AllArticlesRes> returnData = articleService.getAllArticlesWithFavoriteStatus(email);
      logger.info("Get '/articles' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/author")
  public ResponseEntity<List<AllArticlesByAuthorRes>> getAllArticlesUser(@AuthenticationPrincipal String email) {
    try {
      logger.info("Get '/articles' Request Data => {}",email);

      List<AllArticlesByAuthorRes> returnData = articleService.getAllArticlesByUser(email);
      logger.info("Get '/articles' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("?author={username}")
  public ResponseEntity<List<AllArticlesByAuthorRes>> getArticlesByAuthorWithFavoriteStatus(@AuthenticationPrincipal String email, @PathVariable String username) {
    try {
      logger.info("Get '/articles?author={username}' Request Data => {}, {}", email, username);

      List<AllArticlesByAuthorRes> returnData = articleService.getArticlesByAuthorWithFavoriteStatus(email,username);
      logger.info("Get '/articles?author={username}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/{slug}")
  public ResponseEntity<ArticleBySlugRes> getArticleBySlug(@AuthenticationPrincipal String email, @PathVariable String slug){
    try {
      logger.info("Get '/articles?author={slug}' Request Data => {}, {}", email, slug);

      ArticleBySlugRes returnData = articleService.getArticleBySlug(email, slug);
      logger.info("Get '/articles?author={slug}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("?tag={tagname}")
  public ResponseEntity<List<AllArticlesByAuthorByTagRes>> getArticlesByTag(@AuthenticationPrincipal String email, @PathVariable String tagname) {
    try {
      logger.info("Get '/articles?tag={tagname}' Request Data => {},{}", email,tagname);

      List<AllArticlesByAuthorByTagRes> returnData = articleService.getArticlesByTagWithFavoriteStatus(email, tagname);
      logger.info("Get '/articles?tag={tagname}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PutMapping("/{slug}")
  public ResponseEntity<UpdatedArticleRes> updateArticle(@AuthenticationPrincipal String email, @PathVariable String slug, @RequestBody UpdatedArticleReq updatedArticle) {
    try {
      logger.info("Get '/articles?author={slug}' Request Data => {}, {}, {}", email, slug, updatedArticle);

      UpdatedArticleRes returnData = articleService.updatedArticle(email, slug, updatedArticle);
      logger.info("Get '/articles?author={slug}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/{slug}")
  public ResponseEntity<Boolean> deleteArticle(@AuthenticationPrincipal String email, @PathVariable String slug){
    try {
      logger.info("Get '/articles?author={slug}' Request Data => {}, {}", email, slug);

      Boolean returnData = articleService.deleteArticle(email, slug);
      logger.info("Get '/articles?author={slug}' Responses Data => {}",returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping("/{slug}/favorite")
  public ResponseEntity<CreatedFavoriteRes> createdFavorite(@AuthenticationPrincipal String email, @PathVariable String slug) {
    try {
      logger.info("Post '/articles/{slug}/favorite' Request Data => {}, {}", email, slug);

      CreatedFavoriteRes returnData = articleService.createdFavorite(email, slug);
      logger.info("Post '/articles/{slug}/favorite' Responses Data => {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("?favorited={username}")
  public ResponseEntity<List<ArticlesFavoritedbyUsernameRes>> getArticlesByUserWidthFavorite(@AuthenticationPrincipal String email, @PathVariable String username) {
    try {
      logger.info("Get '/articles?favorite={username}' Request Data => {}, {}", email, username);

      List<ArticlesFavoritedbyUsernameRes> returnData = articleService.getArticleByUserWidthFavorite(email, username);
      logger.info("Get '/articles?favorite={username}' Responses Data => {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/{slug}/favorite")
  public ResponseEntity<Boolean> deletedFavorite (@AuthenticationPrincipal String email, @PathVariable String slug) {
    try {
      logger.info("Delete '/articles/{slug}/favorite' Request Data => {}, {}", email, slug);

      Boolean returnData = articleService.deletedFavorite(email, slug);
      logger.info("Delete '/articles/{slug}/favorite' Responses Data => {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
