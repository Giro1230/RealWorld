package io.realword.service;

import io.realword.controller.dto.req.ReqUser;
import io.realword.controller.dto.res.ResUser;
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
  public ResUser register(ReqUser data) {
    try {;
      data.setPassword(passwordEncoder.encode(data.getPassword()));
      User userEntity = User.builder()
        .id(null)
        .username(data.getUsername())
        .email(data.getEmail())
        .password(data.getPassword())
        .bio(data.getBio())
        .image(data.getImage())
        .createdAt(null)
        .updatedAt(null)
        .build();
      userEntity = userRepository.save(userEntity);
      return userEntity.toRes();
    } catch (Exception e) {
      logger.error("Failed to save user", e);
      throw new RuntimeException("Failed to save user", e);
    }
  }

  public String login(ReqUser data) {
    Optional<User> optionalUser = userRepository.findByEmail(data.getEmail());
    User user = optionalUser.orElseThrow(() -> new RuntimeException("Invalid credentials"));
    if (passwordEncoder.matches(data.getPassword(), user.getPassword())) {
      return jwt.generateToken(user);
    } else {
      throw new RuntimeException("Invalid credentials");
    }
  }

  @Override
  public ResUser getUserById(Long userId) {
    try {
      User userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
      return userEntity.toRes();
    } catch (Exception e) {
      logger.error("Failed to get user by id: {}", userId, e);
      throw new RuntimeException("Failed to get user by id: " + userId, e);
    }
  }

  @Override
  public List<ResUser> getUserList() {
    try {
      List<User> usersEntity = userRepository.findAll();
      return usersEntity.stream()
        .map(User::toRes)
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get user list", e);
      throw new RuntimeException("Failed to get user list", e);
    }
  }

  @Override
  public ResUser getUserByEmail(String userEmail) {
    try {
      User userEntity = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));
      return userEntity.toRes();
    } catch (Exception e) {
      logger.error("Failed to get user by email: " + userEmail, e);
      throw new RuntimeException("Failed to get user by email: " + userEmail, e);
    }
  }

  @Override
  public ResUser update(ReqUser data) {
    try {

      User userEntity = User.builder()
        .id(data.getId())
        .username(data.getUsername())
        .email(data.getEmail())
        .password(data.getPassword())
        .bio(data.getBio())
        .image(data.getImage())
        .createdAt(data.getCreatedAt())
        .updatedAt(null)
        .build();

      userEntity = userRepository.save(userEntity);
      return userEntity.toRes();
    } catch (Exception e) {
      logger.error("Failed to update user", e);
      throw new RuntimeException("Failed to update user", e);
    }
  }

  @Override
  public Boolean delete(Long userId) {
    try {
      userRepository.deleteById(userId);
      return true;
    } catch (Exception e) {
      logger.error("Failed to delete user with id: " + userId, e);
      return false;
    }
  }
}
