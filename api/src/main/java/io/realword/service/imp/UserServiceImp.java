package io.realword.service.imp;

import io.realword.model.dto.UserDTO;
import io.realword.model.entity.User;
import io.realword.service.inf.CRUDInterface;
import io.realword.service.repository.UserRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements CRUDInterface {

  private final Logger logger;
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImp(UserRepository userRepository) {
    this.logger = LoggerFactory.getLogger(UserServiceImp.class);
    this.userRepository = userRepository;
  }

  @Override
  public Object save(Object data) {
    try {
      UserDTO userDTO = (UserDTO) data;
      User userEntity = userDTO.insertDataConverter();
      userEntity = userRepository.save(userEntity);
      return userEntity.toDTO();
    } catch (Exception e) {
      logger.error("Failed to save user", e);
      throw new RuntimeException("Failed to save user", e);
    }
  }

  @Override
  public Object getUserById(Long userId) {
    try {
      User userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
      return userEntity.toDTO();
    } catch (Exception e) {
      logger.error("Failed to get user by id: {}", userId, e);
      throw new RuntimeException("Failed to get user by id: " + userId, e);
    }
  }

  @Override
  public List<Object> getUserList() {
    try {
      List<User> usersEntity = userRepository.findAll();
      return usersEntity.stream()
        .map(User::toDTO)
        .collect(Collectors.toList());
    } catch (Exception e) {
      logger.error("Failed to get user list", e);
      throw new RuntimeException("Failed to get user list", e);
    }
  }

  public Object getUserByEmail(String userEmail) {
    try {
      User userEntity = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));
      return userEntity.toDTO();
    } catch (Exception e) {
      logger.error("Failed to get user by email: " + userEmail, e);
      throw new RuntimeException("Failed to get user by email: " + userEmail, e);
    }
  }

  @Override
  public Object update(Object data) {
    try {
      UserDTO userDTO = (UserDTO) data;
      User userEntity = ((UserDTO) data).updateDataConverter();

      userEntity = userRepository.save(userEntity);
      return userEntity.toDTO();
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
