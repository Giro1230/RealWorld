package io.realword.controller.dto.res;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResComment {
  private Long id;
  private String body;
  private String userName;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
