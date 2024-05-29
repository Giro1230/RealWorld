package io.realword.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
