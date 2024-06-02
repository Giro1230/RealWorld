package io.realword.controller.dto.req.follow;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnFollowReq {
  private String username;
}
