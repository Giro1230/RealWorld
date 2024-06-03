package io.realword.controller.dto.req.article;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatedArticleReq {
  private String body;
}
