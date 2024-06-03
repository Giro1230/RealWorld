package io.realword.controller.dto.res.article;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedCommentRes {
  private Long id;
  private String body;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String author;
}
