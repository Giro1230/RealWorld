package io.realword.domain;

import io.realword.controller.dto.res.ResComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String body;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "article_id")
  private Article article;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public ResComment toRes(){
    return ResComment.builder()
      .id(this.id)
      .body(this.body)
      .userName(this.user.getUsername())
      .createdAt(this.createdAt)
      .updatedAt(this.updatedAt)
      .build();
  }
}
