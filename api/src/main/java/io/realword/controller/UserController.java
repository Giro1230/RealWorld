package io.realword.controller;

import io.realword.model.dto.UserDTO;
import io.realword.service.imp.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserServiceImp userService;

  @Autowired
  public UserController(UserServiceImp userService) {
    this.userService = userService;
  }

  @PostMapping("/users")
  public ResponseEntity<UserDTO> userRegister(@RequestBody UserDTO user) {
    try {
      logger.info("'/users' Request  Data = {}", user);
      UserDTO savedUser = (UserDTO) userService.save(user);
      logger.info("'/users' Response  Data = {}", savedUser);
      return ResponseEntity.ok(savedUser);
    } catch (Exception e) {
      logger.error("Failed to register user", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
