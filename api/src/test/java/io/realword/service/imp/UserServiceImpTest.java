package io.realword.service.imp;

import io.realword.model.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {

  private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
  private final UserServiceImp userService;

  public UserServiceImpTest(UserServiceImp userService) {
    this.userService = userService;
  }

  @BeforeEach
  void setUp() {
    logger.info("TEST Start");
  }

  @Test
  void save() {
    // given
    UserDTO data = new UserDTO(0L, "test", "TEST@TEST", "test", null, null, null, null);

    // when
    data = (UserDTO)userService.save(data);

    // then
    logger.info("save test return data : {}", data);
  }

  @Test
  void getUserById() {
  }

  @Test
  void getUserList() {
  }

  @Test
  void getUserByEmail() {
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }
}
