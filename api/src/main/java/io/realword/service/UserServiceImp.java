package io.realword.service;

import io.realword.controller.dto.req.user.CurrentUserReq;
import io.realword.controller.dto.req.user.LoginUserReq;
import io.realword.controller.dto.req.user.RegisterUserReq;
import io.realword.controller.dto.req.user.UpdateUserReq;
import io.realword.controller.dto.res.user.CurrentUserRes;
import io.realword.controller.dto.res.user.LoginUserRes;
import io.realword.controller.dto.res.user.RegisterUserRes;
import io.realword.controller.dto.res.user.UpdatedUserRes;
import io.realword.security.jwt.Jwt;
import io.realword.domain.User;
import io.realword.repository.UserRepository;
import io.realword.service.inf.UserInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserInterface {

  private final Logger logger;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final Jwt jwt;

  @Autowired
  public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, Jwt jwt) {
    this.logger = LoggerFactory.getLogger(UserServiceImp.class);
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwt = jwt;
  }

  @Override
  public RegisterUserRes register(RegisterUserReq data) {
    try {
      // password encode
      data.setPassword(passwordEncoder.encode(data.getPassword()));
      logger.info("user password encoded : {}", data.getPassword());

      // RegisterUserReq -> User
      User userData = User
        .builder()
        .username(data.getUsername())
        .email(data.getEmail())
        .password(data.getPassword())
        .build();

      // User -> RegisterUserRes
      return RegisterUserRes
        .builder()
        .username(userData.getUsername())
        .email(userData.getEmail())
        .bio(userData.getBio())
        .image(userData.getImage())
        .token(jwt.generateToken(userData))
        .build();

    } catch (Exception e) {
      logger.error("Failed to save user", e);
      throw new RuntimeException("Failed to save user", e);
    }
  }

  @Override
  public LoginUserRes login(LoginUserReq data) {
    // getUserEmail
    Optional<User> optionalUser = userRepository.findByEmail(data.getEmail());

    // Optional -> User
    User user = optionalUser.orElseThrow(() -> new RuntimeException("Invalid credentials"));

    // password checked
    if (passwordEncoder.matches(data.getPassword(), user.getPassword())) {

      // User -> LoginUserRes
      return LoginUserRes.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .bio(user.getBio())
        .image(user.getImage())
        .token(jwt.generateToken(user))
        .build();

    } else {
      logger.error("Failed to login user");
      throw new RuntimeException("Failed to login user");
    }
  }

  @Override
  public CurrentUserRes getCurrentUser(CurrentUserReq data) {
    try {
      // findUserByEmail
      User user = getUserByEmail(data.getEmail());

      // User -> CurrentUserRes
      return CurrentUserRes.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .bio(user.getBio())
        .image(user.getImage())
        .token(jwt.generateToken(user))
        .build();

    } catch (Exception e) {

      logger.error("Failed to find user", e);
      throw new RuntimeException("Failed to find user", e);
    }
  }

  @Override
  public UpdatedUserRes update(String email, UpdateUserReq data) {
    try {
      // findUserByEmail
      User user = getUserByEmail(email);

      // updatedUser
      user.updated(data);

      // User -> UpdatedUserRes
      return UpdatedUserRes.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .bio(user.getBio())
        .image(user.getImage())
        .token(jwt.generateToken(user))
        .build();
    } catch (Exception e) {
      logger.error("Failed to update user", e);
      throw new RuntimeException("Failed to update user", e);
    }
  }

  public User getUserByEmail(String userEmail) {
    try {

      return userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));
    } catch (Exception e) {

      logger.error("Failed to get user by email: " + userEmail, e);
      throw new RuntimeException("Failed to get user by email: " + userEmail, e);
    }
  }
}
