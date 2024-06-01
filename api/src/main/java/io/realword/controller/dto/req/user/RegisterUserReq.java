package io.realword.controller.dto.req.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserReq {
  private String username;
  private String email;
  private String password;
}
