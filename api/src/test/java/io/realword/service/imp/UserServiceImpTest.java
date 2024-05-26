package io.realword.service.imp;

import io.realword.model.dto.UserDTO;
import io.realword.service.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImpTest {

  Logger logger = LoggerFactory.getLogger(UserServiceImpTest.class);
  @Autowired
  UserServiceImp userService;
  @Autowired
  UserRepository userRepository;

  @BeforeEach
  void before () {
    logger.info("TEST Start");
  }

  @Test
  void save() {
    // given
    UserDTO data = new UserDTO(null, "test", "test@test","test",null,null,null,null);
    logger.info("insert data : {}", data.toString());

    // when
    data = (UserDTO) userService.save(data);

    // then

    logger.info("user save return : {}", data.toString());
  }

  @Test
  void getUserById() {
    // given
    Long userId = 1L;

    // when
    UserDTO data = (UserDTO) userService.getUserById(userId);

    // then
    logger.info("getUserId : {}", data.toString());
  }

  @Test
  void getUserList() {
    // given

    // when
    List<Object> datas = userService.getUserList();

    // then
    logger.info("getAllUser : {}", datas.toArray());
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
