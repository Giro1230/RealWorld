package io.realword.controller.dto.res.follow;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnFollowRes {
  private String username;
  private String bio;
  private String image;
  private Boolean following;
}
