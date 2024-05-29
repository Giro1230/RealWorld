package io.realword.service.imp;

import io.realword.repository.UserRepository;
import io.realword.service.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class UserServiceImpTest {

  Logger logger = LoggerFactory.getLogger(UserServiceImpTest.class);
  @Autowired
  UserServiceImp userService;
  @Autowired
  UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @BeforeEach
  void before () {
    logger.info("TEST Start");
  }

  @Test
  void save() {
    // given
    UserDTO data = new UserDTO(null, "test", "test@test.com","test",null,null,null,null);
    logger.info("insert data : {}", data.toString());

    // when
    data = (UserDTO) userService.save(data);

    // then

    logger.info("user save return : {}", data.toString());
  }

  @Test
  void getUserById() {
    // given
    Long userId = 5L;

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
    // given
    String email = "test@test";

    // when
    UserDTO data = (UserDTO) userService.getUserByEmail(email);

    // then
    logger.info("getUserByEmail : {}", data.toString());
  }

  @Test
  void update() {
    // given
    UserDTO data = new UserDTO(1L, "test", "test@test", "2222", null, null, null, null);
    data.setPassword(passwordEncoder.encode(data.getPassword()));

    // when
    data = (UserDTO) userService.update(data);

    // then
    logger.info("update : {}", data);
  }

//  @Test
//  void delete() {
//    // given
//    Long userId = 1L;
//
//    // when
//    Boolean data = userService.delete(userId);
//
//    // then
//    logger.info("delete : {}", data);
//  }

  @Test
  void login() {
    // given
    UserDTO data = new UserDTO();
    data.setEmail("test@test.com");
    data.setPassword("test");

    // when
    String returnData = userService.login(data);

    // then
    logger.info("login Token : {}", returnData);
  }
}
