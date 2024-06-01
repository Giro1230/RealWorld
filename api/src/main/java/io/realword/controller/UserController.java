package io.realword.controller;

import io.realword.controller.dto.req.user.CurrentUserReq;
import io.realword.controller.dto.req.user.UpdateUserReq;
import io.realword.controller.dto.res.user.CurrentUserRes;
import io.realword.security.jwt.Jwt;
import io.realword.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserServiceImp userService;
  private final Jwt jwt;

  @Autowired
  public UserController(UserServiceImp userService, Jwt jwt) {
    this.userService = userService;
    this.jwt = jwt;
  }

  @GetMapping
  public ResponseEntity<CurrentUserRes> getCurrentUser(@AuthenticationPrincipal CurrentUserReq user) {
    try {
      logger.info("Get '/user' Request Data = {}", user);

      CurrentUserRes returnData = userService.getUserByEmail(user);
      logger.info("Get '/user' Responses Data = {}", user);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {
      logger.error("Fail to current user", e);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PutMapping
  public ResponseEntity<ResUser> updateUser(@AuthenticationPrincipal String email, @RequestBody UpdateUserReq user) {
    try {
      return ResponseEntity.ok(userService.update(email, user));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
