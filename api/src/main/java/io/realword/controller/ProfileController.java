package io.realword.controller;

import io.realword.service.FollowServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/profiles/celeb_{username}/follow")
public class ProfileController {

  private final Logger logger;
  private final FollowServiceImp followService;

  public ProfileController(FollowServiceImp followService) {
    this.logger = LoggerFactory.getLogger(ProfileController.class);
    this.followService = followService;
  }

  @PostMapping
  public ResponseEntity<?> follow () {
    try {

      logger.info("Post '/profiles/celeb_{username}/follow' Responses Data = {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Fail to follow", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @DeleteMapping
  public ResponseEntity<?> unfollow () {
    try {

      logger.info("Post '/profiles/celeb_{username}/follow' Responses Data = {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Fail to unfollow", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}
