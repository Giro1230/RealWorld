package io.realword.service;

import io.realword.repository.FollowRepository;
import io.realword.repository.UserRepository;
import io.realword.service.inf.FollowInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImp implements FollowInterface {
  private final Logger logger;
  private final FollowRepository followRepository;
  private final UserRepository userRepository;

  @Autowired
  public FollowServiceImp( FollowRepository followRepository, UserRepository userRepository) {
    this.logger = LoggerFactory.getLogger(FollowServiceImp.class);
    this.followRepository = followRepository;
    this.userRepository = userRepository;
  }

  public void getProfile() {
    try {

    } catch (Exception e) {
      logger.error("Fail to get profile: ", e);
      throw new RuntimeException ("Fail to get profile: ", e);
    }
  }

  public void followProfile () {
    try {

    } catch (Exception e) {
      logger.error("Fail to follow profile: ", e);
      throw new RuntimeException ("Fail to follow profile: ", e);
    }
  }

  public void unfollowProfile () {
    try {

    } catch (Exception e) {
      logger.error("Fail to unfollow profile: ", e);
      throw new RuntimeException ("Fail to unfollow profile: ", e);
    }
  }
}
