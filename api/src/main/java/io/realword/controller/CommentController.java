package io.realword.controller;

import io.realword.controller.dto.req.article.CreatedCommentReq;
import io.realword.controller.dto.res.article.CreatedCommentRes;
import io.realword.controller.dto.res.article.GetCommentRes;
import io.realword.security.jwt.Jwt;
import io.realword.service.ArticleServiceImp;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class CommentController {

  private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
  private final ArticleServiceImp articleService;
  private final Jwt jwt;

  @Autowired
  public CommentController(ArticleServiceImp articleService, Jwt jwt) {
    this.articleService = articleService;
    this.jwt = jwt;
  }

  @PostMapping("/{slug}/comments")
  public ResponseEntity<CreatedCommentRes> createdComment (@AuthenticationPrincipal String email, @PathVariable String slug, CreatedCommentReq data){
    try {
      logger.info("Post '/articles/{slug}/comments' Request Data => {}, {}, {}", email, slug, data);

      CreatedCommentRes returnData = articleService.createdComment(email, slug, data);
      logger.info("Post '/articles/{slug}/comments' Responses Data => {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/{slug}/comments")
  public ResponseEntity<List<GetCommentRes>> getCommentsByArticle (@AuthenticationPrincipal String email, @PathVariable String slug){
    try {
      logger.info("Get '/articles/{slug}/comments' Request Data => {}, {}", email, slug);

      List<GetCommentRes> returnData = articleService.getCommentByArticle(email, slug);
      logger.info("Get '/articles/{slug}/comments' Responses Data => {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @DeleteMapping("/{slug}/comments/{commentId}")
  public ResponseEntity<Boolean> deletedCommentById (@AuthenticationPrincipal String email, @PathVariable String slug, @PathVariable Long commentId) {
    try {
      logger.info("Delete '/articles/{slug}/comments/{commentId}' Request Data => {}, {}, {}", email, slug, commentId);

      boolean returnData = articleService.deletedComment(email, slug, commentId);
      logger.info("Delete '/articles/{slug}/comments/{commentId}' Responses Data => {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Error fetching articles", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
