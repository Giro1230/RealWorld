package io.realword.controller.dto.res;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResUser {
  private Long id;
  private String username;
  private String email;
  private String password;
  private String bio;
  private String image;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
