package io.realword.controller;

import io.realword.controller.dto.req.user.LoginUserReq;
import io.realword.controller.dto.req.user.RegisterUserReq;
import io.realword.controller.dto.res.user.LoginUserRes;
import io.realword.controller.dto.res.user.RegisterUserRes;
import io.realword.security.jwt.Jwt;
import io.realword.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {
  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserServiceImp userService;
  private final Jwt jwt;

  @Autowired
  public AuthController(UserServiceImp userService, Jwt jwt) {
    this.userService = userService;
    this.jwt = jwt;
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterUserRes> userRegister(@RequestBody RegisterUserReq user) {
    try {
      logger.info("'/users' Request  Data = {}", user);

      RegisterUserRes returnData = userService.register(user);
      logger.info("'/users' Respones Data = {}", user);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {

      logger.error("Failed to register user", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping("/login")
  public ResponseEntity<LoginUserRes> login(@RequestBody LoginUserReq user) {
    try {
      logger.info("'/login' Request Data = {}", user);

      LoginUserRes returnData = userService.login(user);
      logger.info("'/login' Response Token = {}", returnData);

      return ResponseEntity.ok(returnData);
    } catch (Exception e) {

      logger.error("Failed to login user", e);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
