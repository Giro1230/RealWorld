package io.realword.service;

import io.realword.controller.dto.req.follow.FollowReq;
import io.realword.controller.dto.req.follow.GetFollowUserReq;
import io.realword.controller.dto.req.follow.UnFollowReq;
import io.realword.controller.dto.req.follow.UserReq;
import io.realword.controller.dto.res.follow.FollowRes;
import io.realword.controller.dto.res.follow.GetFollowRes;
import io.realword.controller.dto.res.follow.UnFollowRes;
import io.realword.domain.Follow;
import io.realword.domain.User;
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

  @Override
  public GetFollowRes getProfile(UserReq user, GetFollowUserReq data) {
    try {
      data.setUsername("celeb_" + data.getUsername());
      User userData = userRepository.findByEmail(user.getEmail());
      User targetUserData = userRepository.findByUsername(user.getEmail());

      if (targetUserData == null) {
        throw new RuntimeException("User not found");
      }

      boolean isFollowing = followRepository.isFollowing(userData.getId(), targetUserData.getId());

      return GetFollowRes.builder()
        .username(targetUserData.getUsername())
        .bio(targetUserData.getBio())
        .image(targetUserData.getImage())
        .following(isFollowing)
        .build();
    } catch (Exception e) {
      logger.error("Fail to get profile: ", e);
      throw new RuntimeException ("Fail to get profile: ", e);
    }
  }

  @Override
  public FollowRes followProfile (UserReq user, FollowReq data) {
    try {
      data.setUsername("celeb_" + data.getUsername());
      User userData = userRepository.findByEmail(user.getEmail());
      User targetUserData = userRepository.findByUsername(user.getEmail());

      if (targetUserData == null) {
        throw new RuntimeException("User not found");
      }

      Follow follow = Follow.builder()
        .follower(userData)
        .followee(targetUserData)
        .build();

      followRepository.save(follow);

      return FollowRes.builder()
        .username(targetUserData.getUsername())
        .bio(targetUserData.getBio())
        .image(targetUserData.getImage())
        .following(true)
        .build();
    } catch (Exception e) {
      logger.error("Fail to follow profile: ", e);
      throw new RuntimeException ("Fail to follow profile: ", e);
    }
  }

  @Override
  public UnFollowRes unFollowProfile (UserReq user, UnFollowReq data) {
    try {
      data.setUsername("celeb_" + data.getUsername());
      User userData = userRepository.findByEmail(user.getEmail());
      User targetUserData = userRepository.findByUsername(user.getEmail());

      if (targetUserData == null) {
        throw new RuntimeException("User not found");
      }

      Follow follow = followRepository.findByFollowerIdAndFolloweeId(userData.getId(), targetUserData.getId());
      if (follow == null) {
        throw new RuntimeException("Not following");
      }

      followRepository.delete(follow);

      return UnFollowRes.builder()
        .username(targetUserData.getUsername())
        .bio(targetUserData.getBio())
        .image(targetUserData.getImage())
        .following(false)
        .build();
    } catch (Exception e) {
      logger.error("Fail to unfollow profile: ", e);
      throw new RuntimeException ("Fail to unfollow profile: ", e);
    }
  }
}
