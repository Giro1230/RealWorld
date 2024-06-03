package io.realword.controller.dto.req.follow;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFollowUserReq {
  private String username;
}
