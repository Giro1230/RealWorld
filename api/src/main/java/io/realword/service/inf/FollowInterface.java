package io.realword.service.inf;

import io.realword.controller.dto.req.follow.FollowReq;
import io.realword.controller.dto.req.follow.GetFollowUserReq;
import io.realword.controller.dto.req.follow.UnFollowReq;
import io.realword.controller.dto.req.follow.UserReq;
import io.realword.controller.dto.res.follow.FollowRes;
import io.realword.controller.dto.res.follow.GetFollowRes;
import io.realword.controller.dto.res.follow.UnFollowRes;

public interface FollowInterface {
  GetFollowRes getProfile(UserReq user, GetFollowUserReq data);
  FollowRes followProfile (UserReq user, FollowReq data);
  UnFollowRes unFollowProfile (UserReq user, UnFollowReq data);
}
