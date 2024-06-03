package io.realword.controller.dto.res.follow;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetFollowRes {
  private String username;
  private String bio;
  private String image;
  private Boolean following;
}
