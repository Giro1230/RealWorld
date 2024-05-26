package io.realword.controller;

import io.realword.model.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping("/users")
  public void UserRegister(@RequestBody UserDTO user){
    logger.info("Request Data = {}", user);
//    UserDTO returnData = "";
  }
}
