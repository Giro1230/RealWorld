package io.realword.service;

import io.realword.controller.dto.req.user.CurrentUserReq;
import io.realword.controller.dto.req.user.LoginUserReq;
import io.realword.controller.dto.req.user.RegisterUserReq;
import io.realword.controller.dto.res.user.CurrentUserRes;
import io.realword.controller.dto.res.user.LoginUserRes;
import io.realword.controller.dto.res.user.RegisterUserRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImpTest {

  @Autowired
  private UserServiceImp userService;
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpTest.class);

  @BeforeEach
  void before() {
    logger.info("TEST Start");
  }

  @Test
  void register() {
    // given
    RegisterUserReq user = RegisterUserReq.builder()
      .username("testuser")
      .email("testuser@example.com")
      .password("password")
      .build();

    // when
    RegisterUserRes registeredUser = userService.register(user);

    // then
    assertNotNull(registeredUser);
    assertEquals("testuser", registeredUser.getUsername());
  }

  @Test
  void login() {
    // given
    String email = "testuser@example.com";
    String password = "password";
    LoginUserReq user = new LoginUserReq();
    user.setEmail(email);
    user.setPassword(password);

    // when
    LoginUserRes loggedInUser = userService.login(user);

    // then
    assertNotNull(loggedInUser);
    assertEquals(email, loggedInUser.getEmail());

  }

  @Test
  void getCurrentUser() {
    // given
    String email = "testuser@example.com";
    CurrentUserReq user = new CurrentUserReq();
    user.setEmail(email);


    // when
    CurrentUserRes currentUser = userService.getCurrentUser(user);

    // then
    assertNotNull(currentUser);
    assertEquals(email, currentUser.getEmail());
  }

//  @Test
//  void update() {
//    // given
//    String email = "testuser@example.com";
//    User existingUser = new User();
//    existingUser.setEmail(email);
//
//    User updatedUserDetails = new User();
//    updatedUserDetails.setUsername("updatedUser");
//    updatedUserDetails.setEmail(email);
//
//    when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
//    when(userRepository.save(existingUser)).thenReturn(updatedUserDetails);
//
//    // when
//    User updatedUser = userService.update(email, updatedUserDetails);
//
//    // then
//    assertNotNull(updatedUser);
//    assertEquals("updatedUser", updatedUser.getUsername());
//    verify(userRepository, times(1)).findByEmail(email);
//    verify(userRepository, times(1)).save(existingUser);
//  }
}
