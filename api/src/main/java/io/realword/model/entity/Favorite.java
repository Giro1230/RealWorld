package io.realword.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
  @MapsId("user_id")
  private User user;

  @ManyToOne
  @MapsId("article_id")
  private Article article;

  @Column
  private LocalDateTime created_at;

  @PrePersist
  public void onCreatedAt(){
    this.created_at = LocalDateTime.now();
  }
}
