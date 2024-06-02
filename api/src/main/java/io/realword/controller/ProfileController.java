package io.realword.controller;

import io.realword.controller.dto.req.follow.FollowReq;
import io.realword.controller.dto.req.follow.GetFollowUserReq;
import io.realword.controller.dto.req.follow.UnFollowReq;
import io.realword.controller.dto.req.follow.UserReq;
import io.realword.controller.dto.res.follow.FollowRes;
import io.realword.controller.dto.res.follow.GetFollowRes;
import io.realword.controller.dto.res.follow.UnFollowRes;
import io.realword.domain.User;
import io.realword.service.FollowServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profiles/celeb_{username}/follow")
public class ProfileController {

  private final Logger logger;
  private final FollowServiceImp followService;

  public ProfileController(FollowServiceImp followService) {
    this.logger = LoggerFactory.getLogger(ProfileController.class);
    this.followService = followService;
  }

  @GetMapping
  public ResponseEntity<GetFollowRes> getFollow (@AuthenticationPrincipal UserReq user, @RequestBody GetFollowUserReq data) {
    try {

      logger.info("Get '/profiles/celeb_{username}' Request Data = {}, {}", user, data);
      GetFollowRes returnData = followService.getProfile(user, data);
      logger.info("Get '/profiles/celeb_{username}' Responses Data = {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Fail to follow", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }


  @PostMapping("/follow")
  public ResponseEntity<FollowRes> follow (@AuthenticationPrincipal UserReq user, @RequestBody FollowReq data) {
    try {
      logger.info("Post '/profiles/celeb_{username}/follow' Request Data = {}, {}", user, data);

      FollowRes returnData = followService.followProfile(user, data);
      logger.info("Post '/profiles/celeb_{username}/follow' Responses Data = {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Fail to follow", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @DeleteMapping("/follow")
  public ResponseEntity<UnFollowRes> unfollow (@AuthenticationPrincipal UserReq user, @RequestBody UnFollowReq data) {
    try {
      logger.info("Delete '/profiles/celeb_{username}/follow' Request Data = {}, {}", user, data);

      UnFollowRes returnData = followService.unFollowProfile(user, data);
      logger.info("Delete '/profiles/celeb_{username}/follow' Responses Data = {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Fail to unfollow", e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}
