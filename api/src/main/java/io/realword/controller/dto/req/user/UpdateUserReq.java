package io.realword.controller.dto.req.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserReq {
  private String email;
}
