package io.realword.controller.dto.res.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatedUserRes {
  private String username;
  private String email;
  private String bio;
  private String image;
  private String token;
}
