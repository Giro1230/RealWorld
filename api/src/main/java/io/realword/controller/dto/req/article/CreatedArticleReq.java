package io.realword.controller.dto.req.article;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedArticleReq {
  private String title;
  private String description;
  private String body;
  private List<String> tagList;
}
