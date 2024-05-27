package io.realword.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class FavoriteId implements Serializable {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "article_id")
  private Long articleId;
}
