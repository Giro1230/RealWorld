package io.realword.controller.dto.req.article;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedCommentReq {
  private String body;
}
