package io.realword.controller;

import io.realword.controller.dto.res.article.ArticlesFavoritedbyUsernameRes;
import io.realword.controller.dto.res.article.CreatedFavoriteRes;
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
@RequestMapping("/article")
public class FavoriteController {

  private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
  private final ArticleServiceImp articleService;
  private final Jwt jwt;

  @Autowired
  public FavoriteController(ArticleServiceImp articleService, Jwt jwt) {
    this.articleService = articleService;
    this.jwt = jwt;
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
