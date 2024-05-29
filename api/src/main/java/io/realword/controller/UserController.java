package io.realword.controller;

import io.realword.controller.dto.req.ReqUser;
import io.realword.controller.dto.res.ResUser;
import io.realword.security.jwt.Jwt;
import io.realword.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserServiceImp userService;
  private final Jwt jwt;

  @Autowired
  public UserController(UserServiceImp userService, Jwt jwt) {
    this.userService = userService;
    this.jwt = jwt;
  }

  @PostMapping("/users")
  public ResponseEntity<ResUser> userRegister(@RequestBody ReqUser user) {
    try {
      logger.info("'/users' Request  Data = {}");
      ResUser savedUser = userService.register(user);
      logger.info("'/users' Response  Data = {}", savedUser);
      return ResponseEntity.ok(savedUser);
    } catch (Exception e) {
      logger.error("Failed to register user", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(ReqUser user) {
    try {
      logger.info("'/login' Request Data = {}", user);
      String token = userService.login(user);
      logger.info("'/login' Response Token = {}", token);
      return ResponseEntity.ok(token);
    } catch (Exception e) {
      logger.error("Failed to login user", e);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
