package io.realword.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table
public class Favorite {

  @EmbeddedId
  private FavoriteId id;

  @ManyToOne
  @MapsId("userId")
  private User user;

  @ManyToOne
  @MapsId("articleId")
  private Article article;

  @Column
  private LocalDateTime createdAt;

  @PrePersist
  public void onCreatedAt(){
    this.createdAt = LocalDateTime.now();
  }
}
