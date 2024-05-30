package io.realword.controller.dto.res;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResTag {
  private Long id;
  private String tagName;
  private Long articleId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
