package io.realword.controller.dto.res.article;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllArticlesByAuthorByTagRes {
  private String title;
  private String slug;
  private String body;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String description;
  private List<String> tagList;
  private String author;
  private boolean favorited;
  private int favoritesCount;
}
